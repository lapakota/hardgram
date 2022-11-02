package hardsign.server.services;

import hardsign.server.entities.PostEntity;
import hardsign.server.models.post.CreatePostModel;
import hardsign.server.models.post.PostModel;
import hardsign.server.repositories.PostRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Date;

@Component
public class PostService {
    private final PostRepository postRepository;

    @Inject
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostModel GetPost(Long postId) {
        var post = postRepository.findById(postId).orElse(null);
        try {
            assert post != null;
            return mapToModel(post);
        } catch (Exception e) {
            throw new RuntimeException("Пошел нахуй");
        }
    }

    public PostModel SavePost(PostModel postModel) {
        return mapToModel(postRepository.save(mapToEntity(postModel)));
    }

    public PostModel CreatePost(CreatePostModel createPostModel) {
        var post = new PostEntity(createPostModel.getUserId(), createPostModel.getPhotos(), new Date(), createPostModel.getDescription());
        var newPost = postRepository.save(post);
        return mapToModel(newPost);
    }

    private PostEntity mapToEntity(PostModel model) {
        return new PostEntity(model.getUserId(), model.getPhotos(), model.getCreateTime(), model.getDescription());
    }

    private PostModel mapToModel(PostEntity postEntity) {
        return new PostModel(postEntity.Id, postEntity.UserIdId, postEntity.Photos, postEntity.CreateTime, postEntity.Description);
    }
}
