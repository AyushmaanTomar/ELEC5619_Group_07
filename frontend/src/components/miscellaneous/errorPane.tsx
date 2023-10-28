import React from "react";
import { useError } from "../../errorContext";

function ErrorPane() {
  const { error, clearError } = useError();

  if (!error) {
    return null;
  }

  return (
    <div className="fixed top-0 left-0 w-full h-full flex items-center justify-center text-center z-50">
      <div
        className="bg-gray-900 bg-opacity-50 absolute inset-0"
        onClick={clearError}
      ></div>
      <div className="bg-white w-80 p-4 rounded-sm shadow-lg z-50">
        <div className="text-gray-700 text-lg mb-4">{error}</div>
        <button
          className="bg-gray-700 shadow-lg text-gray-100 px-4 py-2 rounded-md hover:bg-red-800"
          onClick={clearError}
        >
          Close
        </button>
      </div>
    </div>
  );
}

export default ErrorPane;