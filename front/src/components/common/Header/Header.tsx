import React from 'react';
import styles from './Header.module.scss';
import { observer } from 'mobx-react-lite';
import { useStores } from '../../../hooks/useStores';

export const Header = observer(() => {
  const { userInfoStore } = useStores();

  if (!userInfoStore.token) return null;

  return (
    <header className={styles.header}>
      <div className={styles.content}>
        <span className={styles.logo}>hardgram</span>
      </div>
    </header>
  );
});
