import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { Header } from '../components/common/Header/Header';
import { ProtectedRoute } from '../components/common/ProtectedRoute/ProtectedRoute';
import { LoginForm } from '../components/pages/AuthPage/LoginForm';
import { RegisterForm } from '../components/pages/AuthPage/RegisterForm';
import { MainPage } from '../components/pages/MainPage/MainPage';
import styles from './App.module.scss';
import { PostPageView } from '../components/pages/PostPage/PostPageView';
import { PostPageCreate } from '../components/pages/PostPage/PostPageCreate';

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
            <Route path={'/post/view'} element={<PostPageView />} />
            <Route path={'/post/create'} element={<PostPageCreate />} />
          </Routes>
        </BrowserRouter>
      </main>
    </div>
  );
}
// todo добавить в url id

export default App;
