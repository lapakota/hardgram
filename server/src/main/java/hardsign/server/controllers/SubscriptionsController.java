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

    @GetMapping("/following/{userId}")
    public ResponseEntity<List<UserModel>> getFollowing(Long userId){
      try {
          var users = subscriptionsService.getFollowing(userId);
          return ResponseEntity.ok(users);
      } catch (Exception e){
          return ResponseEntity.internalServerError().build();
      }
    }

    @GetMapping("/followers/{userId}")
    public ResponseEntity<List<UserModel>> getFollowers(Long userId){
       try {
            var users = subscriptionsService.getFollowers(userId);
            return ResponseEntity.ok(users);
       } catch (Exception e){
           return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(path = "/following/delete/{userId}")
    public ResponseEntity unFollow(Long userId) {
        try {
            subscriptionsService.deleteFollow(userId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return  ResponseEntity.internalServerError().build();
        }

    }

    @PostMapping(path = "/following/add/{userId}")
    public ResponseEntity follow(Long userId) {
        try {
            subscriptionsService.addFollow(userId);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
}
