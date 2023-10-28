import { createContext, useContext, useState, useEffect, ReactNode } from "react";
import api from "../../axiosConfig";
import { useError } from "src/errorContext";

const AuthContext = createContext({loggedIn: false, login:(s: string, p: string) => {}, logout: () => {}, register: (a: accountDetails) => {}});

type accountDetails = {
  userName: string;
  email: string;
  password: string;
  phone: string;
}

interface AuthProviderProps {
  children: ReactNode; // Correctly specify children prop as ReactNode
}

export function AuthProvider( {children} : AuthProviderProps ) {
  const [loggedIn, setLoggedIn] = useState(false);
  const {showError} = useError();

  const login = async (username: string, password: string) => {
    const result = await api.post("/users/login?username=" + username + "&password=" + password)
      .then(() => {
        setLoggedIn(true);
        localStorage.setItem("username", username);
      })
      .catch((error) => {
        showError(error.response.data);
        throw "Error";
      });
  };

  const logout = () => {
    setLoggedIn(false);
    localStorage.removeItem("username");
  };

  const register = async (details: accountDetails) => {
    const result = await api.post("/users/register", details)
      .then()
      .catch((error) => {
        showError(error.response.data);
        throw "Error";
      });
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
