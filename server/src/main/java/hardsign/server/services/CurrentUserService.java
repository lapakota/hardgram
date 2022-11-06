package hardsign.server.services;

import hardsign.server.entities.UserEntity;
import hardsign.server.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;


@Component
public class CurrentUserService {
    private final UserRepository userRepository;

    public CurrentUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserEntity> getCurrentUser() {
        var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var user = (User) principal;
        var byNickname = userRepository.findByNickname(user.getUsername());
        return Optional.ofNullable(byNickname);
    }
}
