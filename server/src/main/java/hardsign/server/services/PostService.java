package hardsign.server.services;

import hardsign.server.entities.PostEntity;
import hardsign.server.models.post.CreatePostModel;
import hardsign.server.models.post.PostModel;
import hardsign.server.models.post.UpdatePostModel;
import hardsign.server.repositories.PostRepository;
import hardsign.server.repositories.UserRepository;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@Component
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Inject
    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public PostModel getPost(Long postId) {
        var post = postRepository.findById(postId).orElseThrow(RuntimeException::new);
        return mapToModel(post);
    }

    public PostModel updatePost(UpdatePostModel updatePostModel) {
        // Мне похуй абсолютно, через ебаный Query и Modify  нихуя не работает
        var post = postRepository.findById(updatePostModel.getPostId()).get();
        post.setDescription(updatePostModel.getDescription());
        post.setPhotos(updatePostModel.getPhotos());
        var newPost = postRepository.save(post);
        return mapToModel(newPost);
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    public PostModel createPost(CreatePostModel createPostModel) {
        var user = userRepository.findById(createPostModel.getUserId()).get();
        var post = new PostEntity(user, createPostModel.getPhotos(), new Date(), createPostModel.getDescription());
        return mapToModel(postRepository.save(post));
    }

    private PostModel mapToModel(PostEntity postEntity) {
        return new PostModel(postEntity.getId(), postEntity.getUser().getId(), postEntity.getPhotos(), postEntity.getCreateTime(), postEntity.getDescription());
    }
}
