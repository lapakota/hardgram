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
import MoreVertIcon from '@mui/icons-material/MoreVert';
import { observer } from 'mobx-react-lite';
import { useStores } from '../../../hooks/useStores';
import { PostModel } from '../../../typescript/models/Post/PostModel';
import { useEffect, useState } from 'react';
import { getUserInfo } from '../../../api/userApi';
import { UserInfoModel } from '../../../typescript/models/User/UserInfoModel';
import moment from 'moment';
import { addLike, deleteLike } from '../../../api/likesApi';

export const PostCard = observer(
  ({
    post: { photos, description, createTime, liked, likesCount, postId, nickname }
  }: {
    post: PostModel;
  }) => {
    const {
      userInfoStore: { token }
    } = useStores();

    const [creatorInfo, setCreatorInfo] = useState<UserInfoModel>();
    const [isPostLiked, setIsPostLiked] = useState<boolean>(liked);
    const [postLikesCount, setPostLikesCount] = useState<number>(likesCount);

    useEffect(() => {
      if (creatorInfo || !token) return;

      const fetchUserInfo = async () => {
        const info = await getUserInfo(nickname, token);
        setCreatorInfo(info);
      };
      fetchUserInfo();
    }, []);

    const handleLikeBtnClick = () => {
      if (!token) return;

      const handleClick = async () => {
        if (isPostLiked) await deleteLike(postId, token);
        else await addLike(postId, token);
        setPostLikesCount((prev) => (isPostLiked ? --prev : ++prev));
        setIsPostLiked((prev) => !prev);
      };
      handleClick();
    };

    return (
      <Card sx={{ width: 500 }}>
        <CardHeader
          avatar={<Avatar src={creatorInfo?.avatar} />}
          action={
            <IconButton aria-label="settings">
              <MoreVertIcon />
            </IconButton>
          }
          title={creatorInfo?.nickname}
          subheader={moment(createTime).calendar()}
        />
        <CardMedia component={'img'} height={450} image={photos ? photos[0] : undefined} />
        {description && (
          <CardContent sx={{ height: 60 }}>
            <Typography variant="body2" color="text.secondary" style={{ wordBreak: 'break-all' }}>
              {description}
            </Typography>
          </CardContent>
        )}
        <CardActions disableSpacing>
          <IconButton onClick={handleLikeBtnClick} sx={{ color: isPostLiked ? 'red' : '' }}>
            <FavoriteIcon />
          </IconButton>
          {postLikesCount}
        </CardActions>
      </Card>
    );
  }
);
