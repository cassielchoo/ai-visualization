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
      theme="dark"
      
    >

      <div v-if="selectedNodes.length > 0">
        <v-card-title>
          <v-tabs density="compact" center-active v-model="selectedNode">
            <v-tab v-for="node in selectedNodes" :key="node.id" :value="node">
              {{ node.label }}
            </v-tab>
          </v-tabs>
        </v-card-title>
        <v-card-text class="my-2">
          <import-options
            v-if="selectedNode.label === '从 csv/excel 导入'"
            :options="selectedNode.data.options"
          ></import-options>
          <kmeans-options
            v-if="selectedNode.label === 'K-means'"
            :options="selectedNode.data.options"
            @save="saveOptions"
          ></kmeans-options>
          <GNNOptions
            v-if="selectedNode.label === '卷积神经网络'"
            :options="selectedNode.data.options"
            @save="saveOptions"
          ></GNNOptions>
          <full-connect-options
            v-if="selectedNode.label === '全连接神经网络'"
            :options="selectedNode.data.options"
            @save="saveOptions"
          ></full-connect-options>
          <random-forest-options
            v-if="selectedNode.label === '随机森林'"
            :options="selectedNode.data.options"
            @save="saveOptions"
          ></random-forest-options>
          <RNNOptions
            v-if="selectedNode.label === '循环神经网络'"
            :options="selectedNode.data.options"
            @save="saveOptions"
          ></RNNOptions>
          <cat-boost-options
            v-if="selectedNode.label === 'Catboost'"
            :options="selectedNode.data.options"
            @save="saveOptions"
          ></cat-boost-options>
          <xg-boost-options
            v-if="selectedNode.label === 'Xgboost'"
            :options="selectedNode.data.options!"
            @save="saveOptions"
          ></xg-boost-options>
          <light-GBM
            v-if="selectedNode.label === 'lightGBM'"
            :options="selectedNode.data.options"
            @save="saveOptions"
          ></light-GBM>
        </v-card-text>
      </div>
    </v-card>
  </aside>
</template>

<script lang="ts" setup>
import { GraphNode, useVueFlow } from '@vue-flow/core';
import ImportOptions from './ImportOptions.vue';
import KmeansOptions from './KMeansOptions.vue';
import GNNOptions from './CNNOptions.vue';
import FullConnectOptions from './FullConnectOptions.vue';
import RandomForestOptions from './RandomForestOptions.vue';
import RNNOptions from './RNNOptions.vue';
import CatBoostOptions from './CatBoostOptions.vue';
import XgBoostOptions from './XgBoostOptions.vue';
import LightGBM from './LightGBMOptions.vue';
import { ComputedRef, Ref, computed, ref, watch } from 'vue';
import { handleSaveModel } from '../save-model';
import { useProjectStore } from '@/store/project';
import Results from '../results/Results.vue';
const store = useProjectStore();

const { getSelectedNodes, toObject } = useVueFlow();

const selectedNodes: ComputedRef<GraphNode[]> = computed(() =>
  getSelectedNodes.value.filter((node: GraphNode) => node.data.hasOptions),
);
const selectedNode: Ref<GraphNode> = ref(selectedNodes.value[0]);

const saveOptions = (options: any) => {
  selectedNode.value.data.options = options;
  handleSaveModel(store.modelInfo.modelId, JSON.stringify(toObject() ?? {}));
};

watch(
  () => selectedNodes,
  () => {
    if (selectedNodes.value.length === 1) {
      selectedNode.value = selectedNodes.value[0];
    }
  },
  {
    deep: true, // 深度监听的参数
  },
);
</script>

<style></style>
