<template>
  <v-app-bar density="compact" color="background">
    <v-tabs v-model="selectedTab" class="mx-10">
      <v-tab
        v-for="tab of tabs"
        :key="tab.value"
        :value="tab.value"
        variant="plain"
        slider-color="primary"
        style="font-size: 1.1rem"
      >
        {{ tab.name }}
      </v-tab>
    </v-tabs>

    <v-spacer></v-spacer>

    <v-btn-toggle v-model="sortType" density="compact" class="mx-2" mandatory>
      <v-btn rounded="false">
        升序
        <v-icon>mdi-sort-ascending</v-icon>
      </v-btn>

      <v-btn rounded="false">
        降序
        <v-icon>mdi-sort-descending</v-icon>
      </v-btn>
    </v-btn-toggle>
  </v-app-bar>

  <v-main
    style="background-color: rgb(var(--v-theme-background-content))"
    v-if="selectedTab === 0"
  >
    <model-flow type="fav"></model-flow>
  </v-main>

  <v-main
    style="background-color: rgb(var(--v-theme-background-content))"
    v-if="selectedTab === 1"
  >
    <dataset-flow type="fav"></dataset-flow>
  </v-main>
</template>

<script lang="ts" setup>
import { Ref, ref } from 'vue';

import ModelFlow from '@/components/stage/ModelFlow.vue';
import DatasetFlow from '@/components/stage/DatasetFlow.vue';

interface Tab {
  name: string;
  value: number;
}

const selectedTab: Ref<number> = ref(0);

const tabs: Ref<Tab[]> = ref([
  {
    name: '我的模型',
    value: 0,
  },
  {
    name: '我的数据',
    value: 1,
  },
]);

const sortType: Ref<number> = ref(0);
</script>
