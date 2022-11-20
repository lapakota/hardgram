import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import styles from './ProfilePage.module.scss';
import { UserInfo } from '../../../typescript/models/User/UserInfo';
import { getUserInfo } from '../../../api/userApi';
import { Spinner } from '../../common/Spinner/Spinner';
import { useStores } from '../../../hooks/useStores';
import { observer } from 'mobx-react-lite';
import { PostModel } from '../../../typescript/models/Post/PostModel';
import { getUserPosts } from '../../../api/postsApi';
import { Avatar, Button, IconButton, Stack } from '@mui/material';
import { Settings as SettingsIcon } from '@mui/icons-material';
import { useModal } from '../../../hooks/useModal';
import AddPostModal from './PostActionModal/AddPostModal';
import { Layout } from '../../common/Layout/Layout';

export const ProfilePage = observer((): React.ReactElement => {
  const navigate = useNavigate();
  const { nickname } = useParams<{ nickname: string }>();
  const { userInfoStore } = useStores();

  const [userInfo, setUserInfo] = useState<UserInfo | undefined>();
  const [posts, setPosts] = useState<PostModel[] | undefined>();

  const [isModalOpen, handleModalOpen, handleModalClose] = useModal();

  useEffect(() => {
    const fetchPosts = async () => {
      if (!nickname || !userInfoStore.token || userInfo || posts) return;

      const info = await getUserInfo(nickname, userInfoStore.token);
      const userPosts = await getUserPosts(info.id.toString(), userInfoStore.token);
      setUserInfo(info);
      setPosts(userPosts);
    };
    fetchPosts();
  }, []);

  return (
    <Layout className={styles.root}>
      {userInfo ? (
        <div className={styles.content}>
          <Stack
            direction="row"
            spacing={13}
            alignItems={'center'}
            className={styles.userInfoHeader}>
            <Avatar
              src={userInfo.avatar || '/static/blankAvatar.png'}
              sx={{ width: 150, height: 150 }}
              alt="user avatar"
            />
            <Stack direction="column" spacing={2}>
              <Stack direction="row" spacing={3}>
                <h2 className={styles.userNickname}>{userInfo.nickname}</h2>
                <Button variant={'contained'} color={'primary'} size={'small'} disabled>
                  Follow
                </Button>
                {userInfoStore.userInfo?.nickname === nickname && (
                  <IconButton onClick={() => navigate('/user/settings/')}>
                    <SettingsIcon />
                  </IconButton>
                )}
              </Stack>
              <span>{posts?.length || 0} posts</span>
              <span>{userInfo.fullName}</span>
            </Stack>
            <Button
              className={styles.addPostButton}
              variant={'outlined'}
              color={'primary'}
              size={'large'}
              onClick={handleModalOpen}>
              Add post
            </Button>
          </Stack>
          <div className={styles.posts}>
            <Stack direction={'row'} className={styles.postsPreviews}>
              {posts?.map((x) => (
                <>
                  <div key={x.postId}>
                    {x.postId} {x.description}
                  </div>
                </>
              ))}
            </Stack>
          </div>
          <AddPostModal isOpen={isModalOpen} handleClose={handleModalClose} />
        </div>
      ) : (
        <Spinner />
      )}
    </Layout>
  );
});
