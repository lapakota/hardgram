package hardsign.server.controllers;

import hardsign.server.models.post.CreatePostModel;
import hardsign.server.models.post.PostModel;
import hardsign.server.models.post.UpdatePostModel;
import hardsign.server.services.PostService;
import hardsign.server.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.List;

@Controller
public class PostController {
    private final PostService postService;
    private final UserService userService;

    @Inject
    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping(value = "/post/{postId}")
    @ResponseBody
    public PostModel get(Long postId) {
        return postService.getPost(postId);
    }

    @GetMapping(value = "/posts/{userId}")
    @ResponseBody
    public List<PostModel> getPostsByUserId(Long userId) {
        var postEntityList = userService.getUser(userId).get().getPosts();
        return postEntityList.stream().map(postEntity -> postService.getPost(postEntity.getId())).toList();
    }


    @PostMapping(path = "/post/create")
    @ResponseBody
    public PostModel create(@RequestBody CreatePostModel createPostModel) {
        try {
            return postService.createPost(createPostModel);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping(path = "/post/update")
    @ResponseBody
    public PostModel update(@RequestBody UpdatePostModel post) {
        try {
           return postService.updatePost(post);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping(path = "/post/delete/{id}")
    @ResponseBody
    public boolean delete(Long id) {
           return postService.deletePost(id);
    }
}