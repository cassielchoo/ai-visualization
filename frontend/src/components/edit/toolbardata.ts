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
  descr?:string;
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
        descr:
          '一种集成学习方法，通常用于解决分类和回归问题。它基于决策树构建，并结合多个决策树的预测结果以提高模型的性能和鲁棒性。',
      },
      {
        name: 'lightGBM',
        hasOptions: true,
        descr: '一种高效的、开源的梯度提升框架，用于解决分类和回归问题。',
      },
      {
        name: 'Xgboost',
        hasOptions: true,
        descr:
          '一种强大的机器学习算法，特别用于解决回归和分类问题。它是一种集成学习算法，通过组合多个决策树模型来提高预测性能。',
      },
      {
        name: 'Catboost',
        hasOptions: true,
        descr:
          '一种用于解决分类和回归问题的机器学习算法。它是一种梯度提升算法，专门设计用于处理具有大量类别特征（离散特征）的数据集。',
      },
      {
        name: 'K-means',
        hasOptions: true,
        descr: '一种常见的无监督学习算法，用于数据聚类。',
      },
      {
        name: '全连接神经网络',
        hasOptions: true,
        descr:
          '也被称为前馈神经网络（Feedforward Neural Network）或多层感知机（Multilayer Perceptron，MLP），是深度学习领域中最基本和常见的神经网络类型之一。',
      },
      {
        name: '卷积神经网络',
        hasOptions: true,
        descr:
          '一种深度学习神经网络，专门设计用于处理图像和视觉数据的任务，如图像分类、目标检测、图像分割等。',
      },
      {
        name: '循环神经网络',
        hasOptions: true,
        descr:
          '一种用于处理序列数据的神经网络架构。它具有内部循环结构，可以维护对先前输入的记忆，并在处理序列数据时具有灵活性。',
      },
      {
        name: '图神经网络',
        hasOptions: false,
        descr:
          '一种用于处理图结构数据的深度学习模型。它通过学习节点之间的关系和图的拓扑结构来进行节点分类、图分类和链接预测等任务。',
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
        descr:
          '通常用于分类问题，如垃圾邮件检测、医学诊断等。精准率衡量了模型预测为正例中真正为正例的比例。',
      },
      {
        name: '召回率计算',
        hasOptions: false,
        descr:
          '通常用于分类问题，如垃圾邮件检测、医学诊断等。召回率衡量了模型能够正确预测正例的比例。',
      },
      {
        name: 'F1值计算',
        hasOptions: false,
        descr:
          '精准率和召回率的调和平均，用于综合考虑精准率和召回率。特别适用于不平衡类别的分类问题。',
      },
      {
        name: 'AUC计算',
        hasOptions: false,
        descr: 'ROC曲线下的面积，AUC值越高，模型性能越好。',
      },
      {
        name: 'ROC计算',
        hasOptions: false,
        descr:
          '适用二元分类任务，用于评估模型的分类性能和不同分类阈值下的表现。',
      },
      {
        name: 'MSE计算',
        hasOptions: false,
        descr:
          '均方误差（Mean Squared Error，MSE）,通常用于回归问题，如房价预测、股票价格预测等。它衡量了模型的预测值与实际值之间的平方误差的均值，越小表示模型拟合得越好。',
      },
      {
        name: 'MAPE计算',
        hasOptions: false,
        descr:
          '平均百分比误差（Mean Absolute Percentage Error，MAPE）用于回归问题，它衡量了模型的百分比误差的均值。适用于涉及百分比预测误差的任务，如销售预测、需求预测等。',
      },
    ],
  },
];
