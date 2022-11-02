package hardsign.server.controllers;

import hardsign.server.models.PostEntity;
import hardsign.server.services.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.Optional;

@Controller
public class PostController {
    private final PostService postService;

    @Inject
    public PostController(PostService postService) { this.postService = postService;}

    @GetMapping(value = "/posts/{id}")
    @ResponseBody
    public Optional<PostEntity> get(Long postId) {
            return postService.GetPost(postId);
    }

    @PostMapping(path = "/posts/create")
    @ResponseBody
    public Optional<PostEntity> create(PostEntity newPost){
        try {
            postService.SavePost(newPost);
            return postService.GetPost(newPost.Id);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @PostMapping(path = "/posts/update")
    @ResponseBody
    public Optional<PostEntity> update (PostEntity postEntity){
        postService.SavePost(postEntity);
        return postService.GetPost(postEntity.Id);
    }

}
