package hardsign.server.controllers;

import hardsign.server.models.UserModel;
import hardsign.server.models.UserRegistrationModel;
import hardsign.server.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

@Controller
public class RegistrationController {
    private final UserService userService;

    @Inject
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("registration")
    @ResponseBody
    public UserModel addUser(@RequestBody UserRegistrationModel userRegistrationModel) {
        try {
            var registeredUser = userService.addUser(userRegistrationModel);
            return new UserModel(registeredUser.nickname);

        } catch (Exception ex) {
            return null;
        }
    }
}
