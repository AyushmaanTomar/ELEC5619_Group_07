import axios from 'axios';

const BASE_URL = "http://localhost:8080/users";  // Ensure this matches your backend's URL and port

export const setUserProfileImage = async (userId, imagePath) => {
    try {
        const response = await axios.put(`${BASE_URL}/${userId}/profileImage`, { imagePath });
        return response.data;
    } catch (error) {
        if (error.response) {
            // The request was made and the server responded with a status code outside of the range of 2xx
            throw new Error(`Error ${error.response.status}: ${error.response.data}`);
        } else if (error.request) {
            // The request was made but no response was received
            throw new Error('No response received from the server.');
        } else {
            // Something happened in setting up the request
            throw new Error(`Request error: ${error.message}`);
        }
    }
};

export const getUserProfileImage = async (userId) => {
    try {
        const response = await axios.get(`${BASE_URL}/${userId}/profileImage`);
        return response.data;
    } catch (error) {
        if (error.response) {
            throw new Error(`Error ${error.response.status}: ${error.response.data}`);
        } else if (error.request) {
            throw new Error('No response received from the server.');
        } else {
            throw new Error(`Request error: ${error.message}`);
        }
    }
};
