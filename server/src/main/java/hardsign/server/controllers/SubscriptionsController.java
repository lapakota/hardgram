package hardsign.server.controllers;

import hardsign.server.common.mapper.Mapper;
import hardsign.server.models.user.UserModel;
import hardsign.server.services.SubscriptionsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@CrossOrigin
public class SubscriptionsController {
    private final SubscriptionsService subscriptionsService;
    private final Mapper mapper;

    @Inject
    public SubscriptionsController(SubscriptionsService subscriptionsService, Mapper mapper) {
        this.subscriptionsService = subscriptionsService;
        this.mapper = mapper;
    }

    @GetMapping("/following/{nickname}")
    public ResponseEntity<List<UserModel>> getFollowing(@PathVariable String nickname){
      return subscriptionsService.getFollowing(nickname)
              .buildResponseEntity(x -> x.stream().map(mapper::mapToModel).toList());
    }

    @GetMapping("/followers/{nickname}")
    public ResponseEntity<List<UserModel>> getFollowers(@PathVariable String nickname){
        return subscriptionsService.getFollowers(nickname)
                .buildResponseEntity(x -> x.stream().map(mapper::mapToModel).toList());
    }

    @PostMapping(path = "/unfollow/{nickname}")
    public ResponseEntity<Void> unfollow(@PathVariable String nickname) {
        try {
            subscriptionsService.unfollow(nickname);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return  ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(path = "/follow/{nickname}")
    public ResponseEntity<Void> follow(@PathVariable String nickname) {
        return subscriptionsService.follow(nickname)
                .buildResponseEntity();
    }
}
