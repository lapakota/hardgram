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

interface PostCardProps {
  description?: string;
  photos?: string[];
}

export const PostCard = observer(({ description, photos }: PostCardProps) => {
  const {
    userInfoStore: { userInfo }
  } = useStores();

  return (
    <Card sx={{ width: 500, minHeight: 650 }}>
      <CardHeader
        avatar={<Avatar src={userInfo?.avatar || userInfo?.nickname} />}
        action={
          <IconButton aria-label="settings">
            <MoreVertIcon />
          </IconButton>
        }
        title="Shrimp and Chorizo Paella"
        subheader="September 14, 2016"
      />
      <CardMedia component={'img'} height={450} image={'/testImage.png'} />
      <CardContent>
        <Typography variant="body2" color="text.secondary">
          {description}
        </Typography>
      </CardContent>
      <CardActions disableSpacing>
        <IconButton aria-label="add to favorites">
          <FavoriteIcon />
        </IconButton>
      </CardActions>
    </Card>
  );
});
