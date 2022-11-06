import React, { useCallback } from 'react';
import styles from './PostPage.module.scss';
import { PhotosView } from '../../common/Photo/PhotosView';
import { Button } from '@mui/material';

export function PostPageView(): React.ReactElement {
  const onChange = useCallback(() => {
    console.log('change');
  }, []);
  const onCancel = useCallback(() => {
    console.log('cancel');
  }, []);
  return (
    <div className={styles.page}>
      <PhotosView />
      <div>asd</div>
      <Button onClick={onChange}>Change</Button>
      <Button onClick={onCancel}>Cancel</Button>
    </div>
  );
}
