<template>
  <aside style="z-index: 99">
    <v-card
      :color="toolbarColor"
      rounded="xl"
      style="
        overflow: auto;
        top: 4.5rem;
        right: 1.6rem;
        width: 16rem;
        position: fixed;
      "
      variant="flat"
    >
      <import-option></import-option>
    </v-card>
  </aside>
</template>

<script lang="ts" setup>
import { ComputedRef, computed } from 'vue';
import { useTheme } from 'vuetify';
import importOption from './importOption.vue';

const theme = useTheme();

let toolbarColor: ComputedRef<string> = computed(() =>
  theme.global.current.value.dark ? '121212' : '#f1f3f4',
);
</script>

<style></style>
