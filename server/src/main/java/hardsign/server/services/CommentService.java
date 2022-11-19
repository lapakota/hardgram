package hardsign.server.services;

import hardsign.server.common.Result;
import hardsign.server.common.Status;
import hardsign.server.entities.CommentEntity;
import hardsign.server.entities.PostEntity;
import hardsign.server.entities.UserEntity;
import hardsign.server.models.comment.AddCommentModel;
import hardsign.server.models.comment.UpdateCommentModel;
import hardsign.server.repositories.CommentRepository;
import hardsign.server.repositories.PostRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final CurrentUserService currentUserService;

    public CommentService(
            CommentRepository commentRepository,
            PostRepository postRepository,
            CurrentUserService currentUserService) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.currentUserService = currentUserService;
    }

    public Result<CommentEntity> add(AddCommentModel addCommentModel) {
        var optional = postRepository.findById(addCommentModel.getPostId());
        if (optional.isEmpty())
            return Result.fault(Status.NotFound);

        return currentUserService.getCurrentUser()
                .then(user -> createComment(user, optional.get(), addCommentModel))
                .then(commentRepository::save);
    }

    public Result<CommentEntity> update(UpdateCommentModel updateCommentModel) {
        return Result.fromOptional(commentRepository.findById(updateCommentModel.getPostId()), Status.NotFound)
                .then(x -> update(x, updateCommentModel));
    }

    public Result<String> delete(Long commentId) {
        return Result.of(() -> {
            commentRepository.deleteById(commentId);
            return "Success";
        }, Status.NotFound);
    }

    private CommentEntity createComment(UserEntity user, PostEntity post, AddCommentModel addCommentModel) {
        return new CommentEntity(user, post, addCommentModel.getText(), new Date());
    }

    private CommentEntity update(CommentEntity comment, UpdateCommentModel updateCommentModel) {
        comment.setText(updateCommentModel.getText());
        return comment;
    }
}
