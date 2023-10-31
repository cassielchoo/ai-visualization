export interface ToolClass {
  value: string;
  type: string;
  title: string;
  icon: string;
  color: string;
  children: Tool[];
}

export interface Tool {
  name: string;
  value?: string;
}

export const toolbardata: ToolClass[] = [
  {
    value: 'import',
    type: 'input',
    title: '数据导入',
    icon: 'mdi-file-download-outline',
    color: '#519f9e',
    children: [
      {
        name: '从数据库导入',
        value: 'importfromdatabase',
      },
      {
        name: '从 csv/excel 导入',
        value: 'importfromtable',
      },
      {
        name: '非结构化数据导入',
        value: 'importfromfile',
      },
    ],
  },
  {
    value: 'preprocess',
    type: 'default',
    title: '数据预处理',
    icon: 'mdi-folder-outline',
    color: '#9d80bb',
    children: [
      {
        name: '缺失值处理',
      },
      {
        name: '异常值处理',
      },
      {
        name: '数据筛选',
      },
      {
        name: '增删数据',
      },
      {
        name: '数据合并',
      },
      {
        name: '数据集划分',
      },
      {
        name: '文本分词',
      },
      {
        name: '图像分割',
      },
      {
        name: '灰度处理',
      },
    ],
  },
  {
    value: 'feature',
    type: 'default',
    title: '特征工程',
    icon: 'mdi-check-all',
    color: '#c6577a',
    children: [
      {
        name: '数据归一化',
      },
      {
        name: '数据标准化',
      },
      {
        name: '数据离散化',
      },
      {
        name: 'onehot编码',
      },
      {
        name: 'label编码',
      },
      {
        name: '描述性统计',
      },
      {
        name: '词向量转化',
      },
      {
        name: '相似度计算',
      },
      {
        name: '图像增强',
      },
    ],
  },
  {
    value: 'model',
    type: 'default',
    title: '模型选择',
    icon: 'mdi-memory',
    color: '#c39949',
    children: [
      {
        name: '随机森林',
      },
      {
        name: 'lightGBM',
      },
      {
        name: 'Xgboost',
      },
      {
        name: 'Catboost',
      },
      {
        name: 'K-means',
      },
      {
        name: '全连接神经网络',
      },
      {
        name: '卷积神经网络',
      },
      {
        name: '循环神经网络',
      },
      {
        name: '图神经网络',
      },
    ],
  },
  {
    value: 'indictator',
    type: 'output',
    title: '评估指标',
    icon: 'mdi-chart-line',
    color: '#6cbc55',
    children: [
      {
        name: '精准率计算',
      },
      {
        name: '召回率计算',
      },
      {
        name: 'F1值计算',
      },
      {
        name: 'AUC计算',
      },
      {
        name: 'ROC计算',
      },
      {
        name: 'MSE计算',
      },
      {
        name: 'MAPE计算',
      },
    ],
  },
];
