package hardsign.server.repositories;

import hardsign.server.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByNickname(String nickname);
}