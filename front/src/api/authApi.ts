import { UserRegistrationModel } from '../typescript/models/Auth/UserRegistrationModel';
import { AuthRequest } from '../typescript/models/Auth/AuthRequest';
import { BASE_ROUTE } from './utils';
import axios from 'axios';

export const registerAccount = async (
  userRegistrationModel: UserRegistrationModel
): Promise<Response> => axios.post(`${BASE_ROUTE}/registration`, userRegistrationModel);

export const signIn = async (authRequest: AuthRequest): Promise<{ token: string }> =>
  axios.post(`${BASE_ROUTE}/auth/login`, authRequest).then((x) => x.data);
