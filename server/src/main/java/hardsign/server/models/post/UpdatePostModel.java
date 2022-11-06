package hardsign.server.models.post;
import java.util.List;

public class UpdatePostModel {
    private final Long postId;
    private final List<String> photos;
    private final String description;

    public UpdatePostModel(Long postId, List<String> photos, String description) {
        this.postId = postId;
        this.photos = photos;
        this.description = description;
    }

    public Long getPostId() {
        return postId;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public String getDescription() {
        return description;
    }
}
