import { useAppStore } from '@/store/app';
import axios from 'axios';

const { handleGlobalMessaging } = useAppStore();

const instance = axios.create({
  baseURL: '/api',
  timeout: 4000,

  params: {
    //重试次数
    retry: 2,
    //重试间隔(ms)
    retryDelay: 100
  },
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
    if (response.status === 200) return response.data;
    else alert('错误');
  },
  (error) => {
    if (error.code === 'ECONNABORTED' && error.message.includes('timeout')) {
      const config = error.config;
      if (!config || !config.params.retry) {
        handleGlobalMessaging('网络超时');
        return 'timeout';
      }

      config.__retryCount = config.__retryCount || 0;

      if (config.__retryCount >= config.params.retry) {
        // 超时重发的次数已用完
        handleGlobalMessaging('网络超时');
        return 'timeout';
      }

      // 定义一个延迟的方法，也可以单独抽离出来做公共方法
      const backoff = new Promise<void>((resolve) => {
        setTimeout(() => {
          resolve();
        }, config.params.retryDelay || 1000);
      });

      // 延迟时间到后重新发起请求
      return backoff.then(() => {
        config.__retryCount++;
        return instance(config);
      });
    }
  },
);

export default instance;
