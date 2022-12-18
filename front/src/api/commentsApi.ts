import axios from 'axios';
import { AxiosAuthConfig, BASE_ROUTE } from './utils';
import { CommentModel } from '../typescript/models/Comment/CommentModel';
import { UpdateCommentModel } from '../typescript/models/Comment/UpdateCommentModel';
import { AddCommentModel } from '../typescript/models/Comment/AddCommentModel';

export const addComment = async (data: AddCommentModel, token: string): Promise<CommentModel> =>
  axios.post(`${BASE_ROUTE}/comment`, data, { ...AxiosAuthConfig(token) }).then((x) => x.data);

export const updateComment = async (data: UpdateCommentModel, token: string): Promise<Response> =>
  axios.post(`${BASE_ROUTE}/comment/update`, data, { ...AxiosAuthConfig(token) });

export const getComments = async (postId: number, token: string): Promise<CommentModel[]> =>
  axios
    .get(`${BASE_ROUTE}/comments/${postId}`, {
      ...AxiosAuthConfig(token)
    })
    .then((x) => x.data);

export const deleteComment = async (commentId: number, token: string): Promise<Response> =>
  axios.delete(`${BASE_ROUTE}/comment/${commentId}`, { ...AxiosAuthConfig(token) });
