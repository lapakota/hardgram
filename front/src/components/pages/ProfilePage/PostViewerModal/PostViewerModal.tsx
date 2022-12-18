import * as React from 'react';
import Box from '@mui/material/Box';
import Modal from '@mui/material/Modal';
import { PostModel } from '../../../../typescript/models/Post/PostModel';
import { Stack, Typography } from '@mui/material';
import { observer } from 'mobx-react-lite';
import { useStores } from '../../../../hooks/useStores';
import Carousel from 'react-material-ui-carousel';
import { Photo } from '../../../common/Photo/Photo';
import { useForm } from 'react-hook-form';
import { FormInputText } from '../../../common/Controls/FormInputText';
import { COMMENT_RULES } from '../../../../utils/validation/validationRules';
import IconButton from '@mui/material/IconButton';
import { useEffect, useState } from 'react';
import { addComment, getComments } from '../../../../api/commentsApi';
import { CommentModel } from '../../../../typescript/models/Comment/CommentModel';
import { Spinner } from '../../../common/Spinner/Spinner';
import { AddCommentModel } from '../../../../typescript/models/Comment/AddCommentModel';
import { CommentCard } from '../../../common/Comment/CommentCard/CommentCard';
import { Send } from '@mui/icons-material';

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

interface FormValues {
  text: string;
}

interface PostViewerModalProps {
  isOpen: boolean;
  handleClose: () => void;
  activePost: PostModel;
}

export const PostViewerModal = observer(
  ({ isOpen, handleClose, activePost }: PostViewerModalProps) => {
    const {
      handleSubmit,
      control,
      formState: { errors },
      reset
    } = useForm<FormValues>({
      defaultValues: {
        text: ''
      }
    });

    const {
      userInfoStore: { token }
    } = useStores();

    const [comments, setComments] = useState<CommentModel[]>();

    useEffect(() => {
      if (!token) return;
      const fetchComments = async () => {
        const fetchedComments = (await getComments(activePost.postId, token)) || [];
        setComments(fetchedComments);
      };
      fetchComments();
    }, []);

    const onAddComment = async (values: FormValues) => {
      if (!token || !comments) return;
      const addCommentModel: AddCommentModel = { postId: activePost.postId, text: values.text };
      const newComment = await addComment(addCommentModel, token);
      setComments([...comments, newComment]);
      reset();
    };

    return (
      <div>
        <Modal open={isOpen} onClose={handleClose}>
          <Box sx={MODAL_BOX_STYLE}>
            <Stack direction={'row'} spacing={5} sx={{ height: '100%' }}>
              <Stack direction={'column'} spacing={3} sx={{ width: 500 }}>
                <div
                  style={{
                    paddingBottom: 8,
                    borderBottom: '1px solid #DBDBDB',
                    background: '#EFEFEF'
                  }}>
                  {activePost.photos.length === 1 ? (
                    <Photo photo={activePost.photos[0]} />
                  ) : (
                    <Carousel autoPlay={false} height={540} animation={'slide'}>
                      {activePost.photos.map((photo, idx) => (
                        <Photo key={idx} photo={photo} />
                      ))}
                    </Carousel>
                  )}
                </div>
                <div>
                  <Typography width={500} sx={{ color: '#999999' }}>
                    Description
                  </Typography>
                  <div style={{ width: 500, wordBreak: 'break-all' }}>{activePost.description}</div>
                </div>
              </Stack>
              <Stack
                direction={'column'}
                spacing={2}
                sx={{ bgcolor: '#EFEFEF', flexGrow: '1', p: 2 }}>
                <Stack direction={'row'} alignItems={'center'}>
                  <FormInputText
                    name={'text'}
                    label={'Enter your comment'}
                    control={control}
                    multiline
                    rows={2}
                    rules={COMMENT_RULES}
                    errors={errors}
                    sx={{ flexGrow: 1, maxHeight: 90 }}
                  />
                  <IconButton onClick={handleSubmit(onAddComment)}>
                    <Send />
                  </IconButton>
                </Stack>
                {comments ? (
                  <Stack direction={'column'} spacing={2} sx={{ height: 600, overflowY: 'auto' }}>
                    {comments.map((comment) => (
                      <CommentCard
                        key={comment.commentId}
                        comment={comment}
                        setComments={setComments}
                      />
                    ))}
                  </Stack>
                ) : (
                  <Spinner />
                )}
              </Stack>
            </Stack>
          </Box>
        </Modal>
      </div>
    );
  }
);
