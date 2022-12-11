package hardsign.server.controllers;

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

    @Inject
    public SubscriptionsController(SubscriptionsService subscriptionsService) {
        this.subscriptionsService = subscriptionsService;
    }

    @GetMapping("/following/{nickname}")
    public ResponseEntity<List<UserModel>> getFollowing(@PathVariable String nickname){
      try {
          var users = subscriptionsService.getFollowing(nickname);
          return ResponseEntity.ok(users);
      } catch (Exception e){
          return ResponseEntity.internalServerError().build();
      }
    }

    @GetMapping("/followers/{nickname}")
    public ResponseEntity<List<UserModel>> getFollowers(@PathVariable String nickname){
       try {
            var users = subscriptionsService.getFollowers(nickname);
            return ResponseEntity.ok(users);
       } catch (Exception e){
           return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(path = "/unfollow/{nickname}")
    public ResponseEntity unFollow(@PathVariable String nickname) {
        try {
            subscriptionsService.deleteFollow(nickname);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return  ResponseEntity.internalServerError().build();
        }

    }

    @PostMapping(path = "/follow/{nickname}")
    public ResponseEntity follow(@PathVariable String nickname) {
        try {
            subscriptionsService.addFollow(nickname);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
}
