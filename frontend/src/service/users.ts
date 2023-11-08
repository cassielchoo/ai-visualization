import axios from './axios';
import sha256 from 'crypto-js/sha256';
import CryptoJS from 'crypto-js';
import { useAppStore } from '@/store/app';

const store = useAppStore();
const { saveLoginInfo, handleGlobalMessaging } = store;

import {
  UserRegisterProps,
  UserLoginProps,
  ResponseData,
  UserLoginResData,
} from './interface';

//用户登录
export const userLogin = async (
  data: UserLoginProps,
): Promise<ResponseData<UserLoginResData>> => {
  const res: ResponseData<UserLoginResData> = await axios({
    method: 'post',
    url: `/user/login`,
    data: {
      ...data,
      userPasshash: sha256(data.userPasshash).toString(CryptoJS.enc.Hex),
    },
  });
  if (res.code === 200) {
    saveLoginInfo(res.data!.tokenValue, data.userId);
    handleGlobalMessaging('登陆成功');
  }
  if (res.code === 403) {
    if (res.msg === 'Account Did Not Exist') {
      handleGlobalMessaging('用户不存在');
    }
    if (res.msg === 'Password Incorrect') {
      handleGlobalMessaging('密码错误');
    }
  }
  return res;
};

export const userRegister = async (
  data: UserRegisterProps,
): Promise<ResponseData> => {
  const res: ResponseData = await axios({
    method: 'post',
    url: `/user/register`,
    data: {
      ...data,
      userPasshash: sha256(data.userPasshash).toString(CryptoJS.enc.Hex),
    },
  });

  if (res.code === 200) {
    handleGlobalMessaging('注册成功');
  }
  if (res.code === 403) {
    handleGlobalMessaging('用户已存在');
  }

  return res;
};
