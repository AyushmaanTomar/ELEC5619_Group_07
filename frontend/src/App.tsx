import React from 'react';
import {
  BrowserRouter as Router,
  Routes,
  Route,
  NavLink,
} from 'react-router-dom';

import './App.css';
import HomePage from './components/home/homepage';
import ProductPage from './components/items/listingPage';
import ProductsPage from './components/items/listingsPage';
import UserProfile from './components/miscellaneous/userProfile';

function App() {
  return (
    <Router>
      <header className="navbar justify-around bg-secondary text-secondary-content border-b-2 border-b-solid border-b-primary ">
        <div className="flex-1">
          <a href="/" className="unix-intro">
            <span><img src="/assets/unix_logo.png" className='relative right-10' alt='home-logo' width="200" height="100" /></span>
          </a>
        </div>
        <div className="flex-auto">
          <ul className="menu menu-horizontal font-semibold space-x-2"> {/*(border-b-2 border-b-primary) To add underline*/} 
            <li>
              <NavLink to="/" className="transition ease-in-out delay-150 hover:-translate-y-1 hover:scale-110 hover:bg-red-500 duration-300">Home</NavLink>
            </li>
            <li>
              <NavLink to="/products/" className="transition ease-in-out delay-150 hover:-translate-y-1 hover:scale-110 hover:bg-red-500 duration-300">Products</NavLink>
            </li>
          </ul>
        </div>
        <NavLink to="/profile/" className="transition ease-in-out delay-150 hover:-translate-y-1 hover:scale-110 hover:bg-red-500 duration-300">Profile</NavLink>
      </header>
      <main className="mx-10">
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/products" element={<ProductsPage />} />
          <Route path="/products/:id" element={<ProductPage />} />
          <Route path="/profile" element={<UserProfile name="John Doe" age={20} />} />
        </Routes>
      </main>
    </Router>
  );
}

export default App;