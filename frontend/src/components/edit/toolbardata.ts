export interface ToolClass {
  value: string;
  type: string;
  title: string;
  icon: string;
  color: string;
  accentColor:string;
  children: Tool[];
}

export interface Tool {
  name: string;
  hasOptions: boolean;
}

export const toolbardata: ToolClass[] = [
  {
    value: 'import',
    type: 'input',
    title: '数据导入',
    icon: 'mdi-file-download-outline',
    color: '#519f9e',
    accentColor: '#376c6b',
    children: [
      {
        name: '从数据库导入',
        hasOptions: true,
      },
      {
        name: '从 csv/excel 导入',
        hasOptions: true,
      },
      {
        name: '非结构化数据导入',
        hasOptions: true,
      },
    ],
  },
  {
    value: 'preprocess',
    type: 'default',
    title: '数据预处理',
    icon: 'mdi-folder-outline',
    color: '#9d80bb',
    accentColor: '#725d87',
    children: [
      {
        name: '缺失值处理',
        hasOptions: false,
      },
      {
        name: '异常值处理',
        hasOptions: false,
      },
      {
        name: '数据筛选',
        hasOptions: false,
      },
      {
        name: '增删数据',
        hasOptions: false,
      },
      {
        name: '数据合并',
        hasOptions: false,
      },
      {
        name: '数据集划分',
        hasOptions: false,
      },
      {
        name: '文本分词',
        hasOptions: false,
      },
      {
        name: '图像分割',
        hasOptions: false,
      },
      {
        name: '灰度处理',
        hasOptions: false,
      },
    ],
  },
  {
    value: 'feature',
    type: 'default',
    title: '特征工程',
    icon: 'mdi-check-all',
    color: '#c6577a',
    accentColor: '#93405a',
    children: [
      {
        name: '数据归一化',
        hasOptions: false,
      },
      {
        name: '数据标准化',
        hasOptions: false,
      },
      {
        name: '数据离散化',
        hasOptions: false,
      },
      {
        name: 'onehot编码',
        hasOptions: false,
      },
      {
        name: 'label编码',
        hasOptions: false,
      },
      {
        name: '描述性统计',
        hasOptions: false,
      },
      {
        name: '词向量转化',
        hasOptions: false,
      },
      {
        name: '相似度计算',
        hasOptions: false,
      },
      {
        name: '图像增强',
        hasOptions: false,
      },
    ],
  },
  {
    value: 'model',
    type: 'default',
    title: '模型选择',
    icon: 'mdi-memory',
    color: '#c39949',
    accentColor: '#907035',
    children: [
      {
        name: '随机森林',
        hasOptions: true,
      },
      {
        name: 'lightGBM',
        hasOptions: false,
      },
      {
        name: 'Xgboost',
        hasOptions: false,
      },
      {
        name: 'Catboost',
        hasOptions: false,
      },
      {
        name: 'K-means',
        hasOptions: true,
      },
      {
        name: '全连接神经网络',
        hasOptions: true,
      },
      {
        name: '卷积神经网络',
        hasOptions: true,
      },
      {
        name: '循环神经网络',
        hasOptions: false,
      },
      {
        name: '图神经网络',
        hasOptions: false,
      },
    ],
  },
  {
    value: 'indictator',
    type: 'output',
    title: '评估指标',
    icon: 'mdi-chart-line',
    color: '#6cbc55',
    accentColor: '#4e893d',
    children: [
      {
        name: '精准率计算',
        hasOptions: false,
      },
      {
        name: '召回率计算',
        hasOptions: false,
      },
      {
        name: 'F1值计算',
        hasOptions: false,
      },
      {
        name: 'AUC计算',
        hasOptions: false,
      },
      {
        name: 'ROC计算',
        hasOptions: false,
      },
      {
        name: 'MSE计算',
        hasOptions: false,
      },
      {
        name: 'MAPE计算',
        hasOptions: false,
      },
    ],
  },
];
