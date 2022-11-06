package hardsign.server.models;

public class UserModel {
    private String nickname;

    public UserModel(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
