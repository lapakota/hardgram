package hardsign.server.models;

import javax.persistence.*;

@Entity
@Table(name = "hardgram_users")
public class UserEntity {
    public UserEntity() { }

    public UserEntity(String nickname, String name, String surname, String avatar) {
        this.Nickname = nickname;
        this.Name = name;
        this.Surname = surname;
        this.Avatar = avatar;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "username")
    public String Name;

    @Column(name = "surname")
    public String Surname;

    @Column(name = "nickname")
    public String Nickname;

    @Column(name = "avatar")
    public String Avatar;
}
