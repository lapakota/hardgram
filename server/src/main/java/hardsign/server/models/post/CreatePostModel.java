package hardsign.server.models.post;

import java.util.List;

public class CreatePostModel {
    private final List<String> photos;
    private final String description;

    public CreatePostModel(List<String> photos, String description) {
        this.photos = photos;
        this.description = description;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public String getDescription() {
        return description;
    }
}
