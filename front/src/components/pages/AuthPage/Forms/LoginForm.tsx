import React, { useState } from 'react';
import { Button } from '@mui/material';
import { useForm } from 'react-hook-form';
import { NavLink, useNavigate } from 'react-router-dom';
import { signIn } from '../../../../api/authApi';
import { FormInputText } from '../../../common/Controls/FormInputText';
import { AuthRequest } from '../../../../typescript/models/Auth/AuthRequest';
import { HardgramLogo } from '../../../common/HardgramLogo/HardgramLogo';
import styles from './AuthForms.module.scss';
import { useStores } from '../../../../hooks/useStores';
import { me } from '../../../../api/userApi';
import { observer } from 'mobx-react-lite';
import Toast from '../../../common/Toast/Toast';
import { LOGIN_RULES, PASSWORD_RULES } from '../../../../utils/validation/validationRules';

interface FormValues {
  nickname: string;
  password: string;
}

export const LoginForm = observer(() => {
  const [showErrorToast, setShowErrorToast] = useState<boolean>(false);

  const navigate = useNavigate();

  const {
    handleSubmit,
    control,
    formState: { errors }
  } = useForm<FormValues>();

  const { userInfoStore } = useStores();

  const onLogin = async (formData: AuthRequest) => {
    try {
      const { token } = await signIn(formData);
      const userInfo = await me(token);

      userInfoStore.initStore(userInfo, token);
      navigate(`/user/profile/${userInfoStore.userInfo?.nickname}`);
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
            name={'password'}
            control={control}
            label={'Password'}
            rules={PASSWORD_RULES}
            errors={errors}
            fieldType={'password'}
            required
          />
          <Button onClick={handleSubmit(onLogin)} variant={'contained'}>
            Sign in
          </Button>
        </div>
      </div>
      <div className={styles.additional}>
        Don&apos;t have an account? &nbsp; <NavLink to={'/auth/register'}>Sign up</NavLink>
      </div>
      <Toast
        isOpen={showErrorToast}
        setIsOpen={setShowErrorToast}
        toastType={'error'}
        message={'Failed to login, please try again'}
      />
    </form>
  );
});
