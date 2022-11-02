package hardsign.server.models.post;

import java.util.List;
import java.util.Date;

public class PostModel {
    private final Long  PostId;
    private final Long userId;
    private final List<String> photos;
    private final Date createTime;
    private final String description;


    public PostModel(Long postId, Long userId, List<String> photos, Date createTime, String description){this.PostId = postId;
        this.userId = userId;
        this.photos = photos;
        this.createTime = createTime;
        this.description = description;
    }

    public Long getPostId() {
        return PostId;
    }
    public PostModel getPost() { return this; }

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
