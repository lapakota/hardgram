package hardsign.server.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "comments")
public class CommentEntity {

    @Id
    public UUID Id;

    @ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(name = "user_id", nullable = false)
    public UUID UserId;

    @ManyToOne(targetEntity = PostEntity.class)
    @JoinColumn(name = "post_id", nullable = false)
    public UUID PostId;

    @Column(name = "create_at", nullable = false)
    public Date CreateTime;
}
