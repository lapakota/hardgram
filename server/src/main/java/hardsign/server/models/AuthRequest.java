package hardsign.server.models;

public class AuthRequest {
    private final String nickname;
    private final String password;

    public AuthRequest(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }
}
