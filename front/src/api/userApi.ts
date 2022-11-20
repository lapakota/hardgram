import axios from 'axios';
import { AxiosAuthConfig, BASE_ROUTE } from './utils';
import { UserInfo } from '../typescript/models/User/UserInfo';

export const me = async (token: string): Promise<UserInfo> =>
  axios.get(`${BASE_ROUTE}/me`, AxiosAuthConfig(token)).then((x) => x.data);

export const getUserInfo = async (nickname: string, token: string): Promise<UserInfo> => {
  const params = new URLSearchParams([['nickname', nickname]]);
  return axios
    .get(`${BASE_ROUTE}/user/${nickname}`, { ...AxiosAuthConfig(token), params })
    .then((x) => x.data);
};
