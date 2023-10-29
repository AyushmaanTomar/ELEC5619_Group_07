import React from 'react';
import {
  BrowserRouter as Router,
  Routes,
  Route,
} from 'react-router-dom';

import './App.css';
import HomePage from './components/home/homepage';
import ProductPage from './components/items/listingPage';
import ProductsPage from './components/items/listingsPage';
import { AddProductsComponent } from './components/miscellaneous/addProducts';
import UserList from './components/usermanagement/userList';
import RegisterPage from './components/usermanagement/registerpage'
import LoginPage from './components/usermanagement/loginpage'
import UserProfile from './components/miscellaneous/userProfile';
import Navbar from './components/base/navbar';
import { AuthProvider } from './components/usermanagement/AuthProvider';
import { ErrorProvider } from './errorContext';
import ErrorPane from './components/miscellaneous/errorPane';
import LikePage from './components/usermanagement/likePage';
import ChangePassword from './components/usermanagement/PasswordChangePage';
import ForgotPassword from './components/usermanagement/ForgotPasswordPage';
import AdminPage from './components/Admin/AdminPage';

function App() {
  return (
    <ErrorProvider>
      <AuthProvider>
        <ErrorPane />
        <Router>
          <Navbar />
          <main className="mx-10">
            <Routes>
              <Route path="/" element={<HomePage />} />
              <Route path="/products" element={<ProductsPage />} />
              <Route path="/products/:id" element={<ProductPage />} />
              <Route path="/add" element={<AddProductsComponent />} />  
              <Route path="/users" element={<UserList />} />
              <Route path="/register" element={<RegisterPage />} />
              <Route path="/login" element={<LoginPage />} />
              <Route path="/profile" element={<UserProfile />} />
              <Route path="/like" element={<LikePage />} />
              <Route path="/changePassword" element={<ChangePassword />} />
              <Route path="/forgotPassword" element={<ForgotPassword />} />
              <Route path="/admin" element={<AdminPage />} />
            </Routes>
          </main>
        </Router>
      </AuthProvider>
    </ErrorProvider>
  );
}

export default App;