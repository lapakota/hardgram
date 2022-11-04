package hardsign.server.services;

import hardsign.server.common.Result;
import hardsign.server.common.Status;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

    public Result<String> Auth(String nickname, String password) {
        if (nickname == null || password == null)
            return Result.fault(Status.IncorrectArguments);

        var authToken = new UsernamePasswordAuthenticationToken(nickname, password);

        return Result
                .of(() -> authenticationManager.authenticate(authToken), Status.AuthError)
                .then(() -> userService.loadUserByUsername(nickname), Status.ServerError)
                .then(tokenProvider::generate);
    }
}