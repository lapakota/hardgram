package hardsign.server.services;

import hardsign.server.entities.UserEntity;
import hardsign.server.models.UserRegistrationModel;
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
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var dbUser = userRepository.findByNickname(username);
        return new User(dbUser.nickname, dbUser.Password, new ArrayList<>());
    }

    public Optional<UserEntity> GetUser(Long userId) {
        return userRepository.findById(userId);
    }

    public UserEntity SaveUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
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
        return new UserEntity(model.getNickname(), model.getName(),
                model.getSurname(), model.getAvatar(), password);
    }
}