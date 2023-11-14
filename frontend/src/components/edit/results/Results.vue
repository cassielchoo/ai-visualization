<template>
  <aside style="z-index: 99">
    <v-card
      color="background"
      rounded="xl"
      style="
        overflow: auto;
        top: 4.5rem;
        right: 1.6rem;
        width: 22rem;
        position: fixed;
      "
      variant="flat"
    >
      <v-card-title>训练结果</v-card-title>
      <v-card-text class="my-2">
        <CNN-results />
      </v-card-text>
    </v-card>
  </aside>
</template>

<script lang="ts" setup>
import LightGBMResultsVue from './LightGBMResults';
import CNNResults from './CNNResults';
</script>

<style></style>
