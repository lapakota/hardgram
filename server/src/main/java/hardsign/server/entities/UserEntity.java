package hardsign.server.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hardgram_users")
public class UserEntity {
    public UserEntity() { }

    public UserEntity(String nickname, String fullName, byte[] avatar, String password) {
        this.id = 0L;
        this.nickname = nickname;
        this.fullName = fullName;
        this.avatar = avatar;
        this.password = password;
        this.posts = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "avatar")
    private byte[] avatar;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PostEntity> posts;

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PostEntity> getPosts() {
        return posts;
    }

    public void setPosts(List<PostEntity> posts) {
        this.posts = posts;
    }
}
