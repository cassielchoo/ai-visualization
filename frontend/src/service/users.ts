import axios from './axios';
import sha256 from 'crypto-js/sha256';
import CryptoJS from 'crypto-js';
import { useAppStore } from '@/store/app';
import defaultAvatar from '@/assets/default_avatar.png';

const store = useAppStore();

const { saveLoginInfo, handleGlobalMessaging } = store;

import {
  UserRegisterProps,
  UserLoginProps,
  ResponseData,
  UserLoginResData,
} from './types/request';
import { User } from '../types/user';

//用户登录
export const userLogin = async (data: UserLoginProps) => {
  const res: ResponseData<UserLoginResData> = await axios({
    method: 'post',
    url: `/user/login`,
    data: {
      ...data,
      userPasshash: sha256(data.userPasshash).toString(CryptoJS.enc.Hex),
    },
  });
  if (res.code === 200) {
    saveLoginInfo(res.data!.tokenValue);
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

//用户注册
export const userRegister = async (data: UserRegisterProps) => {
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

//获取用户信息
export const getUserInfo = async () => {
  const res: ResponseData<User> = await axios({
    method: 'get',
    url: `/user/info`,
  });

  res.data!.userPhoto = res.data?.userPhoto || defaultAvatar;
  return res;
};
