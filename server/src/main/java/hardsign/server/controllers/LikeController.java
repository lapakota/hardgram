package hardsign.server.controllers;

import hardsign.server.services.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@CrossOrigin
public class LikeController {
    private final LikeService likeService;

    @Inject
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping(path = "/like/add/{postId}")
    public ResponseEntity add(Long postId) {
        try {
           likeService.addLike(postId);
           return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(path = "/like/delete/{postId}")
    public ResponseEntity delete(Long postId) {
        try {
            likeService.deleteLike(postId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


}
