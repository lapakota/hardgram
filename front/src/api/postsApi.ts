import { CreatePostModel } from '../typescript/models/Post/CreatePostModel';
import { AxiosAuthConfig, BASE_ROUTE } from './utils';
import axios from 'axios';
import { PostModel } from '../typescript/models/Post/PostModel';
import { UpdatePostModel } from '../typescript/models/Post/UpdatePostModel';

export const createPost = async (post: CreatePostModel, token: string): Promise<Response> =>
  axios.post(`${BASE_ROUTE}/post/create`, post, { ...AxiosAuthConfig(token) });

export const updatePost = async (post: UpdatePostModel, token: string): Promise<Response> =>
  axios.post(`${BASE_ROUTE}/post/update`, post, { ...AxiosAuthConfig(token) });

export const getPost = async (postId: number, token: string): Promise<PostModel> =>
  axios
    .get(`${BASE_ROUTE}/post/${postId}`, {
      ...AxiosAuthConfig(token)
    })
    .then((x) => x.data);

export const getUserPosts = async (nickname: string, token: string): Promise<PostModel[]> =>
  axios.get(`${BASE_ROUTE}/posts/${nickname}`, { ...AxiosAuthConfig(token) }).then((x) => x.data);

export const deletePost = async (postId: number, token: string): Promise<Response> =>
  axios.delete(`${BASE_ROUTE}/post/${postId}`, { ...AxiosAuthConfig(token) });
