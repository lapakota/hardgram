package hardsign.server.models.comment;

public class AddCommentModel {
    private Long postId;
    private String text;

    public Long getPostId() {
        return postId;
    }

    public String getText() {
        return text;
    }
}
