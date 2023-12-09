import { useAppStore } from '@/store/app';
import { GraphNode, VueFlowStore } from '@vue-flow/core';

const appStore = useAppStore();

export const validate = (
  name: string,
  category: string,
  flow: VueFlowStore,
) => {
  //不能同时存在多个相同节点
  for (const node of flow.nodes.value) {
    if (name === node.label) {
      appStore.handleGlobalMessaging('已存在相同节点');
      return false;
    }
  }

  //只能存在一个模型
  if (
    category === 'model' &&
    flow.nodes.value.filter((node: GraphNode) => node.data.category === 'model')
      .length !== 0
  ) {
    appStore.handleGlobalMessaging('不能同时选择多个模型');
    return false;
  }

  //只能存在一个数据导入方式
  if (
    category === 'import' &&
    flow.nodes.value.filter((node: GraphNode) => node.data.category === 'import')
      .length !== 0
  ) {
    appStore.handleGlobalMessaging('不能同时选择多个导入方式');
    return false;
  }

  return true;
};
