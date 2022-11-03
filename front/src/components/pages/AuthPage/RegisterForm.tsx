import React from 'react';
import { Button } from '@mui/material';
import { useForm } from 'react-hook-form';
import { NavLink } from 'react-router-dom';
import { registerAccount } from '../../../api/auth/authApi';
import { UserRegistrationModel } from '../../../api/models/UserRegistrationModel';
import { FormInputText } from '../../common/FormInputText/FormInputText';
import {
  REQUIRED_FIELD_ERROR_MESSAGE,
  SHORT_FULL_NAME_ERROR_MESSAGE,
  SHORT_LOGIN_ERROR_MESSAGE,
  SHORT_PASSWORD_ERROR_MESSAGE
} from './constants';
import { HardgramLogo } from '../../common/HardgramLogo/HardgramLogo';
import styles from './AuthForms.module.scss';

export const RegisterForm = () => {
  const {
    handleSubmit,
    control,
    formState: { errors }
  } = useForm();

  const onRegister = async (formData: UserRegistrationModel) => {
    try {
      await registerAccount(formData);
    } catch (e) {
      console.log(e);
    }
  };
  const onSubmit = (data: any) => {
    onRegister(data).then((x) => console.log(x));
  };

  return (
    <form className={styles.form}>
      <div className={styles.main}>
        <HardgramLogo />
        <div className={styles.fields}>
          <FormInputText
            name={'nickname'}
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
            name={'fullName'}
            control={control}
            label={'Full name'}
            rules={{
              required: false,
              minLength: { value: 3, message: SHORT_FULL_NAME_ERROR_MESSAGE }
            }}
            errors={errors}
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
            Sign up
          </Button>
        </div>
      </div>
      <div className={styles.additional}>
        Already have an account? &nbsp; <NavLink to={'/auth/login'}>Sign in</NavLink>
      </div>
    </form>
  );
};
