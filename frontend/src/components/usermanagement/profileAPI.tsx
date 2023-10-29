import api from 'src/axiosConfig';
import { User } from './userClass';
import { useError } from 'src/errorContext';
import { useEffect } from 'react';

const baseUrl = 'http://localhost:4000';
const url = `${baseUrl}/profile`;

function translateStatusToErrorMessage(status: number) {
  switch (status) {
    case 401:
      return 'Please login again.';
    default:
      return 'There was an error retrieving profile information. Please try again.';
  }
}

function checkStatus(response: any) {
  if (response.ok) {
    return response;
  } else {
    const httpErrorInfo = {
      status: response.status,
      statusText: response.statusText,
      url: response.url,
    };
    console.log(`log server http error: ${JSON.stringify(httpErrorInfo)}`);

    let errorMessage = translateStatusToErrorMessage(httpErrorInfo.status);
    throw new Error(errorMessage);
  }
}

function parseJSON(response: Response) {
  return response.json();
}

// eslint-disable-next-line
function delay(ms: number) {
  return function (x: any): Promise<any> {
    return new Promise((resolve) => setTimeout(() => resolve(x), ms));
  };
}

function convertToProjectModels(data: any): User {
  let profile: User = data;
  return profile;
}

const getUserProfile = async () => {
  const username = localStorage.getItem('username')
  const a = await api.post('/users/getProfile?username=' + username).then((response) => {
    return response.data.profile;
  }).catch((error) => {
    throw(error.response.data);
  });
  return a;
}

const profile = {
  username: "",
  email: "",
  phoneNumber: "",
}

const userAPI = () => {
    // try {
    //   const data: any = await getUserProfile();
    //   console.log(data);
    //   profile.username = data.username;
    //   profile.email = data.email;
    //   profile.phoneNumber = data.phoneNumber;
    // } catch (error) {
    //   throw error;
    // }
    getUserProfile().then(
      (data: any) => {
        profile.username = data.name;
        profile.email = data.email;
        profile.phoneNumber = data.phone;
      }
    ).catch((error) => { throw error; })
    return new User({username: profile.username, email: profile.email, phoneNumber: profile.phoneNumber, imageUrl: "/assets/profile.png"});
  
};

export { userAPI };