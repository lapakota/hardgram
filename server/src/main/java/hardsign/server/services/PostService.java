package hardsign.server.services;

import hardsign.server.common.mapper.Mapper;
import hardsign.server.entities.PostEntity;
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
    public PostService(PostRepository postRepository, Mapper mapper, CurrentUserService currentUserService, Helper helper) {
        this.postRepository = postRepository;
        this.currentUserService = currentUserService;
        this.helper = helper;
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
