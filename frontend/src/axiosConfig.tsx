import axios from "axios";

// const axiosConfig = {
//     baseURL: "http://localhost:8080/"
// };

const api = axios.create({
    baseURL: "http://localhost:8080/", 
    timeout: 10000, 
  });

export default api;