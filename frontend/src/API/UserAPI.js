import axios from 'axios';

const BASE_URL = "http://localhost:8080/users";  // Update this if your backend is on a different port or host

export const setUserProfileImage = async (userId, imagePath) => {
    try {
        const response = await axios.put(`${BASE_URL}/${userId}/profileImage`, { imagePath });
        return response.data;
    } catch (error) {
        throw error;
    }
};

export const getUserProfileImage = async (userId) => {
    try {
        const response = await axios.get(`${BASE_URL}/${userId}/profileImage`);
        return response.data;
    } catch (error) {
        throw error;
    }
};

