import { createContext } from 'react';
import { UserInfoStore } from './userInfoStore';

export const RootStoreContext = createContext({
  userInfoStore: new UserInfoStore()
});
