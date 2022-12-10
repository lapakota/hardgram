import axios from 'axios';
import { AxiosAuthConfig, BASE_ROUTE } from './utils';
import { PostModel } from '../typescript/models/Post/PostModel';

export const addLike = async (postId: number, token: string): Promise<PostModel> => {
  const params = new URLSearchParams([['postId', postId.toString()]]);
  return axios
    .post(`${BASE_ROUTE}/like/add/${postId}`, params, {
      ...AxiosAuthConfig(token)
    })
    .then((x) => x.data);
};

export const deleteLike = async (postId: number, token: string): Promise<PostModel> => {
  const params = new URLSearchParams([['postId', postId.toString()]]);
  return axios
    .post(`${BASE_ROUTE}/like/delete/${postId}`, params, {
      ...AxiosAuthConfig(token)
    })
    .then((x) => x.data);
};
