package hardsign.server.models.comment;

import java.util.Date;

public class CommentModel {
    private Long commentId;
    private Long postId;
    private String nickname;
    private String text;
    private Date createTime;

    public CommentModel(Long commentId, Long postId, String nickname, String text, Date createTime) {
        this.commentId = commentId;
        this.postId = postId;
        this.nickname = nickname;
        this.text = text;
        this.createTime = createTime;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
