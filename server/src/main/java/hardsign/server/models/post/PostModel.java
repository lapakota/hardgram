package hardsign.server.models.post;

import java.util.List;
import java.util.Date;

public class PostModel {
    private final Long postId;
    private final String nickname;
    private final List<String> photos;
    private final Date createTime;
    private final String description;
    private final boolean isLiked;
    private final int likesCount;

    public PostModel(Long postId, String nickname, List<String> photos, Date createTime, String description, boolean isLiked, int countLikes) {
        this.postId = postId;
        this.nickname = nickname;
        this.photos = photos;
        this.createTime = createTime;
        this.description = description;
        this.isLiked = isLiked;
        this.likesCount = countLikes;
    }

    public Long getPostId() {
        return postId;
    }

    public String getNickname() {
        return nickname;
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

    public Number getLikesCount() {
        return likesCount;
    }

    public boolean isLiked() {
        return isLiked;
    }
}
