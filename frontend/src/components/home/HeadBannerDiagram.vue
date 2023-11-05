<template>
  <VueFlow fit-view-on-init>
    <Background :variant="patternVariant" :patternColor="patternColor" />
  </VueFlow>
</template>

<script lang="ts" setup>
import { VueFlow, useVueFlow } from '@vue-flow/core';
import { ref, ComputedRef, computed, Ref } from 'vue';
import { useTheme } from 'vuetify';
import '@vue-flow/core/dist/style.css';
import '@vue-flow/core/dist/theme-default.css';
import { Background, BackgroundVariant } from '@vue-flow/background';
const theme = useTheme();

let patternVariant: Ref<BackgroundVariant> = ref(BackgroundVariant.Dots);
let patternColor: ComputedRef<string> = computed(() => {
  let color = theme.global.current.value.dark ? '#818181' : '#828282';
  if (patternVariant.value === BackgroundVariant.Lines) color += '80';
  return color;
});

const { onPaneReady } = useVueFlow({
  nodes: [
    {
      id: '1',
      type: 'input',
      label: 'Node 1',
      position: { x: 0, y: 50 },
    },
    {
      id: '2',
      type: 'output',
      label: 'Node 2',
      position: { x: 250, y: 0 },
    },
    {
      id: '3',
      label: 'Node 3',
      position: { x: 250, y: 100 },
    },
    {
      id: '4',
      label: 'Node 4',
      position: { x: 500, y: 150 },
    },
    {
      id: '5',
      type: 'output',
      label: 'Node 5',
      position: { x: 750, y: 50 },
    },
  ],
  edges: [],
  maxZoom: 4,
  minZoom: 0.1,
  defaultViewport: {
    x: 0,
    y: 0,
    zoom: 1,
  },
});

onPaneReady(({ fitView }) => {
  fitView();
});
</script>

<style scoped></style>
