package hardsign.server.entities;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "posts")
public class PostEntity {
    public PostEntity() { }

    public PostEntity(Long userId, List<String> photos, Date createTime, String description) {
        UserIdId = userId;
        Photos = photos;
        CreateTime = createTime;
        Description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long Id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    public Long UserIdId;

    @Column(name = "photos", nullable = false)
    @ElementCollection
    public List<String> Photos;

    @Column(name = "create_time", nullable = false)
    public Date CreateTime;

    @Column(name = "description")
    public String Description;
}
