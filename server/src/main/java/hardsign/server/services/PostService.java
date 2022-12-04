package hardsign.server.services;

import hardsign.server.common.Result;
import hardsign.server.common.Status;
import hardsign.server.entities.PostEntity;
import hardsign.server.entities.UserEntity;
import hardsign.server.models.post.CreatePostModel;
import hardsign.server.models.post.UpdatePostModel;
import hardsign.server.repositories.PostRepository;
import hardsign.server.utils.Helper;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Date;


@Component
public class PostService {
    private final PostRepository postRepository;
    private final CurrentUserService currentUserService;
    private final Helper helper;

    @Inject
    public PostService(PostRepository postRepository, CurrentUserService currentUserService, Helper helper) {
        this.postRepository = postRepository;
        this.currentUserService = currentUserService;
        this.helper = helper;
    }

    public Result<PostEntity> getPost(Long postId) {
        return Result.fromOptional(postRepository.findById(postId), Status.NotFound);
    }

    public Result<PostEntity> updatePost(UpdatePostModel updatePostModel) {
        var post = postRepository.findById(updatePostModel.getPostId()).get();
        post.setDescription(updatePostModel.getDescription());
        post.setPhotos(updatePostModel.getPhotos().stream().map(helper::encodeStringToBase64).toList());
        return Result.of(postRepository.save(post));
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    public PostEntity createPost(CreatePostModel createPostModel) {
        var user = currentUserService.getCurrentUser().get();
        var post = new PostEntity(user, createPostModel.getPhotos().stream().map(helper::encodeStringToBase64).toList(), new Date(), createPostModel.getDescription());
        return postRepository.save(post);
    }
}
