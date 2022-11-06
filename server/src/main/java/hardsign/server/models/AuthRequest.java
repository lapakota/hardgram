package hardsign.server.models;

public class AuthRequest {
    private String nickname;
    private String password;

    private AuthRequest() {

    }

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
