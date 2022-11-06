package hardsign.server.controllers;

import hardsign.server.common.mapper.Mapper;
import hardsign.server.models.user.UserModel;
import hardsign.server.models.user.UserRegistrationModel;
import hardsign.server.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
public class RegistrationController {
    private final UserService userService;
    private final Mapper mapper;

    @Inject
    public RegistrationController(UserService userService, Mapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping("registration")
    public ResponseEntity<UserModel> registration(@RequestBody UserRegistrationModel userRegistrationModel) {
        try {
            var registeredUser = userService.addUser(userRegistrationModel);
            return ResponseEntity.ok(mapper.mapToModel(registeredUser));
        } catch (Exception e) {
            return ResponseEntity
                    .status(400)
                    .build();
        }
    }
}
