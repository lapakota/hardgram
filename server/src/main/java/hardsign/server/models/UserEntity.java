package hardsign.server.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    public UUID Id;

    @Column(name = "username", length = 20)
    public String Name;

    @Column(name = "surname", length = 30)
    public String Surname;

    @Column(name = "nickname", length = 24, nullable = false)
    public String Nickname;

    @Column(name = "avatar")
    public String Avatar;
}
