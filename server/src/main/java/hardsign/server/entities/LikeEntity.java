package hardsign.server.entities;

import javax.persistence.*;


@Entity
@Table(name = "likes")
public class LikeEntity {
    public LikeEntity() { }

    public LikeEntity(UserEntity user, PostEntity post) {
        this.user = user;
        this.post = post;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(targetEntity = PostEntity.class)
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity post;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public PostEntity getPost() {
        return post;
    }

    public void setPost(PostEntity post) {
        this.post = post;
    }

    public Long getId() {
        return Id;
    }
}
