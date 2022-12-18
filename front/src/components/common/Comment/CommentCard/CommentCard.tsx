import * as React from 'react';
import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import CardContent from '@mui/material/CardContent';
import Avatar from '@mui/material/Avatar';
import Typography from '@mui/material/Typography';
import { observer } from 'mobx-react-lite';
import { useStores } from '../../../../hooks/useStores';
import moment from 'moment';
import { CommentModel } from '../../../../typescript/models/Comment/CommentModel';
import { UserInfoModel } from '../../../../typescript/models/User/UserInfoModel';
import { useEffect, useState } from 'react';
import { getUserInfo } from '../../../../api/userApi';
import { CommentMenu } from './CommentMenu';

interface CommentCardProps {
  comment: CommentModel;
  setComments: React.Dispatch<React.SetStateAction<CommentModel[] | undefined>>;
}

export const CommentCard = observer(({ comment, setComments }: CommentCardProps) => {
  const {
    userInfoStore: { token, userInfo }
  } = useStores();

  const [ownerInfo, setOwnerInfo] = useState<UserInfoModel>();

  useEffect(() => {
    if (!token) return;
    const fetchUserInfo = async () => {
      const info = await getUserInfo(comment.nickname, token);
      setOwnerInfo(info);
    };
    fetchUserInfo();
  }, []);

  const isOwnComment = comment.nickname === userInfo?.nickname;

  return (
    <div>
      <Card sx={{ width: 280 }} variant={'outlined'}>
        <CardHeader
          avatar={<Avatar src={ownerInfo?.avatar} sx={{ width: 32, height: 32 }} />}
          action={isOwnComment ? <CommentMenu comment={comment} setComments={setComments} /> : null}
          title={ownerInfo?.nickname || 'anonymous'}
          subheader={moment(comment.createTime).fromNow()}
        />
        <CardContent>
          <Typography
            variant="body2"
            color="text.secondary"
            sx={{
              wordBreak: 'break-all',
              overflow: 'hidden',
              height: '100%'
            }}>
            {comment.text}
          </Typography>
        </CardContent>
      </Card>
    </div>
  );
});
