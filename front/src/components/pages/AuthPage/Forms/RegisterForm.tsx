import React, { useState } from 'react';
import { Button } from '@mui/material';
import { useForm } from 'react-hook-form';
import { NavLink, useNavigate } from 'react-router-dom';
import { registerAccount } from '../../../../api/authApi';
import { FormInputText } from '../../../common/Controls/FormInputText';
import { HardgramLogo } from '../../../common/HardgramLogo/HardgramLogo';
import styles from './AuthForms.module.scss';
import { UserRegistrationModel } from '../../../../typescript/models/Auth/UserRegistrationModel';
import Toast from '../../../common/Toast/Toast';
import {
  FULL_NAME_RULES,
  LOGIN_RULES,
  PASSWORD_RULES
} from '../../../../utils/validation/validationRules';

interface FormValues {
  nickname: string;
  fullName: string;
  password: string;
}

export const RegisterForm = () => {
  const [showSuccessToast, setShowSuccessToast] = useState<boolean>(false);
  const [showErrorToast, setShowErrorToast] = useState<boolean>(false);

  const navigate = useNavigate();

  const {
    handleSubmit,
    control,
    formState: { errors }
  } = useForm<FormValues>();

  const onRegister = async (formData: UserRegistrationModel) => {
    try {
      await registerAccount(formData);
      setShowSuccessToast(true);
      navigate(`/auth/login`);
    } catch (e) {
      setShowErrorToast(true);
    }
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
            rules={LOGIN_RULES}
            errors={errors}
            required
          />
          <FormInputText
            name={'fullName'}
            control={control}
            label={'Full name'}
            rules={FULL_NAME_RULES}
            errors={errors}
          />
          <FormInputText
            name={'password'}
            control={control}
            label={'Password'}
            rules={PASSWORD_RULES}
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
      <Toast
        isOpen={showSuccessToast}
        setIsOpen={setShowSuccessToast}
        toastType={'success'}
        message={'Registration complete! Please log in.'}
      />
    </form>
  );
};
