<template>
  <div
    class="dndflow"
    @drop="onDrop"
    :oncontextmenu="openContextMenu"
    id="diagram"
  >
    <VueFlow @dragover="onDragOver" fit-view-on-init>
      <Background :variant="patternVariant" :patternColor="patternColor" />
    </VueFlow>
  </div>
</template>

<script lang="ts" setup>
import { GraphEdge, GraphNode, VueFlow, useVueFlow } from '@vue-flow/core';
import {
  nextTick,
  watch,
  ref,
  ComputedRef,
  computed,
  Ref,
  onMounted,
} from 'vue';
import { useTheme } from 'vuetify';
import { Background, BackgroundVariant } from '@vue-flow/background';
import { onContextMenu } from './contextMenu/context-menu';
import { useProjectStore } from '@/store/project';
import { getModelById } from '@/service/user-model';
import { handleSaveModel } from './save-model';
import { useRoute } from 'vue-router';
const theme = useTheme();
const store = useProjectStore();

const patternVariant: Ref<BackgroundVariant> = ref(BackgroundVariant.Dots);
const patternColor: ComputedRef<string> = computed(() => {
  let color = theme.global.current.value.dark ? '#818181' : '#828282';
  if (patternVariant.value === BackgroundVariant.Lines) color += '80';
  return color;
});

const flow = useVueFlow({
  nodes: [],
  edges: [],
  maxZoom: 4,
  minZoom: 0.1,
  defaultViewport: {
    x: 0,
    y: 0,
    zoom: 1,
  },
});

const {
  findNode,
  onConnect,
  addEdges,
  addNodes,
  project,
  vueFlowRef,
  onPaneReady,
  nodes,
  fromObject,
  toObject,
} = flow;

const openContextMenu = (e: MouseEvent) => {
  onContextMenu(e, flow, theme.global.current.value.dark);
};

onPaneReady((instance: typeof VueFlow) => {
  instance.fitView();
});

const onDragOver = ref();
onDragOver.value = function (event: DragEvent) {
  event.preventDefault();
  if (event.dataTransfer) {
    event.dataTransfer.dropEffect = 'move';
  }
};

onConnect((params: GraphEdge) => {
  // edge连接规则
  const handleRules = [
    () => params.source !== params.target,
    () =>
      params.sourceHandle?.endsWith('bottom') &&
      params.targetHandle?.endsWith('top'),
  ];
  const judger = handleRules.every((rule) => rule());
  if (judger) {
    addEdges([params]);
    handleSaveModel(store.modelInfo.modelId, JSON.stringify(toObject() ?? {}));
  }
});

const onDrop = (event: DragEvent) => {
  const category = event.dataTransfer!.getData('category');
  const type = event.dataTransfer!.getData('type');
  const color = event.dataTransfer!.getData('color');
  const accentColor = event.dataTransfer!.getData('accentColor');
  const name = event.dataTransfer!.getData('name');
  const data = event.dataTransfer!.getData('data');
  const options = JSON.parse(event.dataTransfer!.getData('options'));

  const { left, top } = vueFlowRef.value!.getBoundingClientRect() ?? 0;
  const position = project({
    x: event.clientX - left,
    y: event.clientY - top,
  });

  if (
    category !== 'model' ||
    nodes.value.filter((node: GraphNode) => node.data.category === 'model')
      .length === 0
  ) {
    addNodes([
      {
        id: nodes.value.length.toString(),
        type,
        position,
        label: name,
        data: {
          color,
          accentColor,
          hasOptions: data === 'true',
          options,
          category
        },
        style: (el: GraphNode) => {
          if (el.selected)
            return {
              '--vf-node-text': 'white',
              '--vf-node-bg': color,
              '--vf-node-color': color,
              'border-color': accentColor + '!important',
            };
          return {
            '--vf-node-text': 'white',
            '--vf-node-bg': color,
            '--vf-node-color': color,
          };
        },
      },
    ]);
    handleSaveModel(store.modelInfo.modelId, JSON.stringify(toObject() ?? {}));

    nextTick(() => {
      const node = findNode((nodes.value.length - 1).toString())!;
      const stop = watch(
        () => node.dimensions,
        (dimensions) => {
          if (dimensions.width > 0 && dimensions.height > 0) {
            node.position = {
              x: node.position.x - node.dimensions.width / 2,
              y: node.position.y - node.dimensions.height / 2,
            };
            stop();
          }
        },
        { deep: true, flush: 'post' },
      );
    });
  }
};

const route = useRoute();

onMounted(async () => {
  store.modelInfo.modelId = route.params.id as string;

  const res = await getModelById(store.modelInfo.modelId);

  store.modelInfo = res.data!;

  const flow = JSON.parse(res.data!.dataJson);

  if (flow) {
    fromObject(flow);
  }

  nodes.value.forEach((node: GraphNode) => {
    node.style = () => {
      if (node.selected)
        return {
          '--vf-node-text': 'white',
          '--vf-node-bg': node.data.color,
          '--vf-node-color': node.data.color,
          'border-color': node.data.accentColor + '!important',
        };
      return {
        '--vf-node-text': 'white',
        '--vf-node-bg': node.data.color,
        '--vf-node-color': node.data.color,
      };
    };
  });
});
</script>

<style>
.dndflow {
  flex-direction: column;
  display: flex;
  height: 100%;
}

.dndflow .vue-flow-wrapper {
  flex-grow: 1;
  height: 100%;
}

.vue-flow__handle{
  width:10px !important;
  height:5px !important;
  border-radius: 3px !important;
}
</style>