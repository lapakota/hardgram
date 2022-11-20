import * as React from 'react';
import Box from '@mui/material/Box';
import Modal from '@mui/material/Modal';
import { PostModel } from '../../../../typescript/models/Post/PostModel';
import { useForm } from 'react-hook-form';
import { FormUploadImage } from '../../../common/Controls/FormUploadImage';
import { FormInputText } from '../../../common/Controls/FormInputText';
import { Button } from '@mui/material';

const MODAL_BOX_STYLE = {
  position: 'absolute' as const,
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 500,
  bgcolor: 'background.paper',
  boxShadow: 32,
  p: 4
};

interface FormValues {
  photos: FileList[];
  description: string;
}

interface AddPostModalProps {
  isOpen: boolean;
  handleClose: React.Dispatch<React.SetStateAction<boolean>>;
  activePost?: PostModel;
}

export default function AddPostModal({ isOpen, handleClose, activePost }: AddPostModalProps) {
  const { handleSubmit, control } = useForm<FormValues>();

  const onAddPost = (values: FormValues) => {
    console.log(values);
  };

  return (
    <div>
      <Modal open={isOpen} onClose={handleClose}>
        <Box sx={MODAL_BOX_STYLE}>
          {activePost ? (
            'Change post'
          ) : (
            <form>
              <FormUploadImage name={'photos'} control={control} multiple />
              <FormInputText name={'description'} control={control} multiline />
              <Button onClick={handleSubmit(onAddPost)} variant={'contained'}>
                Sign up
              </Button>
            </form>
          )}
        </Box>
      </Modal>
    </div>
  );
}
