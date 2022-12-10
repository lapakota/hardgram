import * as React from 'react';
import Box from '@mui/material/Box';
import Modal from '@mui/material/Modal';
import { PostModel } from '../../../../typescript/models/Post/PostModel';
import { useForm } from 'react-hook-form';
import { FormUploadImage } from '../../../common/Controls/FormUploadImage';
import { FormInputText } from '../../../common/Controls/FormInputText';
import { Button, Stack } from '@mui/material';
import { DESCRIPTION_RULES } from '../../../../utils/validation/validationRules';
import { createPost } from '../../../../api/postsApi';
import { observer } from 'mobx-react-lite';
import { useStores } from '../../../../hooks/useStores';
import { CreatePostModel } from '../../../../typescript/models/Post/CreatePostModel';
import { useState } from 'react';
import Toast from '../../../common/Toast/Toast';
import Carousel from 'react-material-ui-carousel';

const MODAL_BOX_STYLE = {
  position: 'absolute' as const,
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 500,
  bgcolor: 'background.paper',
  boxShadow: 32,
  p: 12
};

interface FormValues {
  photos: string[];
  description: string;
}

interface AddPostModalProps {
  isOpen: boolean;
  handleClose: () => void;
  activePost?: PostModel;
}

export const AddPostModal = observer(({ isOpen, handleClose, activePost }: AddPostModalProps) => {
  const {
    handleSubmit,
    control,
    formState: { errors },
    watch,
    reset
  } = useForm<FormValues>({
    defaultValues: {
      photos: activePost?.photos || [],
      description: activePost?.description || ''
    }
  });

  const {
    userInfoStore: { token }
  } = useStores();

  const [showSuccessToast, setShowSuccessToast] = useState<boolean>(false);
  const [showErrorToast, setShowErrorToast] = useState<boolean>(false);

  const photos = watch('photos');

  const onAddPost = async (values: CreatePostModel) => {
    if (!token || (values.photos?.length === 0 && !values.description)) return;

    try {
      await createPost(values, token);
      setShowSuccessToast(true);
    } catch (e) {
      setShowErrorToast(true);
    }
  };

  return (
    <div>
      <Modal
        open={isOpen}
        onClose={() => {
          reset();
          handleClose();
        }}>
        <Box sx={MODAL_BOX_STYLE}>
          <form>
            {photos.length > 0 && (
              <Carousel autoPlay={false} height={350} animation={'slide'}>
                {photos.map((photo, idx) => (
                  <Stack justifyContent={'center'} alignItems={'center'} key={idx}>
                    <img
                      src={photo}
                      alt={'post photo'}
                      style={{ objectFit: 'contain', width: 'auto', maxHeight: 350 }}
                    />
                  </Stack>
                ))}
              </Carousel>
            )}
            <Stack direction={'column'} spacing={2}>
              <FormUploadImage
                caption={'Upload photos'}
                name={'photos'}
                control={control}
                multiple
              />
              <FormInputText
                name={'description'}
                control={control}
                multiline
                rules={DESCRIPTION_RULES}
                errors={errors}
              />
              <Button onClick={handleSubmit(onAddPost)} variant={'contained'} size={'large'}>
                Add post
              </Button>
            </Stack>
            <Toast
              isOpen={showSuccessToast}
              setIsOpen={setShowSuccessToast}
              toastType={'success'}
              message={'Post is created!'}
            />
            <Toast
              isOpen={showErrorToast}
              setIsOpen={setShowErrorToast}
              toastType={'error'}
              message={'Failed to create post :('}
            />
          </form>
        </Box>
      </Modal>
    </div>
  );
});
