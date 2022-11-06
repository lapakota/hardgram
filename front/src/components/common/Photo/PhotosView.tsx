import React from 'react';
import styles from './Photo.module.scss';

interface Props {
  photos: string[];
}

export function PhotosView(): React.ReactElement {
  return <div className={styles.page}></div>;
}
