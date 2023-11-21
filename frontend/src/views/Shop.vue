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
        :to="{ name: tab.to }"
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

  <v-main style="background-color: rgb(var(--v-theme-background-content))">
    <router-view></router-view>
  </v-main>
</template>

<script lang="ts" setup>
import { Ref, ref } from 'vue';

interface Tab {
  name: string;
  value: number;
  to: string;
}

const selectedTab: Ref<number> = ref(0);

const tabs: Ref<Tab[]> = ref([
  {
    name: '模型',
    value: 0,
    to: 'sharedmodel',
  },
  {
    name: '数据集',
    value: 1,
    to: 'shareddataset',
  },
]);

const sortType: Ref<number> = ref(0);
</script>
