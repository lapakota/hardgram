package hardsign.server.services;

import hardsign.server.entities.UserEntity;
import hardsign.server.models.user.UserRegistrationModel;
import hardsign.server.repositories.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Optional;


@Component
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoderService encoder;

    @Inject
    public UserService(UserRepository userRepository, PasswordEncoderService encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        AddDefaultUser();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var dbUser = userRepository.findByNickname(username);
        return new User(dbUser.getNickname(), dbUser.getPassword(), new ArrayList<>());
    }

    public Optional<UserEntity> getUser(Long userId) {
        return userRepository.findById(userId);
    }

    public UserEntity getUser(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    public UserEntity addUser(UserRegistrationModel userRegistrationModel) throws Exception {
        var dbUser = userRepository.findByNickname(userRegistrationModel.getNickname());

        if (dbUser != null) {
            throw new Exception("already exist");
        }

        var user = map(userRegistrationModel);

        return userRepository.save(user);
    }

    private UserEntity map(UserRegistrationModel model) {
        var password = encoder.getEncoder().encode(model.getPassword());
        return new UserEntity(model.getNickname(), model.getFullName(), model.getAvatar(), password);
    }

    private void AddDefaultUser() {
        var defaultUser = getDefaultUser();
        var dbUser = getUser(defaultUser.getNickname());

        if (dbUser == null)
            userRepository.save(defaultUser);
    }

    private UserEntity getDefaultUser() {
        var password = encoder.getEncoder().encode("I_hate_Java");
        return new UserEntity("lapakota", "Артём Самошкин", null, password);
    }
}