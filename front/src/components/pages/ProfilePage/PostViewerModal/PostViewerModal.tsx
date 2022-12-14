import * as React from 'react';
import Box from '@mui/material/Box';
import Modal from '@mui/material/Modal';
import { PostModel } from '../../../../typescript/models/Post/PostModel';
import { Stack, Typography } from '@mui/material';
import { observer } from 'mobx-react-lite';
import { useStores } from '../../../../hooks/useStores';
import Carousel from 'react-material-ui-carousel';
import styles from './PostViewerModal.module.scss';
import { Photo } from '../../../common/Photo/Photo';

const MODAL_BOX_STYLE = {
  position: 'absolute' as const,
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 850,
  height: 700,
  bgcolor: 'background.paper',
  boxShadow: 32,
  p: 5
};

interface PostViewerModalProps {
  isOpen: boolean;
  handleClose: () => void;
  activePost: PostModel;
  setPosts: React.Dispatch<React.SetStateAction<PostModel[] | undefined>>;
}

export const PostViewerModal = observer(
  ({ isOpen, handleClose, activePost, setPosts }: PostViewerModalProps) => {
    const {
      userInfoStore: { token, userInfo }
    } = useStores();

    // TODO нормально сверстать и добавить комментарии
    return (
      <div>
        <Modal open={isOpen} onClose={handleClose}>
          <Box sx={MODAL_BOX_STYLE}>
            <Stack direction={'row'} spacing={5} sx={{ height: '100%' }}>
              <Stack direction={'column'} spacing={3} sx={{ width: 500 }}>
                {activePost.photos.length === 1 ? (
                  <Photo photo={activePost.photos[0]} />
                ) : (
                  <Carousel autoPlay={false} height={550} animation={'slide'}>
                    {activePost.photos.map((photo, idx) => (
                      <Photo key={idx} photo={photo} />
                    ))}
                  </Carousel>
                )}
                <div>
                  <Typography width={500}>Description</Typography>
                  <div style={{ width: 500, wordBreak: 'break-all' }}>{activePost.description}</div>
                </div>
              </Stack>
              <div style={{ background: '#EFEFEF', flexGrow: '1' }} />
            </Stack>
          </Box>
        </Modal>
      </div>
    );
  }
);
