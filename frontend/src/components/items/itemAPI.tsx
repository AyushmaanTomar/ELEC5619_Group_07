import { Item } from './listings';
import { useError } from 'src/errorContext';
import api from 'src/axiosConfig';

// const { showError } = useError();

function translateStatusToErrorMessage(status: number): string {
  switch (status) {
    case 401:
      return 'Please login again.';
    case 403:
      return 'You do not have permission to view the product(s).';
    default:
      return 'There was an error retrieving the product(s). Please try again.';
  }
}

function handleAxiosError(error: any): never {
  if (error.response) {
    console.log(`log server http error: ${JSON.stringify(error.response.data)}`);

    let errorMessage = translateStatusToErrorMessage(error.response.status);
    throw new Error(errorMessage);
  } else if (error.request) {
    throw new Error('No response received from the server.');
  } else {
    throw error;
  }
}

function convertToProductModels(data: any[]): Item[] {
  return data.map(item => new Item(item));
}

const productAPI = {

  async get(page = 1, limit = 12): Promise<Item[]> {
    try {
      // const response = await api.get(`${url}?_page=${page}&_limit=${limit}&_sort=name`);
      const response = await api.get("/admin/listItem");
      return convertToProductModels(response.data);
    } catch (error) {
      handleAxiosError(error);
    }
  },

  async find(id: number): Promise<Item> {
    try {
      const response = await api.get(`/items/${id}`);
      return new Item(response.data);
    } catch (error) {
      handleAxiosError(error);
    }
  },

  async put(product: Item): Promise<any> {  // TODO: Update endpoint not create
    try {
      const response = await api.post(`/items/create/${product}`, product, {
        headers: {
          'Content-Type': 'application/json',
        },
      });

      // Introduce delay
      // await new Promise(resolve => setTimeout(resolve, 2000));

      return response.data;
    } catch (error) {
      handleAxiosError(error);
    }
  },
  // async getTotalLikesForItem(itemId: number): Promise<number> {
  //   try {
  //     const response = await api.get(`/api/likes/count/byItem/${itemId}`);
  //     return response.data;
  //   } catch (error) {
  //     handleAxiosError(error);
  //   }
  // },
};

export { productAPI };


// import api from 'src/axiosConfig';
// import { Item } from './listings';
// import { useError } from 'src/errorContext';

// const baseUrl = 'http://localhost:4000';
// const url = `${baseUrl}/products`;

// function translateStatusToErrorMessage(status: number) {
//   switch (status) {
//     case 401:
//       return 'Please login again.';
//     case 403:
//       return 'You do not have permission to view the project(s).';
//     default:
//       return 'There was an error retrieving the project(s). Please try again.';
//   }
// }

// function checkStatus(response: any) {
//   if (response.ok) {
//     return response;
//   } else {
//     const httpErrorInfo = {
//       status: response.status,
//       statusText: response.statusText,
//       url: response.url,
//     };
//     console.log(`log server http error: ${JSON.stringify(httpErrorInfo)}`);

//     let errorMessage = translateStatusToErrorMessage(httpErrorInfo.status);
//     throw new Error(errorMessage);
//   }
// }

// function parseJSON(response: Response) {
//   return response.json();
// }

// function delay(ms: number) {
//   return function (x: any): Promise<any> {
//     return new Promise((resolve) => setTimeout(() => resolve(x), ms));
//   };
// }

// function convertToProjectModels(data: any[]): Item[] {
//   let products: Item[] = data;
//   return products;
// }

// function convertToProjectModel(item: any): Item {
//   return new Item(item);
// }

// const productAPI = {
//   get(page = 1, limit = 12) {
//     return (
//       fetch(`${url}?_page=${page}&_limit=${limit}&_sort=name`)
//         .then(checkStatus)
//         .then(parseJSON)
//         .then(convertToProjectModels)
//         .catch((error: TypeError) => {
//           console.log('log client error ' + error);
//           throw new Error(
//             'There was an error retrieving the projects. Please try again.'
//           );
//         })
//     );
//   },

//   find(id: number): Promise<Item> {
//     return fetch(`${url}/${id}`)
//       .then(checkStatus)
//       .then(parseJSON)
//       .then(convertToProjectModel);
//   },

//   put(project: Item) {
//     return fetch(`${url}/${project.id}`, {
//       method: 'PUT',
//       body: JSON.stringify(project),
//       headers: {
//         'Content-Type': 'application/json',
//       },
//     })
//       .then(delay(2000))
//       .then(checkStatus)
//       .then(parseJSON)
//       .catch((error: TypeError) => {
//         console.log('log client error ' + error);
//         throw new Error(
//           'There was an error updating the project. Please try again.'
//         );
//       });
//   },
  
//   add(product: Item) {
//     return fetch(url, {
//       method: 'POST',
//       body: JSON.stringify(product),
//       headers: {
//         'Content-Type': 'application/json',
//       },
//     })
//     .then(checkStatus)
//     .then(parseJSON)
//     .catch((error: TypeError) => {
//       console.log('log client error ' + error);
//       throw new Error(
//         'There was an error adding the product. Please try again.'
//       );
//     });
//   },
// };

// export { productAPI };