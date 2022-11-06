import { BASE_ROUTE, HTTP_METHODS } from '../utils';
import { PostModelCreate } from '../models/Post/PostModelCreate';

export const create = async (post: PostModelCreate): Promise<Response> =>
  await fetch(BASE_ROUTE + '/posts/create', {
    method: HTTP_METHODS.POST,
    headers: {
      'Content-Type': 'application/json',
      accept: '*/*'
    },
    body: JSON.stringify(post)
  });

export const getPost = async (postId: string): Promise<Response> =>
  await fetch(BASE_ROUTE + '/posts/create', {
    method: HTTP_METHODS.GET,
    headers: {
      'Content-Type': 'application/json',
      accept: '*/*'
    },
    body: JSON.stringify(postId)
  });

export const getPosts = async (userId: string): Promise<Response> =>
  await fetch(BASE_ROUTE + '/posts/create', {
    method: HTTP_METHODS.GET,
    headers: {
      'Content-Type': 'application/json',
      accept: '*/*'
    },
    body: JSON.stringify(userId)
  });
