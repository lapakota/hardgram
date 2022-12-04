package hardsign.server.controllers;

import hardsign.server.common.mapper.Mapper;
import hardsign.server.entities.UserEntity;
import hardsign.server.entities.PostEntity;
import hardsign.server.models.post.CreatePostModel;
import hardsign.server.models.post.PostModel;
import hardsign.server.models.post.UpdatePostModel;
import hardsign.server.services.PostService;
import hardsign.server.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Comparator;
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
        return postService.getPost(postId)
                .buildResponseEntity(mapper::mapToModel);
    }

    @GetMapping(value = "/posts/{userName}")
    public ResponseEntity<List<PostModel>> getPostsByNickname(String userName) {
        var postEntityList = userService.getUser(userName).get().getPosts();
        var postModels = postEntityList.stream().sorted((x,y)-> y.getCreateTime().compareTo(x.getCreateTime())).map(mapper::mapToModel).toList();
        return ResponseEntity.ok(postModels);
    }

    @PostMapping(path = "/post/create")
    public ResponseEntity<PostModel> create(@RequestBody CreatePostModel createPostModel) {
        return postService.createPost(createPostModel)
                .buildResponseEntity(mapper::mapToModel);
    }

    @PostMapping(path = "/post/update")
    public ResponseEntity<PostModel> update(@RequestBody UpdatePostModel updatePostModel) {
        return postService.updatePost(updatePostModel)
                .buildResponseEntity(mapper::mapToModel);
    }

    @DeleteMapping(path = "/post/{id}")
    public ResponseEntity<String> delete(Long id) {
        return postService.deletePost(id)
                .buildResponseEntity(x -> x);
    }
}