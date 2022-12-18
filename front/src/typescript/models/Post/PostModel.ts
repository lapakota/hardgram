export interface PostModel {
  postId: number;
  nickname: string;
  photos: string[];
  description: string;
  liked: boolean;
  likesCount: number;
  commentsCount: number;
  createTime: string;
}
