package hardsign.server.common.mapper;

import hardsign.server.entities.CommentEntity;
import hardsign.server.entities.PostEntity;
import hardsign.server.entities.UserEntity;
import hardsign.server.models.comment.CommentModel;
import hardsign.server.models.post.PostModel;
import hardsign.server.models.user.UserModel;
import hardsign.server.services.CurrentUserService;
import hardsign.server.utils.Helper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Mapper {
    private final CurrentUserService currentUserService;
    private final Helper helper;

    public Mapper(CurrentUserService currentUserService, Helper helper) {
        this.currentUserService = currentUserService;
        this.helper = helper;
    }

    public PostModel mapToModel(PostEntity post) {
        var photos = post.getPhotos().stream().map(helper::decodeBase64ToString).toList();
        return new PostModel(post.getId(), post.getUser().getId(), photos, post.getCreateTime(), post.getDescription(),
                isPostLikedByCurrentUser(post), getLikesCount(post));
    }

    public UserModel mapToModel(UserEntity user) {
        var posts = user.getPosts().stream().map(this::mapToModel).toList();
        return new UserModel(user.getId(), user.getNickname(), user.getFullName(), helper.decodeBase64ToString(user.getAvatar()), posts);
    }

    public CommentModel mapToModel(CommentEntity comment) {
        return new CommentModel(comment.getId(),
                comment.getPost().getId(),
                comment.getUser().getId(),
                comment.getText(),
                comment.getCreateTime());
    }

    private boolean isPostLikedByCurrentUser(PostEntity post) {
        var currentUser = currentUserService.getCurrentUser().get();
        var likes = post.getLikes();
        return likes.stream().anyMatch(like -> Objects.equals(like.getUser().getId(), currentUser.getId()));
    }

    private int getLikesCount(PostEntity post) {
        return post.getLikes().size();
    }
}