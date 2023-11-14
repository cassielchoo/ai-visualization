import axios from './axios';
import { useAppStore } from '@/store/app';
import {
  ResponseData,
  kMeansModelProps,
  kMeansModelResData,
  RandomForestModelProps,
  RandomForestModelResData,
  CatBoostModelResData,
  CNNModelProps,
  CNNResData,
  FullConnectModelProps,
  FullConnectResData,
  LightGBMResData,
  RNNResData,
  XgBoostResData,
  XgBoostModelProps,
  PredProps,
  LightGBMProps,
  CatBoostModelProps,
  RNNProps,
} from './request';

const store = useAppStore();

const { handleGlobalMessaging } = store;

//Kmeans模型
export const kMeansModel = async (data: kMeansModelProps) => {
  const res: ResponseData<kMeansModelResData> = await axios({
    method: 'post',
    url: `/model/Kmeans`,
    data,
  });
  return res;
};

//RandomForest模型
export const randomForestModel = async (data: RandomForestModelProps) => {
  const res: ResponseData<RandomForestModelResData> = await axios({
    method: 'post',
    url: `/model/RandomForest`,
    data,
  });
  return res;
};

//CatBoost模型
export const catBoostModel = async (data: CatBoostModelProps) => {
  const res: ResponseData<CatBoostModelResData> = await axios({
    method: 'post',
    url: `/model/Catboost`,
    data
  });
  return res;
};

//CNN模型
export const CNNModel = async (data: CNNModelProps) => {
  const res: ResponseData<CNNResData> = await axios({
    method: 'post',
    url: `/model/CNN`,
    data,
  });
  return res;
};

//FullConnect模型
export const fullConnectModel = async (data: FullConnectModelProps) => {
  const res: ResponseData<FullConnectResData> = await axios({
    method: 'post',
    url: `/model/FullConnect`,
    data,
  });
  return res;
};

//LightGBM模型
export const lightGBMModel = async (data: LightGBMProps) => {
  const res: ResponseData<LightGBMResData> = await axios({
    method: 'post',
    url: `/model/LightGBM`,
    data
  });
  return res;
};

//RNN模型
export const RNNModel = async (data: RNNProps) => {
  const res: ResponseData<RNNResData> = await axios({
    method: 'post',
    url: `/model/RNN`,
    data
  });
  return res;
};

//XgBoost模型
export const xgBoostModel = async (data: XgBoostModelProps) => {
  const res: ResponseData<XgBoostResData> = await axios({
    method: 'post',
    url: `/model/Xgboost`,
    data,
  });
  return res;
};



//预测
export const handlePred = async (data: PredProps) => {
  const res: ResponseData = await axios({
    method: 'post',
    url: `/model/Pred`,
    data,
  });
  return res;
};