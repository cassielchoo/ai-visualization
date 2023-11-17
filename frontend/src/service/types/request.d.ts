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

export interface CreateModelResData {
  modelId: string;
}

export interface SetModelFavProps {
  modelId: string;
  isFavourite: '0' | '1';
}

export interface SaveModelProps {
  modelId: string;
  datajson: string;
  thumbnailUrl: string;
}

export interface CreateDatasetProps {
  dataName: string;
  dataURL: string;
}

export interface SetDatasetFavProps {
  dataId: string;
  isFavourite: '0' | '1';
}

export interface BriefDataset {
  dataSetId: string;
  dataSetName: string;
}

export interface Dataset {
  dataId: string;
  dataName: string;
  dataURL: string;
  isFavourite: '0'|'1';
  userId: string;
}
