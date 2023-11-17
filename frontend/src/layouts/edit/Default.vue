<template>
  <v-app>
    <edit-app-bar></edit-app-bar>
    <default-view />
  </v-app>
</template>

<script lang="ts" setup>
import DefaultView from './View.vue';
import EditAppBar from '@/components/edit/EditAppBar.vue';
</script>
