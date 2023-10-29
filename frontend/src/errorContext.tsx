import React, { ReactNode, createContext, useContext, useState } from "react";

const ErrorContext = createContext({error: "", showError: (string: string) => {}, clearError: () => {}});

export function useError() {
  return useContext(ErrorContext);
}

interface AuthProviderProps {
  children: ReactNode; 
}

export function ErrorProvider( {children}: AuthProviderProps) {
  const [error, setError] = useState("");

  const showError = (errorMessage: string) => {
    setError(errorMessage);
  };

  const clearError = () => {
    setError("");
  };

  return (
    <ErrorContext.Provider value={{ error, showError, clearError }}>
      {children}
    </ErrorContext.Provider>
  );
}
