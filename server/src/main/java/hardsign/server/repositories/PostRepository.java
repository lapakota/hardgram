package hardsign.server.repositories;

import hardsign.server.entities.PostEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PostRepository extends CrudRepository<PostEntity, Long> {
    Optional<PostEntity> findById(Long id);
}
