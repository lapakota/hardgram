package hardsign.server.services;

import hardsign.server.models.UserEntity;
import hardsign.server.repositories.UserRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Optional;


@Component
public class UserService {
    private final UserRepository userRepository;

    @Inject
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserEntity> GetUser(Long userId) {
        return userRepository.findById(userId);
    }

    public UserEntity SaveUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }
}