export interface BriefModel {
  UserName: string;
  modelId: string;
  lastEditTime: string;
  isFavourite: string;
  thumbnailUrl: string;
  modelName: string;
}

export interface Model extends BriefModel {
  userId: string;
  dataJson: string;
  creatTime: string;
  modelURL: string;
}
