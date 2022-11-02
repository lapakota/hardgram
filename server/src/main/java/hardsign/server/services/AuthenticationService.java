package hardsign.server.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final TokenProvider tokenProvider;

    public AuthenticationService(
            AuthenticationManager authenticationManager,
            UserService userService,
            TokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.tokenProvider = tokenProvider;
    }

    public String Auth(String nickname, String password) throws AuthenticationException {
        var authToken = new UsernamePasswordAuthenticationToken(nickname, password);
        authenticationManager.authenticate(authToken);

        var userDetails = userService.loadUserByUsername(nickname);
        return tokenProvider.generate(userDetails);
    }
}
