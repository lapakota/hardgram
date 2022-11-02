package hardsign.server.services;

import hardsign.server.entities.PostEntity;
import hardsign.server.models.post.CreatePostModel;
import hardsign.server.models.post.PostModel;
import hardsign.server.repositories.PostRepository;
import hardsign.server.repositories.UserRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

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
        } catch (Exception e){
            throw new RuntimeException("Пошел нахуй");
        }
    }

    public PostModel SavePost(PostModel postModel) {
        return mapToModel(postRepository.save(mapToEntity(postModel)));
    }

    public PostModel CreatePost(CreatePostModel createPostModel) {
        var post = new PostEntity(userRepository.findById(createPostModel.getUserId()).get(), createPostModel.getPhotos(), createPostModel.getCreateTime(), createPostModel.getDescription());
        postRepository.save(post);
        return mapToModel(post);
    }

    private PostEntity mapToEntity(PostModel model) {
        return new PostEntity(userRepository.findById(model.getUserId()).get(), model.getPhotos(), model.getCreateTime(), model.getDescription());
    }

    private PostModel mapToModel(PostEntity postEntity){
        return new PostModel(postEntity.Id, postEntity.User.id, postEntity.Photos, postEntity.CreateTime, postEntity.Description);
    }
}
