package hardsign.server.controllers;

import hardsign.server.models.UserModel;
import hardsign.server.models.UserRegistrationModel;
import hardsign.server.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
public class RegistrationController {
    private final UserService userService;

    @Inject
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("registration")
    public ResponseEntity<UserModel> registration(@RequestBody UserRegistrationModel userRegistrationModel) {
        try {
            var registeredUser = userService.addUser(userRegistrationModel);
            return ResponseEntity.ok(new UserModel(registeredUser.getNickname()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(400)
                    .build();
        }
    }
}
