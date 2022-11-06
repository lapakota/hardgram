package hardsign.server.models;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "comments")
public class CommentEntity {
    public CommentEntity(UserEntity user, PostEntity post, String text, Date createTime) {
        User = user;
        Post = post;
        Text = text;
        CreateTime = createTime;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(name = "user_id", nullable = false)
    public UserEntity User;

    @ManyToOne(targetEntity = PostEntity.class)
    @JoinColumn(name = "post_id", nullable = false)
    public PostEntity Post;

    @Column(name = "create_at", nullable = false)
    public Date CreateTime;

    @Column(name = "text", nullable = false)
    public String Text;
}