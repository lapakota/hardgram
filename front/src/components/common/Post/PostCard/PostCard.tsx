import * as React from 'react';
import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import CardMedia from '@mui/material/CardMedia';
import CardContent from '@mui/material/CardContent';
import CardActions from '@mui/material/CardActions';
import Avatar from '@mui/material/Avatar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import FavoriteIcon from '@mui/icons-material/Favorite';
import { observer } from 'mobx-react-lite';
import { useStores } from '../../../../hooks/useStores';
import { PostModel } from '../../../../typescript/models/Post/PostModel';
import { useEffect, useState } from 'react';
import { getUserInfo } from '../../../../api/userApi';
import { UserInfoModel } from '../../../../typescript/models/User/UserInfoModel';
import moment from 'moment';
import { addLike, deleteLike } from '../../../../api/likesApi';
import { PostMenu } from './PostMenu';
import { AddCommentOutlined } from '@mui/icons-material';
import { Stack } from '@mui/material';
import { useNavigate } from 'react-router-dom';

interface PostCardProps {
  post: PostModel;
  setPosts: React.Dispatch<React.SetStateAction<PostModel[] | undefined>>;
  onOpenEditorModal: (post?: PostModel) => void;
  onOpenViewerModal: (post?: PostModel) => void;
}

export const PostCard = observer(
  ({ post, setPosts, onOpenEditorModal, onOpenViewerModal }: PostCardProps) => {
    const {
      userInfoStore: { token, userInfo }
    } = useStores();
    const navigate = useNavigate();

    const [creatorInfo, setCreatorInfo] = useState<UserInfoModel>();
    const [isPostLiked, setIsPostLiked] = useState<boolean>(post.liked);
    const [postLikesCount, setPostLikesCount] = useState<number>(post.likesCount);

    useEffect(() => {
      if (creatorInfo || !token) return;

      const fetchUserInfo = async () => {
        const info = await getUserInfo(post.nickname, token);
        setCreatorInfo(info);
      };
      fetchUserInfo();
    }, []);

    const handleLikeBtnClick = () => {
      if (!token) return;

      const handleClick = async () => {
        if (isPostLiked) await deleteLike(post.postId, token);
        else await addLike(post.postId, token);
        setPostLikesCount((prev) => (isPostLiked ? --prev : ++prev));
        setIsPostLiked((prev) => !prev);
      };
      handleClick();
    };

    return (
      <Card sx={{ width: 500 }}>
        <CardHeader
          avatar={
            <Avatar
              src={creatorInfo?.avatar}
              sx={{ cursor: 'pointer' }}
              onClick={() => {
                navigate(`/user/profile/${creatorInfo?.nickname}`);
              }}
            />
          }
          action={
            creatorInfo?.nickname === userInfo?.nickname ? (
              <PostMenu post={post} setPosts={setPosts} onOpenEditorModal={onOpenEditorModal} />
            ) : null
          }
          title={creatorInfo?.nickname}
          subheader={moment(post.createTime).calendar()}
        />
        <CardMedia
          component={'img'}
          height={450}
          image={post.photos ? post.photos[0] : undefined}
          sx={{ cursor: 'pointer' }}
          onClick={() => onOpenViewerModal(post)}
        />
        {post.description && (
          <CardContent sx={{ height: 40 }}>
            <Typography
              variant="body2"
              color="text.secondary"
              sx={{
                wordBreak: 'break-all',
                overflow: 'hidden',
                height: '100%'
              }}>
              {post.description}
            </Typography>
          </CardContent>
        )}
        <CardActions disableSpacing>
          <Stack direction={'row'} spacing={2} justifyContent={'center'}>
            <span>
              <IconButton onClick={handleLikeBtnClick} sx={{ color: isPostLiked ? 'red' : '' }}>
                <FavoriteIcon />
              </IconButton>
              {postLikesCount}
            </span>
            <span>
              <IconButton onClick={() => onOpenViewerModal(post)}>
                <AddCommentOutlined />
              </IconButton>
              {post.commentsCount}
            </span>
          </Stack>
        </CardActions>
      </Card>
    );
  }
);
