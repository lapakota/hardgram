import { PostModelCreate } from '../typescript/models/Post/PostModelCreate';
import { AxiosAuthConfig, BASE_ROUTE } from './utils';
import axios from 'axios';
import { PostModel } from '../typescript/models/Post/PostModel';
import { UserInfo } from '../typescript/models/User/UserInfo';

export const create = async (post: PostModelCreate): Promise<Response> =>
  axios.post(`${BASE_ROUTE}/post/create`, post);

export const getPost = async (postId: string, token: string): Promise<PostModel> => {
  const params = new URLSearchParams([['postId', postId]]);
  return axios
    .get(`${BASE_ROUTE}/post/${postId}`, {
      ...AxiosAuthConfig(token),
      params
    })
    .then((x) => x.data);
};

export const getUserPosts = async (userId: string, token: string): Promise<PostModel[]> => {
  const params = new URLSearchParams([['userId', userId]]);
  return axios
    .get(`${BASE_ROUTE}/posts/${userId}`, { ...AxiosAuthConfig(token), params })
    .then((x) => x.data);
};

export const deletePost = async (postId: string, token: string): Promise<Response> => {
  const params = new URLSearchParams([['id', postId]]);
  return axios.post(`${BASE_ROUTE}/post/delete/${postId}`, { ...AxiosAuthConfig(token), params });
};

export const getUserInfo = async (nickname: string, token: string): Promise<UserInfo> => {
  const params = new URLSearchParams([['nickname', nickname]]);
  return axios
    .get(`${BASE_ROUTE}/user/${nickname}`, { ...AxiosAuthConfig(token), params })
    .then((x) => x.data);
};
