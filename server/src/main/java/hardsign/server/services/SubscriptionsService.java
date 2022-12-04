package hardsign.server.services;

import hardsign.server.common.mapper.Mapper;
import hardsign.server.entities.SubscriptionEntity;
import hardsign.server.models.user.UserModel;
import hardsign.server.repositories.SubscriptionsRepository;
import hardsign.server.repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class SubscriptionsService {
    private final SubscriptionsRepository subscriptionsRepository;
    private final CurrentUserService currentUserService;
    private final UserRepository userRepository;
    private final Mapper mapper;

    public SubscriptionsService(SubscriptionsRepository subscriptionsRepository, CurrentUserService currentUserService, UserRepository userRepository, Mapper mapper) {
        this.subscriptionsRepository = subscriptionsRepository;
        this.currentUserService = currentUserService;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public List<UserModel> getFollowing(Long userId) {
        var subs = subscriptionsRepository.findByUserId(userId);
        return subs.stream().map(SubscriptionEntity::getFollowing).map(mapper::mapToModel).toList();

    }

    public List<UserModel> getFollowers(Long userId) {
        var subs = subscriptionsRepository.findByFollowingId(userId);
        return subs.stream().map(SubscriptionEntity::getUser).map(mapper::mapToModel).toList();
    }

    public void addFollow(Long followingId) {
        var subs = subscriptionsRepository.findByUserId(currentUserService.getCurrentUser().get().getId());
        var existSub = subs.stream().filter(x -> x.getFollowing().getId().equals(followingId)).toList();
        if (existSub.size() == 0) {
            var user = currentUserService.getCurrentUser().get();
            var followingUser = userRepository.findById(followingId).get();
            var sub = new SubscriptionEntity(user, followingUser);
            subscriptionsRepository.save(sub);
        }
    }

    public void deleteFollow(Long followingId) {
        var subs = subscriptionsRepository.findByUserId(currentUserService.getCurrentUser().get().getId());
        var deletingUser = subs.stream().filter(x -> x.getFollowing().getId().equals(followingId)).findFirst();
        if (!deletingUser.isEmpty()) {
            subscriptionsRepository.delete(deletingUser.get());
        }
    }
}
