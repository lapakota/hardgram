package hardsign.server.controllers;

import hardsign.server.entities.UserEntity;
import hardsign.server.services.UserService;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
public class UserController {
    private final UserService userService;

    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/me")
    public UserEntity me() {
        throw new NotYetImplementedException();
    }
}