package hardsign.server.models;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "posts")
public class PostEntity {
    public PostEntity() { }

    public PostEntity(UserEntity user, List<String> photos, Date createTime, String description) {
        User = user;
        Photos = photos;
        CreateTime = createTime;
        Description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long Id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public UserEntity User;

    @Column(name = "photos", nullable = false)
    @ElementCollection
    public List<String> Photos;

    @Column(name = "create_time", nullable = false)
    public Date CreateTime;

    @Column(name = "description")
    public String Description;
}
