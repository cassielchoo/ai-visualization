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
      v-if="showResults"
    >
      <v-card-title>训练结果</v-card-title>
      <v-card-text class="my-2">
        <CNN-results />
      </v-card-text>
    </v-card>
  </aside>
</template>

<script setup lang="ts">
import CNNResults from '@/components/edit/results/CNNResults.vue';
import { GraphNode, useVueFlow } from '@vue-flow/core';
import { ref, watch } from 'vue';

const { nodes, toObject } = useVueFlow();

const node: GraphNode = nodes.value.find(
  (n: GraphNode) => n.data.category === 'model',
);

const showResults=ref(false)

watch(
  () => node,
  () => {
      if (node.data.results) {
        showResults.value=true
    }
  },
  {
    deep: true, // 深度监听的参数
  },
);
</script>

<style scoped></style>
