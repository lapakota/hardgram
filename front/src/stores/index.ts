import { createContext } from 'react';
import { UserInfoStore } from './userInfoStore';
import { PostsStore } from './postsStore';

export const RootStoreContext = createContext({
  userInfoStore: new UserInfoStore(),
  postsStore: new PostsStore()
});
