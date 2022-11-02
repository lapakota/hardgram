package hardsign.server.models.post;

import java.util.Date;
import java.util.List;

public class UpdatePostModel {
    private final Long postId;
    private final Long userId;
    private final List<String> photos;
    private final Date createTime;
    private final String description;

    public UpdatePostModel(Long postId, Long userId, List<String> photos, Date createTime, String description) {
        this.postId = postId;
        this.userId = userId;
        this.photos = photos;
        this.createTime = createTime;
        this.description = description;
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
}
