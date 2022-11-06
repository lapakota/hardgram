package hardsign.server.repositories;

import hardsign.server.models.UserEntity;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<UserEntity, Long> {

}