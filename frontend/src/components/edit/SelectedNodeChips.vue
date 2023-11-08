<template>
  <v-sheet class="selected pa-1" rounded="xl">
    <v-chip v-for="node of getSelectedNodes" :key="node.id" :color="node.data.color" @click="removeSelectedNodes([node])" variant="tonal">{{ node.label }}</v-chip>
  </v-sheet>
</template>

<script setup lang="ts">
import { useVueFlow } from '@vue-flow/core';    
const { getSelectedNodes,removeSelectedNodes } = useVueFlow();
</script>

<style scoped>
.selected {
  position: fixed;
  left: 50%;
  transform: translate(-50%, 0);
  bottom: 1.8rem;
}
</style>
