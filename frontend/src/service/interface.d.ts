export interface ResponseData<T = any> {
  code: number;
  data?: T;
  msg: string;
}

export interface UserLoginResData {
  tokenName: string;
  tokenValue: string;
}


export interface UserLoginProps {
  userId: string;
  userPasshash: string;
}

export interface UserRegisterProps {
  userId: string;
  userPasshash: string;
  userPhone: string;
  userName: string;
}

