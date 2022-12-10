package hardsign.server.services;

import hardsign.server.common.Result;
import hardsign.server.common.Status;
import hardsign.server.entities.UserEntity;
import hardsign.server.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;


@Component
public class CurrentUserService {
    private final UserRepository userRepository;

    public CurrentUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Result<UserEntity> getCurrentUser() {
        var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var user = (User) principal;
        return Result.of(userRepository.findByNickname(user.getUsername()), Status.NotFound);
    }
}
