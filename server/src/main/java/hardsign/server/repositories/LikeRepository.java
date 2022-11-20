package hardsign.server.repositories;

import hardsign.server.entities.LikeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LikeRepository  extends CrudRepository<LikeEntity, Long> {
    @Query(value = "select * from likes l WHERE l.user_id = :userId and l.post_id = :postId", nativeQuery=true)
    Optional<LikeEntity> findLike(@Param("userId") Long userId,
                              @Param("postId") Long postId);
}
