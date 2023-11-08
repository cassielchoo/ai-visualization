<template>
  <div class="dndflow" @drop="onDrop" :oncontextmenu="openContextMenu">
    <VueFlow @dragover="onDragOver" fit-view-on-init>
      <Background :variant="patternVariant" :patternColor="patternColor" />
    </VueFlow>
  </div>
</template>

<script lang="ts" setup>
import { VueFlow, useVueFlow } from '@vue-flow/core';
import { nextTick, watch, ref, ComputedRef, computed, Ref } from 'vue';
import { useTheme } from 'vuetify';
import { Background, BackgroundVariant } from '@vue-flow/background';
import { onContextMenu } from './contextMenu/context-menu';
const theme = useTheme();

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
} = flow;
const openContextMenu = (e:MouseEvent) => {
  onContextMenu(e,flow,theme.global.current.value.dark)
}

onPaneReady(({ fitView }) => {
  fitView();
});

const onDragOver = ref();
onDragOver.value = function (event: DragEvent) {
  event.preventDefault();
  if (event.dataTransfer) {
    event.dataTransfer.dropEffect = 'move';
  }
};

onConnect((params) => {
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
  }
});

const onDrop = (event: DragEvent) => {
  const type = event.dataTransfer!.getData('type');
  const color = event.dataTransfer!.getData('color');
  const accentColor = event.dataTransfer!.getData('accentColor');
  const name = event.dataTransfer!.getData('name');
  const data = event.dataTransfer!.getData('data');

  const { left, top } = vueFlowRef.value!.getBoundingClientRect() ?? 0;
  const position = project({
    x: event.clientX - left,
    y: event.clientY - top,
  });

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
      },
      style: (el) => {
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
};
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
</style>
./contextMenu/context-menu