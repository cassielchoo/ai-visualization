import ContextMenu from '@imengyu/vue3-context-menu';
import { GraphNode, VueFlowStore } from '@vue-flow/core';
import { handleSaveModel } from '../save-model';
import './contextMenuTheme.scss';
import { useProjectStore } from '@/store/project';
const store = useProjectStore();

export function onContextMenu(
  e: MouseEvent,
  flow: VueFlowStore,
  isDark: boolean,
) {
  //prevent the browser's default menu
  e.preventDefault();

  const items = [];

  if (
    flow.getSelectedNodes.value.length !== 0 ||
    flow.getSelectedEdges.value.length !== 0
  ) {
    items.push({
      label: '删除所选元素',
      onClick: async() => {
        flow.removeNodes(flow.getSelectedNodes.value);
        flow.removeEdges(flow.getSelectedEdges.value);
        await handleSaveModel(
          store.modelInfo.modelId,
          JSON.stringify(flow.toObject()),
        );
      },
    });
  }

  items.push({
    label: '选中全部',
    onClick: () => {
      flow.nodes.value.forEach((node: GraphNode) => {
        node.selected = true;
      });
    },
  });

  if (flow.getSelectedElements.value.length !== 0) {
    items.push({
      label: '取消选中',
      onClick: () => {
        flow.nodes.value.forEach((node: GraphNode) => {
          node.selected = false;
        });
      },
    });
  }

  //show your menu
  ContextMenu.showContextMenu({
    theme: isDark ? 'mac dark' : 'mac',
    x: e.x,
    y: e.y,
    items,
  });
}
