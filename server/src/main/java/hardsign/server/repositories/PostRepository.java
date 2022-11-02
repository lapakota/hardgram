package hardsign.server.repositories;

import hardsign.server.models.PostEntity;
import hardsign.server.models.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<PostEntity, Long> {
}
