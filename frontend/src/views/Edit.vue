<template>
  <diagram />
  <tool-bar />
  <options />
  <controls />

</template>

<script lang="ts" setup>
import diagram from '@/components/edit/diagram.vue';
import controls from '@/components/edit/controls.vue';
import toolBar from '@/components/edit/toolBar.vue';
import options from '@/components/edit/options/options.vue'
import { useVueFlow } from '@vue-flow/core';
import '@vue-flow/core/dist/style.css';
import '@vue-flow/core/dist/theme-default.css';
useVueFlow();
</script>

<style>

</style>
