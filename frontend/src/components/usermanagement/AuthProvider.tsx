import { createContext, useContext, useState, useEffect, ReactNode } from "react";
import api from "../../axiosConfig";
import { useError } from "src/errorContext";

const AuthContext = createContext({
  loggedIn: false,
  loggedInEmail: null,
  login: (s: string, p: string) => {},
  logout: () => {},
  register: (a: accountDetails) => {},
  changePassword: (loggedInEmail: string, currentPassword: string, newPassword: string) => {}
});

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
  const [loggedInEmail, setLoggedInEmail] = useState(null);
  const [loggedIn, setLoggedIn] = useState(false);
  const {showError} = useError();

  const login = async (userName: string, password: string) => {
    try {
      const result = await api.post("/users/login?username=" + userName + "&password=" + password);

      if (result && result.data && result.data.email) {
        setLoggedInEmail(result.data.email); // Assuming the email is returned from your API
      }

      setLoggedIn(true);
      localStorage.setItem("username", userName);

    } catch (error: any) {
      showError(error.response.data);
      throw "Error"
    }
  };

  const logout = () => {
    setLoggedIn(false);
    localStorage.removeItem("username");
  };

  const changePassword = async (loggedInEmail:string, currentPassword: string, newPassword: string) => {
    const user = {
      email: loggedInEmail,
      currentPassword: currentPassword
    };

    await api.put("/users/changePassword", user, {
      params: {
        newPassword: newPassword
      }
    })
        .then(() => {
          alert("Password changed successfully!");
        })
        .catch((error) => {
          console.log("Error:", error);
          if (error && error.response && error.response.data) {
            showError(error.response.data);
          } else {
            showError("An unexpected error occurred.");
          }
          throw "Error";
        });
  };


  const register = async (details: accountDetails) => {
    const result = await api.post("/users/register", details)
      .then()
      .catch((error) => {
        showError(error.response.data);
        throw "Error";
      });
  }

  useEffect(() => {
    if (localStorage.getItem("username") != null) {
      setLoggedIn(true);
    }
  }, []);

  return (
      <AuthContext.Provider value={{ loggedIn, loggedInEmail, login, logout, register, changePassword }}>
        {children}
      </AuthContext.Provider>
  );
}

export function useAuth() {
  return useContext(AuthContext);
}
