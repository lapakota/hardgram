import React from 'react';
import styles from './Header.module.scss';

export const Header = () => {
  //TODO проверять из userStore
  const isAuth = false;

  if (!isAuth) return null;

  return (
    <header className={styles.header}>
      <span className={styles.logo}>hardgram</span>
    </header>
  );
};
