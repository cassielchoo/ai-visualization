<template>
  <diagram />
  <tool-bar />
  <options />
  <controls />

</template>

<script lang="ts" setup>
import diagram from '@/components/diagram.vue';
import controls from '@/components/controls.vue';
import toolBar from '@/components/toolBar.vue';
import options from '@/components/options.vue'
import { useVueFlow } from '@vue-flow/core';
import '@vue-flow/core/dist/style.css';
import '@vue-flow/core/dist/theme-default.css';
useVueFlow();
</script>

<style>

</style>
