package hardsign.server.services;

import hardsign.server.entities.PostEntity;
import hardsign.server.models.post.CreatePostModel;
import hardsign.server.models.post.PostModel;
import hardsign.server.repositories.PostRepository;
import hardsign.server.repositories.UserRepository;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Date;

@Component
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Inject
    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public PostModel GetPost(Long postId) {
        var post = postRepository.findById(postId).orElse(null);
        try {
            assert post != null;
            return mapToModel(post);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public PostModel UpdatePost(PostModel postModel) {
        return mapToModel(postRepository.save(mapToEntity(postModel)));
    }

    public PostEntity CreatePost(CreatePostModel createPostModel) {
        var user = userRepository.findById(createPostModel.getUserId()).get();
        var post = new PostEntity(user, createPostModel.getPhotos(), new Date(), createPostModel.getDescription());
        return postRepository.save(post);
    }

    private PostEntity mapToEntity(PostModel model) {
        throw new NotYetImplementedException();
    }

    private PostModel mapToModel(PostEntity postEntity) {
        return new PostModel(postEntity.getId(), postEntity.getUser().getId(), postEntity.getPhotos(), postEntity.getCreateTime(), postEntity.getDescription());
    }
}
