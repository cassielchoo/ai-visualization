<template>
  <div class="above-the-fold">
    <div class="diagram">
      <head-banner-diagram></head-banner-diagram>
    </div>
    <div class="statistics">
      <statistics></statistics>
    </div>
  </div>
</template>

<script lang="ts" setup>
import HeadBannerDiagram from '@/components/home/HeadBannerDiagram.vue';
import Statistics from '@/components/home/Statistics.vue';
</script>

<style>
.above-the-fold {
  display: flex;
  flex-flow: column;
  height: 100%;
}

.diagram {
  flex-direction: column;
  position: relative;
  flex: 1;
}

.statistics {
  display: flex;

  height: 10rem;
  background-color: blue;
}
</style>
