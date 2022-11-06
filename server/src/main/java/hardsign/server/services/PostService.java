package hardsign.server.services;

import hardsign.server.entities.PostEntity;
import hardsign.server.models.post.CreatePostModel;
import hardsign.server.models.post.PostModel;
import hardsign.server.models.post.UpdatePostModel;
import hardsign.server.repositories.PostRepository;
import hardsign.server.repositories.UserRepository;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@Component
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Inject
    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public PostModel getPost(Long postId) {
        var post = postRepository.findById(postId).orElse(null);
        try {
            assert post != null;
            return mapToModel(post);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public PostModel updatePost(UpdatePostModel updatePostModel) {
        // Мне похуй абсолютно, через ебаный Query и Modify  нихуя не работает
        var post = postRepository.findById(updatePostModel.getPostId()).get();
        post.setDescription(updatePostModel.getDescription());
        post.setPhotos(updatePostModel.getPhotos());
        postRepository.save(post);
        return mapToModel(postRepository.findById(updatePostModel.getPostId()).get());
    }

    @Modifying
    @Query("update posts p set p.photos = ?2, p.description = ?3 where p.id = ?1")
    private void updatePostEntityById(@Param(value = "postId") long id, @Param(value = "photos") List<String> photos, @Param(value = "description") String description) {

    }

    public boolean deletePost(Long postId){
        try {
            postRepository.deleteById(postId);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public PostModel createPost(CreatePostModel createPostModel) {
        var user = userRepository.findById(createPostModel.getUserId()).get();
        var post = new PostEntity(user, createPostModel.getPhotos(), new Date(), createPostModel.getDescription());
        return mapToModel(postRepository.save(post));
    }

    private PostEntity mapToEntity(PostModel model) {
        var user = userRepository.findById(model.getUserId()).get();
        return new PostEntity(user, model.getPhotos(), model.getCreateTime(), model.getDescription());
    }

    private PostEntity mapToEntity(UpdatePostModel updatePostModel) {
        var postModel =  postRepository.findById(updatePostModel.getPostId()).get();
        var user = postModel.getUser();
        var createTime = postModel.getCreateTime();
        return new PostEntity(user, updatePostModel.getPhotos(), createTime, updatePostModel.getDescription());
    }

    private PostModel mapToModel(PostEntity postEntity) {
        return new PostModel(postEntity.getId(), postEntity.getUser().getId(), postEntity.getPhotos(), postEntity.getCreateTime(), postEntity.getDescription());
    }
}
