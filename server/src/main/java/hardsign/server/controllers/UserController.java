package hardsign.server.controllers;

import hardsign.server.common.mapper.Mapper;
import hardsign.server.models.user.UserModel;
import hardsign.server.models.user.UserUpdateModel;
import hardsign.server.services.CurrentUserService;
import hardsign.server.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
@CrossOrigin
public class UserController {
    private final CurrentUserService  currentUserService;
    private final UserService userService;
    private final Mapper mapper;

    @Inject
    public UserController(CurrentUserService currentUserService, UserService userService, Mapper mapper) {
        this.currentUserService = currentUserService;
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping(value = "/me")
    public ResponseEntity<UserModel> me() {
        return currentUserService.getCurrentUser()
                .map(mapper::mapToModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/user/{nickname}")
    public ResponseEntity<UserModel> getByNickname(String nickname) {
        return userService.getUser(nickname)
                .map(mapper::mapToModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/update")
    public ResponseEntity<UserModel> update(@RequestBody UserUpdateModel userUpdateModel) {
        return userService.updateUser(userUpdateModel)
                .map(mapper::mapToModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }
}