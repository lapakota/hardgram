import { PostModelCreate } from '../typescript/models/Post/PostModelCreate';
import { AxiosAuthConfig, BASE_ROUTE } from './utils';
import axios from 'axios';
import { PostModel } from '../typescript/models/Post/PostModel';

export const createPost = async (post: PostModelCreate, token: string): Promise<Response> =>
  axios.post(`${BASE_ROUTE}/post/create`, post, { ...AxiosAuthConfig(token) });

export const getPost = async (postId: string, token: string): Promise<PostModel> => {
  const params = new URLSearchParams([['postId', postId]]);
  return axios
    .get(`${BASE_ROUTE}/post/${postId}`, {
      ...AxiosAuthConfig(token),
      params
    })
    .then((x) => x.data);
};

export const getUserPosts = async (nickname: string, token: string): Promise<PostModel[]> => {
  const params = new URLSearchParams([['userName', nickname]]);
  return axios
    .get(`${BASE_ROUTE}/posts/${nickname}`, { ...AxiosAuthConfig(token), params })
    .then((x) => x.data);
};

export const deletePost = async (postId: string, token: string): Promise<Response> => {
  const params = new URLSearchParams([['id', postId]]);
  return axios.post(`${BASE_ROUTE}/post/delete/${postId}`, params, { ...AxiosAuthConfig(token) });
};
