import { BASE_ROUTE, HTTP_METHODS } from '../utils';
import { PostModelCreate } from '../models/Post/PostModelCreate';

export const create = async (post: PostModelCreate): Promise<Response> =>
  await fetch(BASE_ROUTE + '/post/create', {
    method: HTTP_METHODS.POST,
    headers: {
      'Content-Type': 'application/json',
      accept: '*/*'
    },
    body: JSON.stringify(post)
  });

export const getPost = async (postId: string): Promise<Response> =>
  await fetch(BASE_ROUTE + `/post/${postId}`, {
    method: HTTP_METHODS.GET,
    headers: {
      'Content-Type': 'application/json',
      accept: '*/*'
    }
  });

export const getPosts = async (userId: string): Promise<Response> =>
  await fetch(BASE_ROUTE + `/posts/${userId}`, {
    method: HTTP_METHODS.GET,
    headers: {
      'Content-Type': 'application/json',
      accept: '*/*'
    }
  });

export const deletePost = async (postId: string): Promise<Response> =>
  await fetch(BASE_ROUTE + `/post/delete/${postId}`, {
    method: HTTP_METHODS.GET,
    headers: {
      'Content-Type': 'application/json',
      accept: '*/*'
    }
  });
