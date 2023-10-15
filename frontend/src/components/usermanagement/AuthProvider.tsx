import React, { createContext, useContext, useState, useEffect, ReactNode, PropsWithChildren } from "react";

const AuthContext = createContext({loggedIn: false, login:(s: string, p: string) => {}, logout: () => {}, register: (a: accountDetails) => {}});

type accountDetails = {
  username: string;
  email: string;
  password: string;
}

interface AuthProviderProps {
  children: ReactNode; // Correctly specify children prop as ReactNode
}

export function AuthProvider( {children} : AuthProviderProps ) {
  const [loggedIn, setLoggedIn] = useState(false);

  const login = (username: string, password: string) => {
    // TODO: Implement password validation
    setLoggedIn(true);
    localStorage.setItem("username", username);
    return true;
  };

  const logout = () => {
    setLoggedIn(false);
    localStorage.removeItem("username");
  };

  const register = (details: accountDetails) => {
    //TODO: Implement once backend route is made
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
