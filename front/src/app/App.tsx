import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { Header } from '../components/common/Header/Header';
import { ProtectedRoute } from '../components/common/ProtectedRoute/ProtectedRoute';
import { LoginForm } from '../components/pages/AuthPage/Forms/LoginForm';
import { RegisterForm } from '../components/pages/AuthPage/Forms/RegisterForm';
import { MainPage } from '../components/pages/MainPage/MainPage';
import styles from './App.module.scss';
import { PostPageView } from '../components/pages/PostPage/PostPageView';
import { PostPageCreate } from '../components/pages/PostPage/PostPageCreate';
import { ProfilePage } from '../components/pages/ProfilePage/ProfilePage';
import { AuthPage } from '../components/pages/AuthPage';
import { SettingsPage } from '../components/pages/SettingsPage/SettingsPage';

function App() {
  return (
    <BrowserRouter>
      <div className={styles.app}>
        <Header />
        <main className={styles.main}>
          <Routes>
            <Route path={'/auth'} element={<AuthPage />}>
              <Route path={'login'} element={<LoginForm />} />
              <Route path={'register'} element={<RegisterForm />} />
            </Route>
            <Route path={'/post/view'} element={<PostPageView />} />
            <Route path={'/post/create'} element={<PostPageCreate />} />
            <Route
              path="/user/profile/:nickname"
              element={
                <ProtectedRoute>
                  <ProfilePage />
                </ProtectedRoute>
              }
            />
            <Route
              path="/user/settings/"
              element={
                <ProtectedRoute>
                  <SettingsPage />
                </ProtectedRoute>
              }
            />
            <Route
              path="/"
              element={
                <ProtectedRoute>
                  <MainPage />
                </ProtectedRoute>
              }
            />
          </Routes>
        </main>
      </div>
    </BrowserRouter>
  );
}

export default App;
