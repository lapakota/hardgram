import { PostModelCreate } from '../../typescript/models/Post/PostModelCreate';
import { BASE_ROUTE } from '../utils';
import axios from 'axios';
import { PostModel } from '../../typescript/models/Post/PostModel';

export const create = async (post: PostModelCreate): Promise<Response> =>
  axios.post(`${BASE_ROUTE}/post/create`, post);

// TODO ЧЗХ?
export const getPost = async (postId: number): Promise<PostModel> =>
  axios.get(`${BASE_ROUTE}/post/%7BpostId%7D?postId=${postId}`).then((x) => x.data);

export const getUserPosts = async (userId: string): Promise<PostModel[]> =>
  axios.get(`${BASE_ROUTE}/posts/%7BuserId%7D?userId=${userId}`).then((x) => x.data);

export const deletePost = async (postId: string): Promise<Response> =>
  axios.post(`${BASE_ROUTE}/post/delete/${postId}`);
