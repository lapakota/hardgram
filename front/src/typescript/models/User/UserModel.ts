import { PostModel } from '../Post/PostModel';

export interface UserModel {
  id: number;
  nickname: string;
  fullName?: string;
  avatar?: string;
  posts: PostModel[];
}
