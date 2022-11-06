import React from 'react';
import styles from './PostPage.module.scss';
import { PhotosView } from '../../common/Photo/PhotosView';
import { Button } from '@mui/material';

export function PostPageView(): React.ReactElement {
  return (
    <div className={styles.page}>
      <PhotosView />
      <div>asd</div>
      <Button>Change</Button>
      <Button>Cancel</Button>
    </div>
  );
}
