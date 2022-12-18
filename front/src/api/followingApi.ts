import axios from 'axios';
import { AxiosAuthConfig, BASE_ROUTE } from './utils';
import { UserModel } from '../typescript/models/User/UserModel';

export const follow = async (nickname: string, token: string): Promise<Response> =>
  axios.post(`${BASE_ROUTE}/follow/${nickname}`, {}, { ...AxiosAuthConfig(token) });

export const unFollow = async (nickname: string, token: string): Promise<Response> =>
  axios.post(`${BASE_ROUTE}/unfollow/${nickname}`, {}, { ...AxiosAuthConfig(token) });

export const getFollowers = async (nickname: string, token: string): Promise<UserModel[]> =>
  axios
    .get(`${BASE_ROUTE}/followers/${nickname}`, {
      ...AxiosAuthConfig(token)
    })
    .then((x) => x.data);

export const getFollowing = async (nickname: string, token: string): Promise<UserModel[]> =>
  axios
    .get(`${BASE_ROUTE}/following/${nickname}`, {
      ...AxiosAuthConfig(token)
    })
    .then((x) => x.data);
