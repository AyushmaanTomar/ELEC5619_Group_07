import React, { createContext, useContext, useState, useEffect, ReactNode } from "react";
import axios from 'axios';

const AuthContext = createContext({loggedIn: false, login:(s: string, p: string): boolean => {return false}, logout: () => {}, register: (a: accountDetails) => {}});

type accountDetails = {
  username: string;
  email: string;
  password: string;
  phone: string;
}

interface AuthProviderProps {
  children: ReactNode; // Correctly specify children prop as ReactNode
}

export function AuthProvider( {children} : AuthProviderProps ) {
  const [loggedIn, setLoggedIn] = useState(false);

  const login = (username: string, password: string): boolean => {
    // TODO: Implement password validation
    setLoggedIn(true);
    localStorage.setItem("username", username);
    return true;
  };

  const logout = () => {
    setLoggedIn(false);
    localStorage.removeItem("username");
  };

  const register = async (details: accountDetails) => {
    const result = await axios.post("/createsUser", details)
    .catch((error) => console.log(error));
  }

  //   Need a useeffect to set logged
  useEffect(() => {
    if (localStorage.getItem("username") != null) {
      setLoggedIn(true);
    }
  }, []);

  return (
    <AuthContext.Provider value={{ loggedIn, login, logout, register }}>
      {children}
    </AuthContext.Provider>
  );
}

export function useAuth() {
  return useContext(AuthContext);
}
