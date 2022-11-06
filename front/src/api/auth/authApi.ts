import { AuthRequest } from '../models/AuthRequest';
import { UserRegistrationModel } from '../models/UserRegistrationModel';
import { BASE_ROUTE, HTTP_METHODS } from '../utils';

export const registerAccount = async (userRegistrationModel: UserRegistrationModel) =>
  await fetch(BASE_ROUTE + '/registration', {
    method: HTTP_METHODS.POST,
    headers: {
      'Content-Type': 'application/json',
      accept: '*/*'
    },
    body: JSON.stringify(userRegistrationModel)
  });

export const signIn = async (authRequest: AuthRequest) =>
  await fetch(BASE_ROUTE + '/auth/login', {
    method: HTTP_METHODS.POST,
    headers: {
      'Content-Type': 'application/json',
      accept: '*/*'
    },
    body: JSON.stringify(authRequest)
  });
