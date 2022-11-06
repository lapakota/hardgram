package hardsign.server.entities;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "posts")
public class PostEntity {
    public PostEntity() { }

    public PostEntity(UserEntity user, List<String> photos, Date createTime, String description) {
        this.id = 0L;
        this.user = user;
        this.photos = photos;
        this.createTime = createTime;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "photos", nullable = false)
    @ElementCollection
    private List<String> photos;

    @Column(name = "create_time", nullable = false)
    private Date createTime;

    @Column(name = "description")
    private String description;

    public Long getId() {
        return id;
    }

    public UserEntity getUser() {
        return user;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
