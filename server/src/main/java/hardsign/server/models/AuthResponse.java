package hardsign.server.models;

public class AuthResponse {
    private String token;

    private AuthResponse() {

    }

    public AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
