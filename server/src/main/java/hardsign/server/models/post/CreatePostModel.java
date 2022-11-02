package hardsign.server.models.post;

import java.util.Date;
import java.util.List;

public class CreatePostModel {
    private final Long userId;
    private final List<String> photos;
    private final Date createTime;
    private final String description;

    public CreatePostModel(Long userId, List<String> photos, Date createTime, String description) {
        this.userId = userId;
        this.photos = photos;
        this.createTime = createTime;
        this.description = description;
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
