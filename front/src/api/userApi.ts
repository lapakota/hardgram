import axios from 'axios';
import { AxiosAuthConfig, BASE_ROUTE } from './utils';
import { UserModel } from '../typescript/models/User/UserModel';
import { UserUpdateModel } from '../typescript/models/User/UserUpdateModel';

export const me = async (token: string): Promise<UserModel> =>
  axios.get(`${BASE_ROUTE}/me`, AxiosAuthConfig(token)).then((x) => x.data);

export const getUserInfo = async (nickname: string, token: string): Promise<UserModel> =>
  axios.get(`${BASE_ROUTE}/user/${nickname}`, { ...AxiosAuthConfig(token) }).then((x) => x.data);

export const updateUser = async (
  userUpdateModel: UserUpdateModel,
  token: string
): Promise<UserModel> => {
  return axios
    .post(`${BASE_ROUTE}/user/update`, userUpdateModel, { ...AxiosAuthConfig(token) })
    .then((x) => x.data);
};
