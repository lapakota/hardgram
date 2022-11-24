package hardsign.server.controllers;

import hardsign.server.models.AuthRequest;
import hardsign.server.models.AuthResponse;
import hardsign.server.services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
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

        try {
            var token = authenticationService.auth(nickname, password);
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (AuthenticationException e) {
            return ResponseEntity
                    .status(401)
                    .build();
        } catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .build();
        }
    }
}


