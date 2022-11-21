import { PostModel } from '../Post/PostModel';

export interface UserInfoModel {
  id: number;
  nickname: string;
  fullName?: string;
  avatar?: string;
  posts: PostModel[];
}
