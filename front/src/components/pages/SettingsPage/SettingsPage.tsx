import React, { useState } from 'react';
import { Layout } from '../../common/Layout/Layout';
import { Avatar, Button, Stack, TextField } from '@mui/material';
import { observer } from 'mobx-react-lite';
import { useStores } from '../../../hooks/useStores';
import { FormInputText } from '../../common/Controls/FormInputText';
import { useForm } from 'react-hook-form';
import { FULL_NAME_RULES } from '../../../utils/validation/validationRules';
import { FormUploadImage } from '../../common/Controls/FormUploadImage';
import { updateUser } from '../../../api/userApi';
import Toast from '../../common/Toast/Toast';
import styles from './SettingsPage.module.scss';

interface FormValues {
  avatar: string[];
  fullName: string;
}

export const SettingsPage = observer(() => {
  const { userInfoStore } = useStores();
  const { handleSubmit, control, formState, watch } = useForm<FormValues>({
    defaultValues: {
      avatar: userInfoStore.userInfo?.avatar ? [userInfoStore.userInfo?.avatar] : [],
      fullName: userInfoStore.userInfo?.fullName || ''
    }
  });

  const [showSuccessToast, setShowSuccessToast] = useState<boolean>(false);
  const [showErrorToast, setShowErrorToast] = useState<boolean>(false);

  const currentAvatar = watch('avatar')[0];

  const onSubmit = async (values: FormValues) => {
    if (!userInfoStore.token) return;
    try {
      const newUserInfo = await updateUser(
        { ...values, avatar: values.avatar[0] || '' },
        userInfoStore.token
      );
      userInfoStore.initStore(newUserInfo, userInfoStore.token);
      setShowSuccessToast(true);
    } catch (e) {
      setShowErrorToast(true);
    }
  };

  return (
    <Layout>
      <form>
        <Stack direction={'column'} spacing={2} alignItems={'center'} justifyContent={'center'}>
          <Avatar src={currentAvatar} alt={'avatar'} sx={{ width: 150, height: 150 }} />
          <FormUploadImage caption={'Upload avatar'} name={'avatar'} control={control} />
          <Stack className={styles.formInputs} direction={'column'} spacing={2}>
            <TextField defaultValue={userInfoStore.userInfo?.nickname} disabled />
            <FormInputText
              name={'fullName'}
              control={control}
              label={'Full name'}
              rules={FULL_NAME_RULES}
              errors={formState.errors}
            />
            <Button onClick={handleSubmit(onSubmit)} size={'large'} variant={'contained'}>
              Save
            </Button>
          </Stack>
        </Stack>
        <Toast
          isOpen={showSuccessToast}
          setIsOpen={setShowSuccessToast}
          toastType={'success'}
          message={'User information changed successfully!'}
        />
        <Toast
          isOpen={showErrorToast}
          setIsOpen={setShowErrorToast}
          toastType={'error'}
          message={'Failed to change user information :( Please try again.'}
        />
      </form>
    </Layout>
  );
});
