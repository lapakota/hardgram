package hardsign.server.controllers;

import hardsign.server.common.mapper.Mapper;
import hardsign.server.models.comment.AddCommentModel;
import hardsign.server.models.comment.CommentModel;
import hardsign.server.models.comment.UpdateCommentModel;
import hardsign.server.services.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentsController {
    private final CommentService commentService;
    private final Mapper mapper;

    public CommentsController(CommentService commentService, Mapper mapper) {
        this.commentService = commentService;
        this.mapper = mapper;
    }

    @PostMapping("comment")
    public ResponseEntity<CommentModel> add(@RequestBody AddCommentModel addCommentModel) {
        return commentService.add(addCommentModel)
                .buildResponseEntity(mapper::mapToModel);
    }

    @PostMapping("comment/update")
    public ResponseEntity<CommentModel> update(@RequestBody UpdateCommentModel updateCommentModel) {
        return commentService.update(updateCommentModel)
                .buildResponseEntity(mapper::mapToModel);
    }

    @GetMapping("comments/{postId}")
    public ResponseEntity<List<CommentModel>> get(@PathVariable Long postId) {
        return commentService.getAllByPost(postId)
                .buildResponseEntity(mapper::mapToModel);
    }

    @DeleteMapping("comment/{commentId}")
    public ResponseEntity<String> delete(@PathVariable Long commentId) {
        return commentService.delete(commentId)
                .buildResponseEntity();
    }
}