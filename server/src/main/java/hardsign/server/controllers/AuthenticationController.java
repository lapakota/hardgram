package hardsign.server.controllers;

import hardsign.server.models.AuthRequest;
import hardsign.server.models.AuthResponse;
import hardsign.server.services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("auth/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest){
        var nickname = authRequest.getNickname();
        var password = authRequest.getPassword();
        return authenticationService.Auth(nickname, password)
                .buildResponseEntity(AuthResponse::new);
    }
}