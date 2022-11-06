package hardsign.server.controllers;

import hardsign.server.common.mapper.Mapper;
import hardsign.server.models.post.CreatePostModel;
import hardsign.server.models.post.PostModel;
import hardsign.server.models.post.UpdatePostModel;
import hardsign.server.services.PostService;
import hardsign.server.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@CrossOrigin
public class PostController {
    private final PostService postService;
    private final UserService userService;
    private final Mapper mapper;

    @Inject
    public PostController(PostService postService, UserService userService, Mapper mapper) {
        this.postService = postService;
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping(value = "/post/{postId}")
    public ResponseEntity<PostModel> get(Long postId) {
        var post = postService.getPost(postId);
        var postModel = mapper.mapToModel(post);
        return ResponseEntity.ok(postModel);
    }

    @GetMapping(value = "/posts/{userId}")
    public ResponseEntity<List<PostModel>> getPostsByUserId(Long userId) {
        var postEntityList = userService.getUser(userId).get().getPosts();
        var postModels = postEntityList.stream().map(mapper::mapToModel).toList();
        return ResponseEntity.ok(postModels);
    }

    @PostMapping(path = "/post/create")
    public ResponseEntity<PostModel> create(@RequestBody CreatePostModel createPostModel) {
        try {
            var post = postService.createPost(createPostModel);
            var postModel = mapper.mapToModel(post);
            return ResponseEntity.ok(postModel);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(path = "/post/update")
    public ResponseEntity<PostModel> update(@RequestBody UpdatePostModel post) {
        try {
            var updatedPost = postService.updatePost(post);
            var postModel = mapper.mapToModel(updatedPost);
            return ResponseEntity.ok(postModel);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(path = "/post/delete/{id}")
    public ResponseEntity delete(Long id) {
        try {
            postService.deletePost(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .build();
        }
    }
}