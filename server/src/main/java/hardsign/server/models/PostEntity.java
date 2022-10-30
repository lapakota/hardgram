package hardsign.server.models;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "posts")
public class PostEntity {

    @Id
    public UUID Id;

    @ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(name = "user_id", nullable = false)
    public UUID UserId;

    @Column(name = "photos", nullable = false)
    public Collection<String> Photos;

    @Column(name = "create_time", nullable = false)
    public Date CreateTime;

    @Column(name = "description")
    public String Description;
}
