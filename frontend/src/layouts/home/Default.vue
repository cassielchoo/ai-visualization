<template>
  <v-app>
    <home-app-bar></home-app-bar>
    <default-view />
  </v-app>
</template>

<script setup lang="ts">
import DefaultView from './View.vue';
import HomeAppBar from '@/components/home/HomeAppBar.vue';
</script>
