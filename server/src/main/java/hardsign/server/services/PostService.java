package hardsign.server.services;

import hardsign.server.common.mapper.Mapper;
import hardsign.server.entities.PostEntity;
import hardsign.server.models.post.CreatePostModel;
import hardsign.server.models.post.PostModel;
import hardsign.server.models.post.UpdatePostModel;
import hardsign.server.repositories.PostRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Date;


@Component
public class PostService {
    private final PostRepository postRepository;
    private final Mapper mapper;
    private final CurrentUserService currentUserService;

    @Inject
    public PostService(PostRepository postRepository, Mapper mapper, CurrentUserService currentUserService) {
        this.postRepository = postRepository;
        this.mapper = mapper;
        this.currentUserService = currentUserService;
    }

    public PostModel getPost(Long postId) {
        var post = postRepository.findById(postId).orElseThrow(RuntimeException::new);
        return mapper.mapToModel(post);
    }

    public PostModel updatePost(UpdatePostModel updatePostModel) {
        var post = postRepository.findById(updatePostModel.getPostId()).get();
        post.setDescription(updatePostModel.getDescription());
        post.setPhotos(updatePostModel.getPhotos());
        var newPost = postRepository.save(post);
        return mapper.mapToModel(newPost);
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    public PostModel createPost(CreatePostModel createPostModel) {
        var user = currentUserService.getCurrentUser().get();
        var post = new PostEntity(user, createPostModel.getPhotos(), new Date(), createPostModel.getDescription());
        return mapper.mapToModel(postRepository.save(post));
    }
}
