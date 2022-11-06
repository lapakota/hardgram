package hardsign.server.controllers;

import hardsign.server.common.mapper.Mapper;
import hardsign.server.models.user.UserModel;
import hardsign.server.services.CurrentUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
public class UserController {
    private final CurrentUserService  currentUserService;
    private final Mapper mapper;

    @Inject
    public UserController(CurrentUserService currentUserService, Mapper mapper) {
        this.currentUserService = currentUserService;
        this.mapper = mapper;
    }

    @GetMapping(value = "/me")
    public ResponseEntity<UserModel> me() {
        return currentUserService.getCurrentUser()
                .map(mapper::mapToModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}