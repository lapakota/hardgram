package hardsign.server.repositories;

import hardsign.server.entities.SubscriptionEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubscriptionsRepository extends CrudRepository<SubscriptionEntity, Long> {
    List<SubscriptionEntity> findByUserId(Long userId);
    List<SubscriptionEntity> findByFollowingId(Long userId);
}
