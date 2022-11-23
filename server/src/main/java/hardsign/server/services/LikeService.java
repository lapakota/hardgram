package hardsign.server.services;

import hardsign.server.entities.LikeEntity;
import hardsign.server.repositories.LikeRepository;
import hardsign.server.repositories.PostRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class LikeService {
    private final LikeRepository likeRepository;
    private final CurrentUserService currentUserService;
    private final PostRepository postRepository;

    @Inject
    public LikeService(LikeRepository likeRepository, CurrentUserService currentUserService, PostRepository postRepository) {
        this.likeRepository = likeRepository;
        this.currentUserService = currentUserService;
        this.postRepository = postRepository;
    }

    public void AddLike(Long postId){
        var user = currentUserService.getCurrentUser().get();
        var post = postRepository.findById(postId).get();
        var like = new LikeEntity(user, post);
        likeRepository.save(like);
    }

    public void DeleteLike(Long postId){
        var userId = currentUserService.getCurrentUser().get().getId();
        var like = likeRepository.findLike(userId, postId).get();
        likeRepository.deleteById(like.getId());
    }

    public Boolean IsLikedPostByUserFuckingMe(Long userId, Long postId){
        return !likeRepository.findLike(userId, postId).isEmpty();
    }

    public Number getCountLikes(Long postId){
        return likeRepository.findLikes(postId).size();
    }

}
