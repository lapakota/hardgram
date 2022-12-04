package hardsign.server.services;

import hardsign.server.common.Result;
import hardsign.server.common.Status;
import hardsign.server.entities.LikeEntity;
import hardsign.server.repositories.LikeRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Optional;

@Component
public class LikeService {
    private final LikeRepository likeRepository;
    private final CurrentUserService currentUserService;
    private final PostService postService;

    @Inject
    public LikeService(LikeRepository likeRepository, CurrentUserService currentUserService, PostService postService) {
        this.likeRepository = likeRepository;
        this.currentUserService = currentUserService;
        this.postService = postService;
    }

    public Result<String> addLike(Long postId) {
        var postResult = postService.getPost(postId);
        if (postResult.isFailure())
            return Result.fault(Status.NotFound);

        return currentUserService.getCurrentUser()
                .then(user -> new LikeEntity(user, postResult.get()))
                .then(likeRepository::save, Status.ServerError)
                .then(x -> "success");
    }

    public Result<String> removeLike(Long postId) {
        return currentUserService.getCurrentUser()
                .then(user -> likeRepository.findLike(user.getId(), postId), Status.NotFound)
                .then(Optional::get)
                .then(like -> {
                    likeRepository.delete(like);
                    return "success";
                }, Status.ServerError);
    }
}