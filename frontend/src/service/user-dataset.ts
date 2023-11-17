import axios from './axios';
import { useAppStore } from '@/store/app';
import router from '@/router';
const store = useAppStore();

const { handleGlobalMessaging } = store;

import {
    BriefDataset,
  CreateDatasetProps,
  CreateModelResData,
  Dataset,
  ResponseData,
  SetDatasetFavProps,
} from './types/request';

//创建数据集
export const createDataset = async (data: CreateDatasetProps) => {
  const res: ResponseData<{ dataId: string }> = await axios({
    method: 'post',
    url: `/dataset/creat`,
    data,
  });

  if (res.code === 200) {
    handleGlobalMessaging('创建成功,即将刷新页面');
    setTimeout(() => {
      router.go(0);
    }, 500);
  } else {
    handleGlobalMessaging('创建失败');
  }

  return res;
};

//删除数据集
export const delDataset = async (dataId: string) => {
  const res: ResponseData = await axios({
    method: 'post',
    url: `/dataset/delete`,
    data: {
      dataId,
    },
  });

  if (res.code === 200) {
    handleGlobalMessaging('删除成功，即将刷新页面');
    setTimeout(() => {
      router.go(0);
    }, 500);
  } else {
    handleGlobalMessaging('删除失败');
  }

  return res;
};


//设置数据集收藏
export const setDatasetFav = async (data: SetDatasetFavProps) => {
  const res: ResponseData = await axios({
    method: 'post',
    url: `/dataset/setfavourite`,
    data,
  });

  if (res.code !== 200) {
    handleGlobalMessaging('设置失败');
  }

  return res;
};


//根据datasetid获取数据集详细信息
export const getDatasetById = async (dataSetId: string) => {
  const res: ResponseData<Dataset> = await axios({
    method: 'post',
    url: `/dataset/getdatasetydatasetid`,
    data: {
      dataSetId,
    },
  });
  return res;
};

//获取数据集列表
export const getDatasetList = async () => {
  const res: ResponseData<BriefDataset[]> = await axios({
    method: 'post',
    url: `/dataset/getdatasetbyuserid`,
  });

  return res;
};