import { PostModel } from '../Post/PostModel';

export interface UserInfo {
  id: number;
  nickname: string;
  fullName: string;
  avatar: string;
  posts: PostModel[];
}
