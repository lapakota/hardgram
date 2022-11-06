import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { Header } from '../components/common/Header/Header';
import { ProtectedRoute } from '../components/common/ProtectedRoute/ProtectedRoute';
import { LoginForm } from '../components/pages/AuthPage/LoginForm';
import { RegisterForm } from '../components/pages/AuthPage/RegisterForm';
import { MainPage } from '../components/pages/MainPage/MainPage';
import styles from './App.module.scss';

function App() {
  return (
    <div className={styles.app}>
      <Header />
      <main className={styles.main}>
        <BrowserRouter>
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
        </BrowserRouter>
      </main>
    </div>
  );
}

export default App;
