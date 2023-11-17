<template>
  <v-app>
    <stage-app-bar></stage-app-bar>
    <default-view />
  </v-app>
</template>

<script lang="ts" setup>
import DefaultView from './View.vue';
import StageAppBar from '@/components/stage/StageAppBar.vue';
</script>
