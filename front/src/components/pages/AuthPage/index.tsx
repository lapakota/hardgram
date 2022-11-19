import React from 'react';
import { Outlet } from 'react-router-dom';
import styles from './index.module.scss';

export const AuthPage = () => {
  return (
    <div className={styles.root}>
      <Outlet />
    </div>
  );
};
