package hardsign.server.controllers;

import hardsign.server.services.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
@CrossOrigin
public class LikeController {
    private final LikeService likeService;

    @Inject
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping(path = "/like/{postId}")
    public ResponseEntity<String> add(@PathVariable Long postId) {
        return likeService.addLike(postId)
                .buildResponseEntity(x -> x);
    }

    @DeleteMapping(path = "/like/{postId}")
    public ResponseEntity<String> remove(@PathVariable Long postId) {
        return likeService.removeLike(postId)
                .buildResponseEntity(x -> x);
    }
}