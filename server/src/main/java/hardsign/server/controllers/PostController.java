package hardsign.server.controllers;

import hardsign.server.models.post.CreatePostModel;
import hardsign.server.models.post.PostModel;
import hardsign.server.services.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

@Controller
public class PostController {
    private final PostService postService;
    private RuntimeException runtimeException;

    @Inject
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(value = "/posts/{id}")
    @ResponseBody
    public PostModel get(Long postId) {
        return postService.GetPost(postId);
    }

    @PostMapping(path = "/posts/create")
    @ResponseBody
    public PostModel create(@RequestBody CreatePostModel createPostModel) {
        try {
            return postService.CreatePost(createPostModel);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @PostMapping(path = "/posts/update")
    @ResponseBody
    public PostModel update(@RequestBody PostModel post) {
        try {
            postService.SavePost(post);
        } catch (Exception e) {
            throw runtimeException;
        }
        return postService.GetPost(post.getPostId());
    }

}
