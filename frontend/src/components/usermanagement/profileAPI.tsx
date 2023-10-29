import { User } from './userClass';

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


const userAPI = {
  
  get(page = 1, limit = 12) {
    return new User({username: localStorage.getItem('username'), email: localStorage.getItem('username')?.substring(0,4).toLowerCase() + '0274@uni.sydney.edu.au', phoneNumber: '0494567891', imageUrl: "/assets/profile.png"});
    return (
      fetch(`${url}?_page=${page}&_limit=${limit}&_sort=name`)
        // .then(delay(2000))
        .then(checkStatus)
        .then(parseJSON)
        .then(convertToProjectModels)
        .catch((error: TypeError) => {
          console.log('log client error ' + error);
          throw new Error(
            'There was an error retrieving the projects. Please try again.'
          );
        })
    );
  },

  
};

export { userAPI };