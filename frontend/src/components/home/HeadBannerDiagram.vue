<template>
  <VueFlow :nodes="initialNodes" :edges="initialEdges"  fit-view-on-init>
    <template #node-card="{ data }">
      <v-card class="card-node pa-5" rounded="lg" color="#42b983">
        <v-card-title class="d-flex justify-center" style="font-size: 2rem">
          {{ data.title }}
        </v-card-title>
        <v-card-text style="width: 100%;text-align: center;font-size: 1.4rem;line-height:1.7rem">
            {{ data.text }}
        </v-card-text>
      </v-card>
      <Handle id="b" type="source" :position="Position.Bottom" style="width: 60px;height: 10px;border-radius: 3px;" />
    </template>

    <Background :variant="patternVariant" :patternColor="patternColor" :gap="80"/>

  </VueFlow>
</template>

<script lang="ts" setup>
import { VueFlow, useVueFlow,Handle,Position } from '@vue-flow/core';
import { ref, ComputedRef, computed, Ref } from 'vue';
import { useTheme } from 'vuetify';
import '@vue-flow/core/dist/style.css';
import '@vue-flow/core/dist/theme-default.css';
import { Background, BackgroundVariant } from '@vue-flow/background';
import { onMounted } from 'vue';
const theme = useTheme();

const patternVariant: Ref<BackgroundVariant> = ref(BackgroundVariant.Lines);
const patternColor: ComputedRef<string> = computed(() =>
  theme.global.current.value.dark ? '#818181' : '#828282',
);

const { dimensions, addEdges,onPaneReady } = useVueFlow({
  maxZoom: 4,
  minZoom: 0.1,
  defaultViewport: {
    x: 0,
    y: 0,
    zoom: 1,
  },
});

const initialNodes = computed(() => [
  {
    id: '1',
    type: 'card',
    data: {
      title: 'EASY AI',
      text: '这是一个基于拖拉拽建模的可视化AI模型开发平台。帮助用户实现“零门槛”建模，让模型的构建、优化、落地应用等各个方面更加高效。',
    },
    style: {
      '--vf-node-text': 'white',
      '--vf-node-bg': '#42b983',
      '--vf-node-color': 'black',
      'border-radius': '10px',
    },
    position: {
      x: dimensions.value.width / 2 - 240,
      y: dimensions.value.height / 2 - 250,
    },
  },
  {
    id: '2',
    type: 'output',
    label: '样例',
    style: {
      width: '14rem',
      height: '4rem',
      '--vf-node-text': 'white',
      '--vf-node-bg': '#ec4899',
      '--vf-node-color': '#ec4899',
      'border-radius': '10px',
      'font-size':'1.6rem'
    },
    position: {
      x: dimensions.value.width / 2 - 385,
      y: dimensions.value.height / 2 + 60,
    },
  },
  {
    id: '3',
    type: 'output',
    label: '加入我们',
    style: {
      width: '14rem',
      height: '4rem',
      '--vf-node-text': 'white',
      '--vf-node-bg': '#0ea5e9',
      '--vf-node-color': '#0ea5e9',
      'border-radius': '10px',
      'font-size':'1.6rem'
    },
    position: {
      x: dimensions.value.width / 2 - 90,
      y: dimensions.value.height / 2 + 160,
    },
  },
  {
    id: '4',
    type: 'output',
    label: '说明文档',
    style: {
      width: '14rem',
      height: '4rem',
      '--vf-node-text': 'white',
      '--vf-node-bg': '#f15a16',
      '--vf-node-color': '#f15a16',
      'border-radius': '10px',
      'font-size':'1.6rem'
    },
    position: {
      x: dimensions.value.width / 2 + 170,
      y: dimensions.value.height / 2 + 50,
    },
  },
]);

const initialEdges = [
  {
    id: 'e1-2',
    source: '1',
    target: '2',
    animated: true,
    style: {
      'stroke-width': '5px',
      stroke: '#ec4899',
    },
  },
  {
    id: 'e1-3',
    source: '1',
    target: '3',
    animated: true,
    style: {
      'stroke-width': '5px',
      stroke: '#0ea5e9',
    },
  },
  {
    id: 'e1-4',
    source: '1',
    target: '4',
    animated: true,
    style: {
      'stroke-width': '5px',
      stroke: '#f15a16',
    },
  },
];

</script>

<style>
.card-node {
  width: 30rem;
  height: 14rem;
}

.vue-flow__handle{
  width:30px;
  height:10px;
  border-radius: 3px;
  --vf-handle:#aaaaaa
}
</style>
