package hardsign.server.controllers;

import hardsign.server.models.UserEntity;
import hardsign.server.services.UserService;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

@Controller
public class UserController {
    private final UserService userService;

    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/me")
    @ResponseBody
    public UserEntity me() {
        throw new NotYetImplementedException();
    }
}