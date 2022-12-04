import React from 'react';
import { Outlet } from 'react-router-dom';
import styles from './index.module.scss';
import { Layout } from '../../common/Layout/Layout';

export const AuthPage = () => {
  return (
    <Layout className={styles.root}>
      <Outlet />
    </Layout>
  );
};
