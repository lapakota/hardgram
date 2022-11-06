import React from 'react';
import { Navigate, useLocation } from 'react-router-dom';

export const ProtectedRoute = ({ children }: { children: React.ReactElement }) => {
  // TODO брать isAuth из user стора
  const isAuth = true;
  const location = useLocation();

  if (!isAuth) {
    return <Navigate to="/auth/login" replace state={{ from: location }} />;
  }

  return children;
};
