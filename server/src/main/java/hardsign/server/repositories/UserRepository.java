package hardsign.server.repositories;

import hardsign.server.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByNickname(String nickname);
    Optional<UserEntity> findById(Long id);
}