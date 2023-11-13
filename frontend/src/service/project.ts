import axios from './axios';
import { useAppStore } from '@/store/app';

const store = useAppStore();

const {  handleGlobalMessaging } = store;

import {
  ResponseData,
} from './interface';

//创建项目
export const userLogin = async (projectName:string) => {
  const res: ResponseData = await axios({
    method: 'post',
    url: `/usermodel/creat`,
    data: {
      projectName,
      dataJson:""
    },
  });

  return res;
};