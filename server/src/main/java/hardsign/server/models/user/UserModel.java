package hardsign.server.models.user;

import hardsign.server.models.post.PostModel;

import java.util.List;

public class UserModel {
    private Long id;
    private String nickname;
    private String fullName;
    private String avatar;
    private List<PostModel> posts;

    public UserModel(Long id, String nickname, String fullName, String avatar, List<PostModel> photos) {
        this.id = id;
        this.nickname = nickname;
        this.fullName = fullName;
        this.avatar = avatar;
        this.posts = photos;
    }

    public Long getId() {
        return id;
    }

    public void setUserId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<PostModel> getPosts() {
        return posts;
    }

    public void setPosts(List<PostModel> posts) {
        this.posts = posts;
    }
}
