export interface PostModel {
  postId: number;
  userId: number;
  photos: string[];
  description: string;
  isLike: boolean;
  likesCount: number;
  createTime: string;
}
