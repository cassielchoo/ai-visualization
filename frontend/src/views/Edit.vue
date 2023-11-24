<template>
  <edit-diagram />
  <tool-bar />
  <options />
  <pred />
  <controls />
  <!-- <edit-status /> -->
  <selected-node-chips />
</template>

<script lang="ts" setup>
import EditDiagram from '@/components/edit/EditDiagram.vue';
import Controls from '@/components/edit/Controls.vue';
import ToolBar from '@/components/edit/ToolBar.vue';
import Options from '@/components/edit/options/Options.vue';
import EditStatus from '@/components/edit/EditStatus.vue';
import Pred from '@/components/edit/Pred.vue';

import SelectedNodeChips from '@/components/edit/SelectedNodeChips.vue';
import { useVueFlow } from '@vue-flow/core';
import '@vue-flow/core/dist/style.css';
import '@vue-flow/core/dist/theme-default.css';
useVueFlow();
</script>

<style></style>
