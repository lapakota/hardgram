package hardsign.server.services;

import hardsign.server.common.Result;
import hardsign.server.common.Status;
import hardsign.server.entities.PostEntity;
import hardsign.server.entities.UserEntity;
import hardsign.server.models.post.CreatePostModel;
import hardsign.server.models.post.UpdatePostModel;
import hardsign.server.repositories.PostRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Date;


@Component
public class PostService {
    private final PostRepository postRepository;
    private final CurrentUserService currentUserService;

    @Inject
    public PostService(PostRepository postRepository, CurrentUserService currentUserService) {
        this.postRepository = postRepository;
        this.currentUserService = currentUserService;
    }

    public Result<PostEntity> getPost(Long postId) {
        return Result.fromOptional(postRepository.findById(postId), Status.NotFound);
    }

    public Result<PostEntity> updatePost(UpdatePostModel updatePostModel) {
        return Result.fromOptional(postRepository.findById(updatePostModel.getPostId()), Status.NotFound)
                .then(post -> update(post, updatePostModel))
                .then(postRepository::save);
    }

    public Result<String> deletePost(Long postId) {
        return Result.of(() -> {
            postRepository.deleteById(postId);
            return "Success";
            }, Status.NotFound);
    }

    public Result<PostEntity> createPost(CreatePostModel createPostModel) {
        return currentUserService.getCurrentUser()
                .then(user -> createPostEntity(user, createPostModel))
                .then(postRepository::save);
    }

    private PostEntity update(PostEntity post, UpdatePostModel updatePostModel) {
        post.setDescription(updatePostModel.getDescription());
        post.setPhotos(updatePostModel.getPhotos());
        return post;
    }

    private PostEntity createPostEntity(UserEntity user, CreatePostModel createPostModel) {
        return new PostEntity(user, createPostModel.getPhotos(), new Date(), createPostModel.getDescription());
    }
}