package hardsign.server.entities;

import javax.persistence.*;

@Entity
@Table(name = "hardgram_users")
public class UserEntity {
    public UserEntity() { }

    public UserEntity(String nickname, String fullName, String avatar, String password) {
        this.nickname = nickname;
        this.fullName = fullName;
        this.Avatar = avatar;
        this.Password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "fullName")
    public String fullName;

    @Column(name = "nickname")
    public String nickname;

    @Column(name = "avatar")
    public String Avatar;

    @Column(name = "password", nullable = false)
    public String Password;
}
