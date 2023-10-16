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
import RegisterPage from './components/usermanagement/registerpage'
import LoginPage from './components/usermanagement/loginpage'
import UserProfile from './components/miscellaneous/userProfile';
import Navbar from './components/base/navbar';
import { AuthProvider } from './components/usermanagement/AuthProvider';

function App() {
  return (
    <AuthProvider>
      <Router>
        <Navbar />
        <main className="mx-10">
          <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/products" element={<ProductsPage />} />
            <Route path="/products/:id" element={<ProductPage />} />
            <Route path="/register" element={<RegisterPage />} />
            <Route path="/login" element={<LoginPage />} />
            <Route path="/profile" element={<UserProfile />} />
          </Routes>
        </main>
      </Router>
    </AuthProvider>
  );
}

export default App;