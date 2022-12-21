package hardsign.server.services;

import hardsign.server.common.Result;
import hardsign.server.common.Status;
import hardsign.server.entities.UserEntity;
import hardsign.server.models.user.UserRegistrationModel;
import hardsign.server.models.user.UserUpdateModel;
import hardsign.server.repositories.UserRepository;
import hardsign.server.utils.Helper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;


@Component
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final CurrentUserService currentUserService;
    private final PasswordEncoderService encoder;
    private final Helper helper;

    @Inject
    public UserService(
            UserRepository userRepository,
            CurrentUserService currentUserService, PasswordEncoderService encoder, Helper helper) {
        this.userRepository = userRepository;
        this.currentUserService = currentUserService;
        this.encoder = encoder;
        this.helper = helper;
        AddDefaultUserIfNotExist();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var dbUser = userRepository.findByNickname(username);
        return new User(dbUser.getNickname(), dbUser.getPassword(), new ArrayList<>());
    }

    public Result<UserEntity> getUser(String nickname) {
        return Result.of(userRepository.findByNickname(nickname), Status.NotFound);
    }

    public Result<UserEntity> updateUser(UserUpdateModel updateUserModel) {
        return currentUserService.getCurrentUser()
                .then(user -> update(user, updateUserModel))
                .then(userRepository::save);
    }

    private UserEntity update(UserEntity user, UserUpdateModel updateUserModel) {
        user.setAvatar(helper.encodeStringToBase64(updateUserModel.getAvatar()));
        user.setFullName(updateUserModel.getFullName());
        return user;
    }

    public Result<UserEntity> addUser(UserRegistrationModel userRegistrationModel){
        var nickname = userRegistrationModel.getNickname();
        var findByNickname = userRepository.findByNickname(nickname);

        if (findByNickname != null) {
            return Result.fault(Status.IncorrectArguments);
        }

        return Result
                .of(() -> map(userRegistrationModel))
                .then(userRepository::save);
    }

    private UserEntity map(UserRegistrationModel model) {
        var password = encoder.getEncoder().encode(model.getPassword());
        return new UserEntity(model.getNickname(), model.getFullName(), helper.encodeStringToBase64(model.getAvatar()), password);
    }

    private void AddDefaultUserIfNotExist() {
        var defaultUser = getDefaultUser();
        var dbUser = getUser(defaultUser.getNickname());

        if (dbUser.isFailure())
            userRepository.save(defaultUser);
    }

    private UserEntity getDefaultUser() {
        var password = encoder.getEncoder().encode("I_hate_Java");
        return new UserEntity("lapakota", "Артём Самошкин", null, password);
    }
}