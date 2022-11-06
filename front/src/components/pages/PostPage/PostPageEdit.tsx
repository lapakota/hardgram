import React, { useCallback } from 'react';
import styles from './PostPage.module.scss';
import { Button } from '@mui/material';
import { PhotosEdit } from '../../common/Photo/PhotosEdit';

export function PostPageEdit(): React.ReactElement {
  const onChange = useCallback(() => {}, []);
  const onCancel = useCallback(() => {}, []);
  return (
    <div className={styles.page}>
      <PhotosEdit />
      <div>as</div>
      <Button onClick={onChange}>Change</Button>
      <Button onClick={onCancel}>Cancel</Button>
    </div>
  );
}
