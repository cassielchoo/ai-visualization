import axios from './axios';
import { useAppStore } from '@/store/app';

const store = useAppStore();

const { handleGlobalMessaging } = store;

import {
  CreateModelResData,
  ResponseData,
  SetModelFavProps,
  SaveModelProps,
} from './request';
import { BriefModel, Model } from '@/types/model';

//创建项目
export const createModel = async (modelName: string) => {
  const res: ResponseData<CreateModelResData> = await axios({
    method: 'post',
    url: `/usermodel/creat`,
    data: {
      modelName,
    },
  });

  if (res.code === 200) {
    handleGlobalMessaging('创建成功');
  } else {
    handleGlobalMessaging('创建失败');
  }

  return res;
};

//获取模型列表
export const getModelList = async () => {
  const res: ResponseData<BriefModel[]> = await axios({
    method: 'post',
    url: `/usermodel/getmodelbyuserid`,
  });

  return res;
};

//设置模型收藏
export const setModelFav = async (data: SetModelFavProps) => {
  const res: ResponseData = await axios({
    method: 'post',
    url: `/usermodel/setfavourite`,
    data,
  });

  if (res.code !== 200) {
    handleGlobalMessaging('设置失败');
  }

  return res;
};

//根据modelid获取模型详细信息
export const getModelById = async (modelId: string) => {
  const res: ResponseData<Model> = await axios({
    method: 'post',
    url: `/usermodel/getmodelbymodelid`,
    data: {
      modelId,
    },
  });
  return res;
};

//保存模型
export const saveModel = async (data: SaveModelProps) => {
  const res: ResponseData<Model> = await axios({
    method: 'post',
    url: `/usermodel/save`,
    data,
  });
  return res;
};


