import axios from 'axios';
import { AxiosAuthConfig, BASE_ROUTE } from './utils';
import { PostModel } from '../typescript/models/Post/PostModel';

export const addLike = async (postId: number, token: string): Promise<PostModel> =>
  axios
    .post(
      `${BASE_ROUTE}/like/add/${postId}`,
      {},
      {
        ...AxiosAuthConfig(token)
      }
    )
    .then((x) => x.data);

export const deleteLike = async (postId: number, token: string): Promise<PostModel> =>
  axios
    .post(
      `${BASE_ROUTE}/like/delete/${postId}`,
      {},
      {
        ...AxiosAuthConfig(token)
      }
    )
    .then((x) => x.data);
