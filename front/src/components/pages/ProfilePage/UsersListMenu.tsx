import * as React from 'react';
import Menu from '@mui/material/Menu';
import MenuItem from '@mui/material/MenuItem';
import { observer } from 'mobx-react-lite';
import { UserModel } from '../../../typescript/models/User/UserModel';
import { Avatar, Stack } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import styles from './ProfilePage.module.scss';

interface UsersListProps {
  label: string;
  users: UserModel[];
}

export const UsersListMenu = observer(({ label, users }: UsersListProps) => {
  const navigate = useNavigate();

  const [anchorEl, setAnchorEl] = React.useState<null | HTMLElement>(null);
  const open = Boolean(anchorEl);

  const handleClick = (event: React.MouseEvent<HTMLElement>) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  const onUserClick = (nickname: string) => navigate(`/user/profile/${nickname}`);

  return (
    <div>
      <span className={styles.usersList} onClick={handleClick}>
        <b>{users?.length || 0}</b> {label}
      </span>
      <Menu
        anchorEl={anchorEl}
        id="users-menu"
        open={open && users.length > 0}
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
        {users.map((user) => (
          <MenuItem key={user.nickname} onClick={() => onUserClick(user.nickname)}>
            <Stack direction={'row'} spacing={1} alignItems={'center'}>
              <Avatar src={user.avatar} />
              <span>{user.nickname}</span>
            </Stack>
          </MenuItem>
        ))}
      </Menu>
    </div>
  );
});
