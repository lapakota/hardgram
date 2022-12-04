package hardsign.server.services;

import hardsign.server.common.mapper.Mapper;
import hardsign.server.entities.PostEntity;
import hardsign.server.models.post.CreatePostModel;
import hardsign.server.models.post.UpdatePostModel;
import hardsign.server.repositories.PostRepository;
import hardsign.server.repositories.SubscriptionsRepository;
import hardsign.server.utils.Helper;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Component
public class PostService {
    private final PostRepository postRepository;
    private final CurrentUserService currentUserService;
    private final Helper helper;
    private final SubscriptionsRepository subscriptionsRepository;

    @Inject
    public PostService(PostRepository postRepository, Mapper mapper, CurrentUserService currentUserService, Helper helper, SubscriptionsRepository subscriptionsRepository) {
        this.postRepository = postRepository;
        this.currentUserService = currentUserService;
        this.helper = helper;
        this.subscriptionsRepository = subscriptionsRepository;
    }

    public List<PostEntity> getPosts(){
        var subs = subscriptionsRepository.findByUserId(currentUserService.getCurrentUser().get().getId());
        var subPosts = subs.stream().map(x -> x.getFollowing().getPosts()).toList();
        var result = new ArrayList<PostEntity>();
        subPosts.forEach(result::addAll);
        return result;
    }



    public PostEntity getPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(RuntimeException::new);
    }

    public PostEntity updatePost(UpdatePostModel updatePostModel) {
        var post = postRepository.findById(updatePostModel.getPostId()).get();
        post.setDescription(updatePostModel.getDescription());
        post.setPhotos(updatePostModel.getPhotos().stream().map(helper::encodeStringToBase64).toList());
        return postRepository.save(post);
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
