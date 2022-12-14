import * as React from 'react';
import Box from '@mui/material/Box';
import Modal from '@mui/material/Modal';
import { PostModel } from '../../../../typescript/models/Post/PostModel';
import { useForm } from 'react-hook-form';
import { FormUploadImage } from '../../../common/Controls/FormUploadImage';
import { FormInputText } from '../../../common/Controls/FormInputText';
import { Button, Stack } from '@mui/material';
import { DESCRIPTION_RULES } from '../../../../utils/validation/validationRules';
import { createPost, getUserPosts, updatePost } from '../../../../api/postsApi';
import { observer } from 'mobx-react-lite';
import { useStores } from '../../../../hooks/useStores';
import { CreatePostModel } from '../../../../typescript/models/Post/CreatePostModel';
import { useEffect, useState } from 'react';
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
  p: 5
};

interface FormValues {
  photos: string[];
  description: string;
}

interface PostEditorModalProps {
  isOpen: boolean;
  handleClose: () => void;
  activePost?: PostModel;
  setPosts: React.Dispatch<React.SetStateAction<PostModel[] | undefined>>;
}

export const PostEditorModal = observer(
  ({ isOpen, handleClose, activePost, setPosts }: PostEditorModalProps) => {
    const {
      handleSubmit,
      control,
      formState: { errors },
      watch,
      reset
    } = useForm<FormValues>({
      defaultValues: {
        photos: [],
        description: ''
      }
    });

    const {
      userInfoStore: { token, userInfo }
    } = useStores();

    const [showErrorToast, setShowErrorToast] = useState<boolean>(false);

    useEffect(() => {
      activePost &&
        reset({
          photos: activePost?.photos || [],
          description: activePost?.description || ''
        });
    }, [activePost]);

    const photos = watch('photos');

    const onPostAction = async (values: CreatePostModel) => {
      if (!token || !userInfo?.nickname || (values.photos?.length === 0 && !values.description))
        return;

      try {
        activePost?.postId
          ? await updatePost(
              {
                ...values,
                postId: activePost.postId
              },
              token
            )
          : await createPost(values, token);
        const newPosts = await getUserPosts(userInfo.nickname, token);
        setPosts(newPosts);
        reset();
        handleClose();
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
                    <Stack
                      justifyContent={'center'}
                      alignItems={'center'}
                      key={idx}
                      sx={{ height: 350 }}>
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
                <Stack direction={'row'} justifyContent={'flex-end'}>
                  <FormUploadImage
                    caption={'Upload photos'}
                    name={'photos'}
                    control={control}
                    multiple
                  />
                </Stack>
                <FormInputText
                  name={'description'}
                  control={control}
                  multiline
                  rules={DESCRIPTION_RULES}
                  errors={errors}
                />
                <Button onClick={handleSubmit(onPostAction)} variant={'contained'} size={'large'}>
                  {activePost ? 'Update post' : 'Add post'}
                </Button>
              </Stack>
              <Toast
                isOpen={showErrorToast}
                setIsOpen={setShowErrorToast}
                toastType={'error'}
                message={'Fail, please try again'}
              />
            </form>
          </Box>
        </Modal>
      </div>
    );
  }
);
