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
  isFavourite: string;
  modelId: string;
}

export interface SaveModelProps {
  modelId: string;
  datajson: string;
  thumbnailUrl: string;
}

export interface kMeansModelProps {
  NUmClusters: number;
}

export interface kMeansModelResData {
  performance: {
    center1: string;
    center2: string;
    center3: string;
    SE: string;
    cluster_1: string;
    cluster_2: string;
    cluster_3: string;
  };
}

export interface RandomForestModelProps {
  NumTrees: number;
  depth: number;
  NumSeed: number;
  NumAttribute: number;
}

export interface RandomForestModelResData {
  performance: {
    precision: number;
    recall: number;
    f1score: number;
    MSE: number;
    MAE: number;
    CC: number;
    RRSE: number;
    RAE: number;
  };
}

export interface CatBoostModelProps {
  eta: number;
  depth: number;
  nEpochs: number;
  objFunc: string;
}

export interface CatBoostModelResData {
  performance: {
    precision: number;
    recall: number;
    f1score: number;
  };
}

export interface CNNModelProps {
  learningRate: number;
  batchSize: number;
  nEpochs: number;
  numHiddenNodes: number;
}

export interface CNNResData {
  loss: {
    1: number;
    50: number;
  };
  performance: {
    Accuracy: number;
    Recall: number;
    'F1 Score': number;
    Precision: number;
  };
}

export interface FullConnectModelProps {
  learningRate: number;
  batchSize: number;
  nEpochs: number;
  numHiddenNodes: number;
}

export interface FullConnectResData {
  loss: {
    1: number;
    50: number;
  };
  performance: {
    Accuracy: number;
    Recall: number;
    'F1 Score': number;
    Precision: number;
  };
}

export interface LightGBMProps {
  learningRate: number;
  nEpochs: number;
  depth: number;
  objFunc: string;
}

export interface LightGBMResData {
  loss: {
    22: number;
    23: number;
  };
  performance: {
    Accuracy: number;
    Recall: number;
    'F1 Score': number;
    Precision: number;
  };
}

export interface RNNProps {
  learningRate: number;
  batchSize: number;
  nEpochs: number;
  numHiddenNodes: number;
}

export interface RNNResData {
  loss: {
    1: number;
    50: number;
  };
  performance: {
    recall: number;
    f1score: number;
    precision: number;
  };
}

export interface XgBoostModelProps {
  eta: number;
  depth: number;
  nEpochs: number;
  objFunc: string;
}

export interface XgBoostResData {
  performance: {
    recall: number;
    f1score: number;
    precision: number;
  };
}

export interface PredProps {
  modelType: string;
  name: string;
  sepal_length: number;
  sepal_width: number;
  petal_length: number;
  petal_width: number;
}
