<template>
  <aside style="z-index: 99">
    <v-card
      color="background"
      rounded="xl"
      style="
        overflow: auto;
        top: 4.5rem;
        right: 1.6rem;
        width: 20rem;
        position: fixed;
      "
      variant="flat"
      v-if="selectedNodes.length > 0"
    >
      <v-card-title>
        <v-tabs density="compact" center-active v-model="selectedTab">
          <v-tab
            v-for="(node, i) in selectedNodes"
            :key="i"
            :value="node.label"
          >
            {{ node.label }}
          </v-tab>
        </v-tabs>
      </v-card-title>
      <v-card-text class="my-2">
        <kmeans-options v-if="selectedTab === 'K-means'"></kmeans-options>
        <import-options
          v-if="selectedTab === '从 csv/excel 导入'"
        ></import-options>
        <GNNOptions v-if="selectedTab === '卷积神经网络'"></GNNOptions>
        <full-connect-options
          v-if="selectedTab === '全连接神经网络'"
        ></full-connect-options>
        <random-forest-options v-if="selectedTab === '随机森林'"></random-forest-options>
      </v-card-text>
    </v-card>
  </aside>
</template>

<script lang="ts" setup>
import { GraphNode, useVueFlow } from '@vue-flow/core';
import ImportOptions from './ImportOptions.vue';
import KmeansOptions from './KMeansOptions.vue';
import GNNOptions from './GNNOptions.vue';
import FullConnectOptions from './FullConnectOptions.vue';
import RandomForestOptions from './RandomForestOptions.vue';
import { ComputedRef, Ref, computed, ref, watch } from 'vue';

const { nodes } = useVueFlow();

let selectedTab: Ref<string> = ref('');
let selectedNodes: ComputedRef<GraphNode[]> = computed(() =>
  nodes.value.filter((node) => node.selected && node.data.hasOptions),
);

watch(
  () => selectedNodes,
  () => {
    if (selectedNodes.value.length === 1) {
      selectedTab.value = typeof selectedNodes.value[0].label === 'string'? selectedNodes.value[0].label : '';
    }
  },
  {
    deep: true, // 深度监听的参数
  },
);
</script>

<style></style>
