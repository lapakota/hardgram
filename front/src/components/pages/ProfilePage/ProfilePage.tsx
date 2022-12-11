import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import styles from './ProfilePage.module.scss';
import { UserInfoModel } from '../../../typescript/models/User/UserInfoModel';
import { getUserInfo } from '../../../api/userApi';
import { Spinner } from '../../common/Spinner/Spinner';
import { useStores } from '../../../hooks/useStores';
import { observer } from 'mobx-react-lite';
import { PostModel } from '../../../typescript/models/Post/PostModel';
import { getUserPosts } from '../../../api/postsApi';
import { Avatar, Button, Stack } from '@mui/material';
import { useModal } from '../../../hooks/useModal';
import { AddPostModal } from './AddPostModal/AddPostModal';
import { Layout } from '../../common/Layout/Layout';
import { PostCard } from '../../common/PostCard/PostCard';

export const ProfilePage = observer((): React.ReactElement => {
  const { nickname } = useParams<{ nickname: string }>();
  const { userInfoStore } = useStores();

  const [userInfo, setUserInfo] = useState<UserInfoModel | undefined>();
  const [posts, setPosts] = useState<PostModel[] | undefined>();

  const [isModalOpen, handleModalOpen, handleModalClose] = useModal();

  const isOwnPage = userInfoStore.userInfo?.nickname === nickname;

  useEffect(() => {
    const fetchProfileInfo = async () => {
      if (!nickname || !userInfoStore.token) return;

      const info = await getUserInfo(nickname, userInfoStore.token);
      const userPosts = await getUserPosts(info.nickname, userInfoStore.token);
      setUserInfo(info);
      setPosts(userPosts);
    };
    fetchProfileInfo();
  }, [nickname]);

  return (
    <Layout className={styles.root}>
      {userInfo ? (
        <div className={styles.content}>
          <Stack
            direction="row"
            spacing={13}
            alignItems={'center'}
            className={styles.userInfoHeader}>
            <Avatar src={userInfo.avatar} sx={{ width: 150, height: 150 }} alt="user avatar" />
            <Stack direction="column" spacing={2}>
              <Stack direction="row" spacing={3}>
                <h2 className={styles.userNickname}>{userInfo.nickname}</h2>
                <Button variant={'contained'} color={'primary'} size={'small'} disabled>
                  Follow
                </Button>
              </Stack>
              <span>{posts?.length || 0} posts</span>
              <span>{userInfo.fullName}</span>
            </Stack>
            {isOwnPage && (
              <Button
                className={styles.addPostButton}
                variant={'outlined'}
                color={'primary'}
                size={'large'}
                onClick={handleModalOpen}>
                Add post
              </Button>
            )}
          </Stack>
          <div className={styles.posts}>
            <Stack direction={'column'} spacing={2}>
              {posts?.map((post) => (
                <PostCard key={post.postId} post={post} setPosts={setPosts} />
              ))}
            </Stack>
          </div>
          <AddPostModal isOpen={isModalOpen} handleClose={handleModalClose} setPosts={setPosts} />
        </div>
      ) : (
        <Spinner />
      )}
    </Layout>
  );
});
