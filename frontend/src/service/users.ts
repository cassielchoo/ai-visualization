import axios from './axios';
import sha256 from 'crypto-js/sha256';
import CryptoJS from 'crypto-js';

import { UserRegisterProps, UserLoginProps } from './interface';

//用户登录
export const userLogin = (data: UserLoginProps) => {
  data.userPasshash = sha256(data.userPasshash).toString(CryptoJS.enc.Hex);
  return axios({
    method: 'post',
    url: `/user/login`,
    data,
  });
};

export const userRegister = (data: UserRegisterProps) => {
  return axios({
    method: 'post',
    url: `/user/register`,
    data,
  });
};
