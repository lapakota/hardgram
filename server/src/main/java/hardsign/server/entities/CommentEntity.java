package hardsign.server.entities;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "comments")
public class CommentEntity {
     public CommentEntity() {

     }

    public CommentEntity(UserEntity user, PostEntity post, String text, Date createTime) {
        this.user = user;
        this.post = post;
        this.text = text;
        this.createTime = createTime;
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

    @Column(name = "create_at", nullable = false)
    private Date createTime;

    @Column(name = "text", nullable = false)
    private String text;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getId() {
        return Id;
    }
}