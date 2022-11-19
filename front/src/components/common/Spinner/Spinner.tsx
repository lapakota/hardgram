import React from 'react';
import { CircularProgress } from '@mui/material';
import styles from './Spinner.module.scss';

export const Spinner = () => (
  <div className={styles.spinner}>
    <CircularProgress />
  </div>
);
