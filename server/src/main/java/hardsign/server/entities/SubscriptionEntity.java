package hardsign.server.entities;

import javax.persistence.*;

@Entity
@Table(name = "subscriptions")
public class SubscriptionEntity {

    public SubscriptionEntity() {
    }

    public SubscriptionEntity(UserEntity user, UserEntity following) {
        this.id = 0L;
        this.following = following;
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_id", nullable = false)
    private UserEntity following;

    public Long getId() {
        return id;
    }

    public UserEntity getFollowing() {
        return following;
    }
    public UserEntity getUser() {
        return user;
    }

}
