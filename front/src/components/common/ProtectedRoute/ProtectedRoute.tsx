import React from 'react';
import { Navigate, useLocation } from 'react-router-dom';
import { useStores } from '../../../hooks/useStores';
import { observer } from 'mobx-react-lite';

export const ProtectedRoute = observer(({ children }: { children: React.ReactElement }) => {
  const { userInfoStore } = useStores();
  const location = useLocation();

  if (!userInfoStore.token) {
    return <Navigate to="/auth/login" replace state={{ from: location }} />;
  }

  return children;
});
