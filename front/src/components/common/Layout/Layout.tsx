import React from 'react';
import styles from './Layout.module.scss';
import classNames from 'classnames';

interface Props {
  children: React.ReactElement;
  className?: string;
}

export const Layout = ({ children, className }: Props) => {
  return <div className={classNames(styles.layout, className)}>{children}</div>;
};
