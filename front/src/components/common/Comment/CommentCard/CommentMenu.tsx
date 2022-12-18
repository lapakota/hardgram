import * as React from 'react';
import Menu from '@mui/material/Menu';
import MenuItem from '@mui/material/MenuItem';
import ListItemIcon from '@mui/material/ListItemIcon';
import IconButton from '@mui/material/IconButton';
import Tooltip from '@mui/material/Tooltip';
import { observer } from 'mobx-react-lite';
import { useStores } from '../../../../hooks/useStores';
import MoreVertIcon from '@mui/icons-material/MoreVert';
import { Delete } from '@mui/icons-material';
import { CommentModel } from '../../../../typescript/models/Comment/CommentModel';
import { deleteComment, getComments } from '../../../../api/commentsApi';

interface CommentMenuProps {
  comment: CommentModel;
  setComments: React.Dispatch<React.SetStateAction<CommentModel[] | undefined>>;
}

export const CommentMenu = observer(({ comment, setComments }: CommentMenuProps) => {
  const {
    userInfoStore: { token }
  } = useStores();

  const [anchorEl, setAnchorEl] = React.useState<null | HTMLElement>(null);
  const open = Boolean(anchorEl);

  const handleClick = (event: React.MouseEvent<HTMLElement>) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  const onDelete = async () => {
    if (!token) return;
    await deleteComment(comment.commentId, token);
    const newComments = await getComments(comment.postId, token);
    setComments(newComments);
  };

  return (
    <>
      <Tooltip title="Comment settings">
        <IconButton
          onClick={handleClick}
          size="small"
          aria-controls={open ? 'comment-menu' : undefined}
          aria-haspopup="true"
          aria-expanded={open ? 'true' : undefined}>
          <MoreVertIcon />
        </IconButton>
      </Tooltip>
      <Menu
        anchorEl={anchorEl}
        id="comment-menu"
        open={open}
        onClose={handleClose}
        onClick={handleClose}
        PaperProps={{
          elevation: 0,
          sx: {
            overflow: 'visible',
            filter: 'drop-shadow(0px 2px 8px rgba(0,0,0,0.32))',
            mt: 1.5
          }
        }}
        transformOrigin={{ horizontal: 'right', vertical: 'top' }}
        anchorOrigin={{ horizontal: 'right', vertical: 'bottom' }}
        disableScrollLock>
        <MenuItem onClick={onDelete}>
          <ListItemIcon>
            <Delete fontSize="small" />
          </ListItemIcon>
          Delete
        </MenuItem>
      </Menu>
    </>
  );
});
