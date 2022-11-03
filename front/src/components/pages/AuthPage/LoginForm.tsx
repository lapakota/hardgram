import React from 'react';
import { Button } from '@mui/material';
import { useForm } from 'react-hook-form';
import { NavLink } from 'react-router-dom';
import { signIn } from '../../../api/auth/authApi';
import { AuthRequest } from '../../../api/models/AuthRequest';
import { FormInputText } from '../../common/FormInputText/FormInputText';
import {
  REQUIRED_FIELD_ERROR_MESSAGE,
  SHORT_LOGIN_ERROR_MESSAGE,
  SHORT_PASSWORD_ERROR_MESSAGE
} from './constants';
import { HardgramLogo } from '../../common/HardgramLogo/HardgramLogo';
import styles from './AuthForms.module.scss';

export const LoginForm = () => {
  const {
    handleSubmit,
    control,
    formState: { errors }
  } = useForm();
  const onLogin = async (formData: AuthRequest) => {
    try {
      await signIn(formData);
    } catch (e) {
      console.log(e);
    }
  };
  const onSubmit = (data: any) => {
    onLogin(data).then((x) => console.log(x));
  };

  return (
    <form className={styles.form}>
      <div className={styles.main}>
        <HardgramLogo />
        <div className={styles.fields}>
          <FormInputText
            name={'nickName'}
            control={control}
            label={'Login'}
            rules={{
              required: { value: true, message: REQUIRED_FIELD_ERROR_MESSAGE },
              minLength: { value: 3, message: SHORT_LOGIN_ERROR_MESSAGE }
            }}
            errors={errors}
            required
          />
          <FormInputText
            name={'password'}
            control={control}
            label={'Password'}
            rules={{
              required: { value: true, message: REQUIRED_FIELD_ERROR_MESSAGE },
              minLength: { value: 6, message: SHORT_PASSWORD_ERROR_MESSAGE }
            }}
            errors={errors}
            fieldType={'password'}
            required
          />
          <Button onClick={handleSubmit(onSubmit)} variant={'contained'}>
            Sign in
          </Button>
        </div>
      </div>
      <div className={styles.additional}>
        Don&apos;t have an account? &nbsp; <NavLink to={'/auth/register'}>Sign up</NavLink>
      </div>
    </form>
  );
};
