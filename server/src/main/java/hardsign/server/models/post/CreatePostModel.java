package hardsign.server.models.post;

import java.util.List;

public class CreatePostModel {
    private final Long userId;
    private final List<String> photos;
    private final String description;

    public CreatePostModel(Long userId, List<String> photos, String description) {
        this.userId = userId;
        this.photos = photos;
        this.description = description;
    }

    public Long getUserId() {
        return userId;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public String getDescription() {
        return description;
    }
}
