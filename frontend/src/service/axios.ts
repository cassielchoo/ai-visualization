import axios from 'axios';

const instance = axios.create({
  baseURL: '/api',
  timeout: 8000,
});

// 请求拦截器
instance.interceptors.request.use(
  (config) => {
    // 在发送请求之前做些什么，例如在header中加入token验证
    //let token = localStorage.getItem('token')
    //let userId = localStorage.getItem('userId')
    //if (token) {
    //config.headers.Authorization = token;
    //config.headers.userId = userId;
    //}

    return config;
  },
  (error) => {
    // 对请求错误做些什么
    return Promise.reject(error);
  },
);

// 响应拦截器
instance.interceptors.response.use(
  (response) => {
    if (response.status === 200)
      return response.data;
    else alert('错误')
  },
  (error) => {
    // 对响应错误做点什么
    return Promise.reject(error);
  },
);



export default instance;
