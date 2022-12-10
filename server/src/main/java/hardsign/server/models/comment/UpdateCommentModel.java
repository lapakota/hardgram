package hardsign.server.models.comment;

public class UpdateCommentModel {
    private Long postId;
    private String text;

    public String getText() {
        return text;
    }

    public Long getPostId() {
        return postId;
    }
}
