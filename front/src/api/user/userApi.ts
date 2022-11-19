import axios from 'axios';
import { AxiosAuthConfig, BASE_ROUTE } from '../utils';
import { UserInfo } from '../../typescript/models/User/UserInfo';

// TODO Проблема с корсами из-за автаризационного хедера
export const me = async (token: string): Promise<UserInfo> =>
  axios.get(`${BASE_ROUTE}/me`, AxiosAuthConfig(token)).then((x) => x.data);

export const getUserById = async (id: string): Promise<UserInfo> =>
  axios.get(`${BASE_ROUTE}/user/%7BuserId%7D?userId=${id}`).then((x) => x.data);
