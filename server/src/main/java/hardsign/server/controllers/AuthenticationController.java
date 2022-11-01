package hardsign.server.controllers;

import hardsign.server.models.AuthRequest;
import hardsign.server.models.AuthResponse;
import hardsign.server.services.TokenProvider;
import hardsign.server.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final TokenProvider tokenProvider;

    public AuthenticationController(
            AuthenticationManager authenticationManager,
            UserService userService,
            TokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("auth/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest){
        var nickname = authRequest.getNickname();
        var password = authRequest.getPassword();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(nickname, password));
        } catch (Exception e) {
            return ResponseEntity
                    .status(401)
                    .build();
        }

        var userDetails = userService.loadUserByUsername(authRequest.getNickname());
        var token = tokenProvider.generate(userDetails);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}


