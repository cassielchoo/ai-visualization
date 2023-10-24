<template>
  <div class="dndflow" @drop="onDrop">
    <VueFlow @dragover="()=>onDragOver" />
    <tool-bar />
  </div>
</template>

<script lang="ts" setup>
import { VueFlow, useVueFlow } from '@vue-flow/core';
import { nextTick, watch } from 'vue';
import toolBar from '@/components/toolBar.vue';

let id = 0;
function getId() {
  return `dndnode_${id++}`;
}

const { findNode, onConnect, addEdges, addNodes, project, vueFlowRef } =
  useVueFlow({
    nodes: [
      {
        id: '1',
        type: 'input',
        label: 'input node',
        position: { x: 250, y: 25 },
      },
    ],
  });

const onDragOver = (event: DragEvent) => {
  event.preventDefault();

  if (event.dataTransfer) {
    event.dataTransfer.dropEffect = 'move';
  }
}

onConnect((params) => addEdges(params));

const onDrop=(event: DragEvent)=> {
  const type = event.dataTransfer?.getData('type');
  const color = event.dataTransfer?.getData('color');
  const name = event.dataTransfer?.getData('name');
  const { left, top } = vueFlowRef.value!.getBoundingClientRect() ?? 0
  const position = project({
    x: event.clientX - left,
    y: event.clientY - top,
  });

  const newNode = {
    id: getId(),
    type,
    position,
    label: name,
    style: () => {
        let backgroundColor = '#fff';

        return {
          background: backgroundColor,
          '--vf-node-color': color,
        };
      },
  };

  addNodes([newNode]);

  // align node position after drop, so it's centered to the mouse
  nextTick(() => {
    const node = findNode(newNode.id)!;
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
</script>

<style>
@import '@vue-flow/core/dist/style.css';
/* this contains the default theme, these are optional styles */
@import '@vue-flow/core/dist/theme-default.css';

@import 'https://cdn.jsdelivr.net/npm/@vue-flow/core@1.23.0/dist/style.css';
@import 'https://cdn.jsdelivr.net/npm/@vue-flow/core@1.23.0/dist/theme-default.css';
@import 'https://cdn.jsdelivr.net/npm/@vue-flow/controls@latest/dist/style.css';
@import 'https://cdn.jsdelivr.net/npm/@vue-flow/node-resizer@latest/dist/style.css';

html,
body,
#app {
  margin: 0;
  height: 100%;
}

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
