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

export interface SetModelFavProps {
  modelId: string;
  isFavourite: '0' | '1';
}

export interface SetModelSharedProps {
  modelId: string;
  isShared: '0' | '1';
}

export interface SaveModelProps {
  modelId: string;
  datajson: string;
  thumbnailUrl: string;
}

export interface CreateDatasetProps {
  dataName: string;
  dataURL: string;
  dataDescribe: string;
}

export interface SetDatasetFavProps {
  dataId: string;
  isFavourite: '0' | '1';
}

export interface SetDatasetSharedProps {
  dataId: string;
  isShared: '0' | '1';
}

export interface BriefDataset {
  dataId: string;
  dataName: string;
  isFavourite: '0' | '1';
  isShared: '0' | '1';
  dataDescribe: string;
}

export interface Dataset extends BriefDataset {
  dataURL: string;
  userId: string;
}
