<template>
  <v-sheet
    rounded
    width="250"
    style="position: fixed; right: 1.6rem; bottom: 1.8rem"
    class="d-flex justify-space-between"
  >
    <v-tooltip :text="projStore.modelInfo.isShared==='1'?'取消分享':'分享模型'" location="top">
      <template v-slot:activator="{ props }">
        <v-btn
          v-bind="props"
          variant="plain"
          density="comfortable"
          :icon="projStore.modelInfo.isShared==='1'?'mdi-share':'mdi-share-outline'"
          @click="handleShare"
          :loading="loadingSharing"
        ></v-btn>
      </template>
    </v-tooltip>
    <v-btn
      @click="fitView({ duration: 1000, padding: 1 })"
      variant="plain"
      icon="mdi-fullscreen"
      density="comfortable"
    ></v-btn>
    <v-btn @click="zoomOut" variant="plain">-</v-btn>
    <v-btn @click="setDefaultZoom" variant="plain">
      {{ (currentViewport.zoom * 100).toFixed(0) }}%
    </v-btn>
    <v-btn @click="zoomIn" variant="plain">+</v-btn>
  </v-sheet>
</template>

<script setup lang="ts">
import { setModelShared } from '@/service/user-model';
import { ViewportTransform, useVueFlow } from '@vue-flow/core';
import { ComputedRef, Ref, computed, ref } from 'vue';

import { useProjectStore } from '@/store/project';

const projStore = useProjectStore();
const { setViewport, getViewport, zoomIn, zoomOut, fitView } = useVueFlow();

const currentViewport: ComputedRef<ViewportTransform> = computed(() =>
  getViewport(),
);

const loadingSharing:Ref<boolean>=ref(false)

const handleShare = async () => {
  loadingSharing.value=true
  await setModelShared({
    modelId: projStore.modelInfo.modelId,
    isShared: projStore.modelInfo.isShared === '0' ? '1' : '0',
  });
  loadingSharing.value=false

};

const setDefaultZoom = () => {
  setViewport({
    x: currentViewport.value.x,
    y: currentViewport.value.y,
    zoom: 1,
  });
};
</script>
