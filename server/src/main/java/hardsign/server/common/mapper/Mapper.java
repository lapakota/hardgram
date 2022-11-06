package hardsign.server.common.mapper;

import hardsign.server.entities.PostEntity;
import hardsign.server.entities.UserEntity;
import hardsign.server.models.post.PostModel;
import hardsign.server.models.user.UserModel;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public PostModel mapToModel(PostEntity post) {
        return new PostModel(post.getId(),
                post.getUser().getId(),
                post.getPhotos(),
                post.getCreateTime(),
                post.getDescription());
    }

    public UserModel mapToModel(UserEntity user) {
        var posts = user.getPosts().stream().map(this::mapToModel).toList();
        return new UserModel(user.getId(), user.getNickname(), user.getFullName(), user.getAvatar(), posts);
    }
}