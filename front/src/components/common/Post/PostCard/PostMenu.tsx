import * as React from 'react';
import Menu from '@mui/material/Menu';
import MenuItem from '@mui/material/MenuItem';
import ListItemIcon from '@mui/material/ListItemIcon';
import IconButton from '@mui/material/IconButton';
import Tooltip from '@mui/material/Tooltip';
import { observer } from 'mobx-react-lite';
import { useStores } from '../../../../hooks/useStores';
import MoreVertIcon from '@mui/icons-material/MoreVert';
import { Delete, Edit } from '@mui/icons-material';
import { PostModel } from '../../../../typescript/models/Post/PostModel';
import { deletePost, getUserPosts } from '../../../../api/postsApi';

interface PostMenuProps {
  post: PostModel;
  setPosts: React.Dispatch<React.SetStateAction<PostModel[] | undefined>>;
  onOpenEditorModal: (post?: PostModel) => void;
}

export const PostMenu = observer(({ post, setPosts, onOpenEditorModal }: PostMenuProps) => {
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

  const onEdit = () => {
    onOpenEditorModal(post);
  };

  const onDelete = async () => {
    if (!token) return;
    await deletePost(post.postId, token);
    const newPosts = await getUserPosts(post.nickname, token);
    setPosts(newPosts);
  };

  return (
    <>
      <Tooltip title="Post settings">
        <IconButton
          onClick={handleClick}
          size="small"
          aria-controls={open ? 'post-menu' : undefined}
          aria-haspopup="true"
          aria-expanded={open ? 'true' : undefined}>
          <MoreVertIcon />
        </IconButton>
      </Tooltip>
      <Menu
        anchorEl={anchorEl}
        id="account-menu"
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
        <MenuItem onClick={onEdit}>
          <ListItemIcon>
            <Edit fontSize="small" />
          </ListItemIcon>
          Edit
        </MenuItem>
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
