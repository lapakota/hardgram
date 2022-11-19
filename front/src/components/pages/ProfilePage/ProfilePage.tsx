import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import styles from './ProfilePage.module.scss';
import { UserInfo } from '../../../typescript/models/User/UserInfo';
import { getUserById } from '../../../api/user/userApi';
import { Spinner } from '../../common/Spinner/Spinner';

export function ProfilePage(): React.ReactElement {
  const { userId } = useParams<{ userId: string }>();
  const [userInfo, setUserInfo] = useState<UserInfo | undefined>();

  useEffect(() => {
    const fetchPosts = async () => {
      if (!userId || userInfo) return;
      const info = await getUserById(userId);
      setUserInfo(info);
    };
    fetchPosts();
  }, []);

  return (
    <div className={styles.root}>
      {userInfo ? (
        <div className={styles.content}>
          <h1>
            Profile of: {userInfo.nickname} {userInfo.fullName} {userInfo.id}
          </h1>
          <h2>Posts:</h2>
          <ul>
            {userInfo?.posts.map((x) => (
              <li key={x.postId}>
                {x.postId} {x.description}
              </li>
            ))}
          </ul>
        </div>
      ) : (
        <Spinner />
      )}
    </div>
  );
}
