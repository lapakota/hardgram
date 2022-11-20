package hardsign.server.models.post;

import java.util.List;
import java.util.Date;

public class PostModel {
    private final Long postId;
    private final Long userId;
    private final List<String> photos;
    private final Date createTime;
    private final String description;
    private final Boolean like;

    public PostModel(Long postId, Long userId, List<String> photos, Date createTime, String description, Boolean like) {
        this.postId = postId;
        this.userId = userId;
        this.photos = photos;
        this.createTime = createTime;
        this.description = description;
        this.like = like;
    }

    public Long getPostId() {
        return postId;
    }

    public Long getUserId() {
        return userId;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getLike(){ return like; }
}
