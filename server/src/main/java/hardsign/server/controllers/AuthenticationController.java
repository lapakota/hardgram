package hardsign.server.controllers;

import hardsign.server.common.Mapper;
import hardsign.server.models.AuthRequest;
import hardsign.server.models.AuthResponse;
import hardsign.server.services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final Mapper mapper;

    public AuthenticationController(AuthenticationService authenticationService, Mapper mapper) {
        this.authenticationService = authenticationService;
        this.mapper = mapper;
    }

    @PostMapping("auth/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest){
        var nickname = authRequest.getNickname();
        var password = authRequest.getPassword();
        return authenticationService.Auth(nickname, password)
                .then(AuthResponse::new)
                .mapStatus(mapper::map);
    }
}