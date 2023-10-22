<template>
  <v-main
    :style="
      theme.global.current.value.dark
        ? 'background-color: #1A1A1A'
        : 'background-color: #f9f9f9'
    "
  >
    <edit></edit>
  </v-main>
</template>

<script lang="ts" setup>
import Edit from '@/views/Edit.vue';
import { useTheme } from 'vuetify';

const theme = useTheme();
</script>

<style></style>
