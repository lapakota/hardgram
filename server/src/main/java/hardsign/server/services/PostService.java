package hardsign.server.services;

import hardsign.server.models.PostEntity;
import hardsign.server.repositories.PostRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Optional;

@Component
public class PostService {
    private final PostRepository postRepository;

    @Inject
    public PostService(PostRepository postRepository) { this.postRepository = postRepository; }

    public Optional<PostEntity> GetPost(Long postId) {return postRepository.findById(postId);}

    public PostEntity SavePost(PostEntity postEntity){return postRepository.save(postEntity);}


}
