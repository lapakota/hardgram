package hardsign.server.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "likes")
public class LikeEntity {

    @Id
    public UUID Id;

    @Column(name = "user_id", nullable = false)
    public UUID UserId;

    @ManyToOne(targetEntity = PostEntity.class)
    @JoinColumn(name = "post_id", nullable = false)
    public UUID PostId;
}
