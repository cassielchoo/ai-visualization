<template>
  <v-sheet
    rounded
    width="250"
    style="position: fixed; right: 1.6rem; bottom: 1.8rem"
    class="d-flex justify-space-between"
  >
  <v-btn @click="fitView({ duration: 1000, padding: 1 })" variant="plain" icon="mdi-fullscreen" density="comfortable"></v-btn>
    <v-btn @click="zoomOut" variant="plain">-</v-btn>
    <v-btn @click="setDefaultZoom" variant="plain">
      {{ (currentViewport.zoom * 100).toFixed(0) }}%
    </v-btn>
    <v-btn @click="zoomIn" variant="plain">+</v-btn>
  </v-sheet>
</template>

<script setup lang="ts">
import { ViewportTransform, useVueFlow } from '@vue-flow/core';
import { ComputedRef, computed } from 'vue';

const { setViewport, getViewport, zoomIn, zoomOut,fitView } = useVueFlow();

const currentViewport: ComputedRef<ViewportTransform> = computed(() =>
  getViewport(),
);

const setDefaultZoom = () => {
  setViewport({
    x: currentViewport.value.x,
    y: currentViewport.value.y,
    zoom: 1,
  });
};
</script>
