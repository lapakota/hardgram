import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { ProtectedRoute } from '../components/common/ProtectedRoute/ProtectedRoute';
import { LoginForm } from '../components/pages/AuthPage/LoginForm/LoginForm';
import { RegisterForm } from '../components/pages/AuthPage/RegisterForm/RegisterForm';
import { MainPage } from '../components/pages/MainPage/MainPage';
import styles from './App.scss';

function App() {
  return (
    <BrowserRouter>
      <div className={styles.app}>
        <Routes>
          <Route
            path="/"
            element={
              <ProtectedRoute>
                <MainPage />
              </ProtectedRoute>
            }
          />
          <Route path={'/auth/login'} element={<LoginForm />} />
          <Route path={'/auth/register'} element={<RegisterForm />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
