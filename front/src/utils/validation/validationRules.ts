import {
  LONG_DESCRIPTION_ERROR_MESSAGE,
  REQUIRED_FIELD_ERROR_MESSAGE,
  SHORT_FULL_NAME_ERROR_MESSAGE,
  SHORT_LOGIN_ERROR_MESSAGE,
  SHORT_PASSWORD_ERROR_MESSAGE
} from './errors';

export const PASSWORD_RULES = {
  required: { value: true, message: REQUIRED_FIELD_ERROR_MESSAGE },
  minLength: { value: 5, message: SHORT_PASSWORD_ERROR_MESSAGE }
};

export const FULL_NAME_RULES = {
  required: false,
  minLength: { value: 3, message: SHORT_FULL_NAME_ERROR_MESSAGE }
};

export const LOGIN_RULES = {
  required: { value: true, message: REQUIRED_FIELD_ERROR_MESSAGE },
  minLength: { value: 3, message: SHORT_LOGIN_ERROR_MESSAGE }
};

export const DESCRIPTION_RULES = {
  maxLength: { value: 140, message: LONG_DESCRIPTION_ERROR_MESSAGE }
};
