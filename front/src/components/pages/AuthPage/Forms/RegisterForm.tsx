import React, { useState } from 'react';
import { Button } from '@mui/material';
import { useForm } from 'react-hook-form';
import { Navigate, NavLink } from 'react-router-dom';
import { registerAccount } from '../../../../api/auth/authApi';
import { FormInputText } from '../../../common/Controls/FormInputText';
import {
  REQUIRED_FIELD_ERROR_MESSAGE,
  SHORT_FULL_NAME_ERROR_MESSAGE,
  SHORT_LOGIN_ERROR_MESSAGE,
  SHORT_PASSWORD_ERROR_MESSAGE
} from './constants';
import { HardgramLogo } from '../../../common/HardgramLogo/HardgramLogo';
import styles from './AuthForms.module.scss';
import { UserRegistrationModel } from '../../../../typescript/models/Auth/UserRegistrationModel';
import Toast from '../../../common/Toast/Toast';

interface FormValues {
  nickname: string;
  fullName: string;
  password: string;
}

export const RegisterForm = () => {
  const [showErrorToast, setShowErrorToast] = useState<boolean>(false);
  const [needRedirect, setNeedRedirect] = useState<boolean>(false);

  const {
    handleSubmit,
    control,
    formState: { errors }
  } = useForm<FormValues>();

  const onRegister = async (formData: UserRegistrationModel) => {
    try {
      await registerAccount(formData);
      setNeedRedirect(true);
    } catch (e) {
      setShowErrorToast(true);
    }
  };

  if (needRedirect) return <Navigate to={`/auth/login`} replace />;

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
              minLength: { value: 5, message: SHORT_PASSWORD_ERROR_MESSAGE }
            }}
            errors={errors}
            fieldType={'password'}
            required
          />
          <Button onClick={handleSubmit(onRegister)} variant={'contained'}>
            Sign up
          </Button>
        </div>
      </div>
      <div className={styles.additional}>
        Already have an account? &nbsp; <NavLink to={'/auth/login'}>Sign in</NavLink>
      </div>
      <Toast
        isOpen={showErrorToast}
        setIsOpen={setShowErrorToast}
        toastType={'error'}
        message={'Failed to register, please try again'}
      />
    </form>
  );
};
