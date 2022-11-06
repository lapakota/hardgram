import React from 'react';
import styles from './PostPage.module.scss';
import { Button } from '@mui/material';
import { PhotosEdit } from '../../common/Photo/PhotosEdit';

export function PostPageEdit(): React.ReactElement {
  return (
    <div className={styles.page}>
      <PhotosEdit />
      <div>as</div>
      <Button>Change</Button>
      <Button>Cancel</Button>
    </div>
  );
}
