import React, { useEffect, useState } from 'react';
import { PostModel } from '../../../typescript/models/Post/PostModel';
import { getPostsFeed } from '../../../api/postsApi';
import { observer } from 'mobx-react-lite';
import { useStores } from '../../../hooks/useStores';
import { PostCard } from '../../common/Post/PostCard/PostCard';
import { Stack } from '@mui/material';
import { Layout } from '../../common/Layout/Layout';
import { PostViewerModal } from '../ProfilePage/PostViewerModal/PostViewerModal';
import { useModal } from '../../../hooks/useModal';
import styles from './FeedPage.module.scss';
import { Spinner } from '../../common/Spinner/Spinner';

export const FeedPage = observer(() => {
  const {
    userInfoStore: { token }
  } = useStores();

  const [posts, setPosts] = useState<PostModel[]>();

  const [activePost, setActivePost] = useState<PostModel | undefined>();
  const [isPostModalOpen, handlePostModalOpen, handlePostModalClose] = useModal();

  useEffect(() => {
    if (!token || posts) return;
    const fetchFeed = async () => {
      const feed = await getPostsFeed(token);
      setPosts(feed);
    };
    fetchFeed();
  }, []);

  const onOpenModal = (post?: PostModel) => {
    post && setActivePost(post);
    handlePostModalOpen();
  };

  return (
    <Layout className={styles.root}>
      <>
        <Stack direction={'column'} spacing={2}>
          {posts ? (
            posts.map((post) => (
              <PostCard
                key={post.postId}
                post={post}
                setPosts={setPosts}
                onOpenEditorModal={() => console.log('Feed does not contains my posts')}
                onOpenViewerModal={onOpenModal}
              />
            ))
          ) : (
            <Spinner />
          )}
        </Stack>
        {isPostModalOpen && activePost && (
          <PostViewerModal
            isOpen={isPostModalOpen}
            activePost={activePost}
            handleClose={() => handlePostModalClose()}
          />
        )}
      </>
    </Layout>
  );
});
