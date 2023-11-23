import axios from './axios';
import { useAppStore } from '@/store/app';
import router from '@/router';
const appStore = useAppStore();
const projStore = useProjectStore();

const { handleGlobalMessaging } = appStore;

import {
  ResponseData,
  SetModelFavProps,
  SaveModelProps,
  SetModelSharedProps,
} from '../types/request';
import { BriefModel, Model } from '@/types/model';
import { useProjectStore } from '@/store/project';

//创建项目
export const createModel = async (
  modelName: string,
  templateModelName?: string,
) => {
  const res: ResponseData<{ modelId: string }> = await axios({
    method: 'post',
    url: `/usermodel/creat`,
    data: {
      modelName,
      templateModelName,
    },
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

//获取模型列表
export const getModelList = async (
  type?: 'fav' | 'shared',
): Promise<ResponseData<BriefModel[]>> => {
  if (type === 'fav') {
    return await axios({
      method: 'post',
      url: `/usermodel/getfavouritemodelbyuserid`,
    });
  } else if (type === 'shared') {
    return await axios({
      method: 'post',
      url: `/usermodel/getallsharedmodel`,
    });
  } else {
    return await axios({
      method: 'post',
      url: `/usermodel/getmodelbyuserid`,
    });
  }
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

//设置模型分享
export const setModelShared = async (data: SetModelSharedProps) => {
  const res: ResponseData = await axios({
    method: 'post',
    url: `/usermodel/setshare`,
    data,
  });

  if (res.code === 200) {
    projStore.modelInfo.isShared = data.isShared;
  } else {
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

//删除模型
export const delModel = async (modelId: string) => {
  const res: ResponseData<Model> = await axios({
    method: 'post',
    url: `/usermodel/delete`,
    data: {
      modelId,
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

//fork分享的模型
export const forkModel = async (modelId: string) => {
  const res: ResponseData<{
    modelId: string;
    lastEditTime: string;
    isFavourite: '0' | '1';
  }> = await axios({
    method: 'post',
    url: `/usermodel/copymodelbyid`,
    data: {
      modelId,
    },
  });

  if (res.code === 200) {
    handleGlobalMessaging('添加成功');
  } else {
    handleGlobalMessaging('添加失败');
  }

  return res;
};
