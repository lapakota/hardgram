package hardsign.server.entities;

import javax.persistence.*;


@Entity
@Table(name = "likes")
public class LikeEntity {
    public LikeEntity() { }

    public LikeEntity(UserEntity user, PostEntity post) {
        User = user;
        Post = post;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long Id;

    @ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(name = "user_id", nullable = false)
    public UserEntity User;

    @ManyToOne(targetEntity = PostEntity.class)
    @JoinColumn(name = "post_id", nullable = false)
    public PostEntity Post;
}
