import React, { useEffect, useState } from 'react';
import { PostModel } from '../../../typescript/models/Post/PostModel';
import { getPostsFeed } from '../../../api/postsApi';
import { observer } from 'mobx-react-lite';
import { useStores } from '../../../hooks/useStores';
import { PostCard } from '../../common/Post/PostCard/PostCard';
import { Stack } from '@mui/material';

export const MainPage = observer(() => {
  const [posts, setPosts] = useState<PostModel[]>();
  const {
    userInfoStore: { token }
  } = useStores();
  useEffect(() => {
    if (!token || posts) return;
    const fetchFeed = async () => {
      const feed = await getPostsFeed(token);
      setPosts(feed);
    };
    fetchFeed();
  }, []);

  // TODO Сделать открытие модалки просмотра поста
  // TODO Сверстать нормально
  return (
    <Stack direction={'column'} spacing={2}>
      {posts?.map((post) => (
        <PostCard
          key={post.postId}
          post={post}
          setPosts={setPosts}
          onOpenEditorModal={() => console.log('Feed does not contains my posts')}
          onOpenViewerModal={() => console.log('openViewerModal')}
        />
      ))}
    </Stack>
  );
});
