package hardsign.server.services;

import hardsign.server.common.Result;
import hardsign.server.common.Status;
import hardsign.server.entities.PostEntity;
import hardsign.server.entities.UserEntity;
import hardsign.server.models.post.CreatePostModel;
import hardsign.server.models.post.UpdatePostModel;
import hardsign.server.repositories.PostRepository;
import hardsign.server.repositories.SubscriptionsRepository;
import hardsign.server.utils.Helper;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.*;


@Component
public class PostService {
    private final PostRepository postRepository;
    private final CurrentUserService currentUserService;
    private final Helper helper;
    private final SubscriptionsRepository subscriptionsRepository;

    @Inject
    public PostService(PostRepository postRepository, CurrentUserService currentUserService, Helper helper,  SubscriptionsRepository subscriptionsRepository) {
        this.postRepository = postRepository;
        this.currentUserService = currentUserService;
        this.helper = helper;
        this.subscriptionsRepository = subscriptionsRepository;
    }

    public List<PostEntity> getPosts(){
        var subs = subscriptionsRepository.findByUserId(currentUserService.getCurrentUser().get().getId());
        var subPosts = subs.stream().map(x -> x.getFollowing().getPosts()).toList();
        return subPosts.stream().flatMap(Collection::stream).toList();
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
        var photos = updatePostModel.getPhotos().stream().map(helper::encodeStringToBase64).toList();
        post.setPhotos(photos);
        return post;
    }

    private PostEntity createPostEntity(UserEntity user, CreatePostModel createPostModel) {
        var photos = createPostModel.getPhotos().stream().map(helper::encodeStringToBase64).toList();
        return new PostEntity(user, photos, new Date(), createPostModel.getDescription());
    }
}
