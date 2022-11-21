import React from 'react';
import styles from './Header.module.scss';
import { observer } from 'mobx-react-lite';
import { useStores } from '../../../hooks/useStores';
import { NavLink, useLocation } from 'react-router-dom';
import { Avatar, Stack } from '@mui/material';

export const Header = observer(() => {
  const location = useLocation();
  const { userInfoStore } = useStores();

  if (!userInfoStore.token || location.pathname.startsWith('/auth')) return null;

  return (
    <header className={styles.header}>
      <Stack direction={'row'} justifyContent={'space-between'} className={styles.content}>
        <NavLink to={'/'} className={styles.logo}>
          <span>hardgram</span>
        </NavLink>
        <NavLink to={`/user/profile/${userInfoStore.userInfo?.nickname}`}>
          <Avatar src={userInfoStore.userInfo?.avatar} />
        </NavLink>
      </Stack>
    </header>
  );
});
