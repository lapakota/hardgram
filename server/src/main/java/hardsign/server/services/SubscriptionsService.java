package hardsign.server.services;

import hardsign.server.common.Result;
import hardsign.server.common.Status;
import hardsign.server.entities.SubscriptionEntity;
import hardsign.server.entities.UserEntity;
import hardsign.server.repositories.SubscriptionsRepository;
import hardsign.server.repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class SubscriptionsService {
    private final SubscriptionsRepository subscriptionsRepository;
    private final CurrentUserService currentUserService;
    private final UserRepository userRepository;
    private final UserService userService;

    public SubscriptionsService(SubscriptionsRepository subscriptionsRepository,
                                CurrentUserService currentUserService,
                                UserRepository userRepository,
                                UserService userService) {
        this.subscriptionsRepository = subscriptionsRepository;
        this.currentUserService = currentUserService;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public Result<List<UserEntity>> getFollowing(String nickname) {
        return userService.getUser(nickname)
                .then(user -> subscriptionsRepository.findByUserId(user.getId()), Status.NotFound)
                .then(subs -> subs.stream().map(SubscriptionEntity::getFollowing).toList());
    }

    public Result<List<UserEntity>> getFollowers(String nickname) {
        return userService.getUser(nickname)
                .then(user -> subscriptionsRepository.findByFollowingId(user.getId()), Status.NotFound)
                .then(subs -> subs.stream().map(SubscriptionEntity::getUser).toList());
    }

    public Result<Void> follow(String nickname) {
        var currentUser = currentUserService.getCurrentUser();
        return currentUser
                .then(user -> subscriptionsRepository.findByUserId(user.getId()), Status.NotFound)
                .when(subs -> subs.stream().noneMatch(sub -> alreadyFollow(nickname, sub)), Status.IncorrectArguments)
                .then(() -> {
                    var followingUser = userRepository.findByNickname(nickname);
                    var sub = new SubscriptionEntity(currentUser.get(), followingUser);
                    subscriptionsRepository.save(sub);
                    return null;
                });
    }

    public void unfollow(String nickname) {
        var subs = subscriptionsRepository.findByUserId(currentUserService.getCurrentUser().get().getId());
        var deletingUser = subs.stream().filter(x -> alreadyFollow(nickname, x)).findFirst();
        deletingUser.ifPresent(subscriptionsRepository::delete);
    }

    private boolean alreadyFollow(String nickname, SubscriptionEntity subscription) {
        return subscription.getFollowing().getNickname().equals(nickname);
    }
}
