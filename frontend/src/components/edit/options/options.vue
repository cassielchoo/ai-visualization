<template>
  <aside style="z-index: 99">
    <v-card
      color="background"
      rounded="xl"
      class="pa-5"
      style="
        overflow: auto;
        top: 4.5rem;
        right: 1.6rem;
        width: 18rem;
        position: fixed;
      "
      variant="flat"
    >
      <import-options></import-options>
    </v-card>
  </aside>
</template>

<script lang="ts" setup>
import importOptions from './importOptions.vue'



</script>

<style></style>
