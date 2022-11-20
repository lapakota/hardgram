import React from 'react';
import styles from './Header.module.scss';
import { observer } from 'mobx-react-lite';
import { useStores } from '../../../hooks/useStores';
import { NavLink, useLocation } from 'react-router-dom';

export const Header = observer(() => {
  const location = useLocation();
  const { userInfoStore } = useStores();

  if (!userInfoStore.token || location.pathname.startsWith('/auth')) return null;

  return (
    <header className={styles.header}>
      <div className={styles.content}>
        <NavLink to={'/'} className={styles.logo}>
          <span>hardgram</span>
        </NavLink>
      </div>
    </header>
  );
});
