<template>
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

  <v-container class="pa-8">
    <v-row>
      <v-col cols="12" sm="6" xl="3">
        <v-btn style="font-size: 1rem" variant="flat" block>
          创建新的项⽬
          <template v-slot:append>
            <v-icon>mdi-plus-circle-outline</v-icon>
          </template>
        </v-btn>
      </v-col>
      <v-col cols="12" sm="6" xl="3">
        <v-btn style="font-size: 1rem" variant="flat" block>
          使⽤模板创建新的项⽬
          <template v-slot:append>
            <v-icon>mdi-plus-circle-outline</v-icon>
          </template>
        </v-btn>
      </v-col>
    </v-row>
    <v-row class="mt-5" >
      <v-col cols="12" sm="6" xl="3" v-for="proj of 8" :key="proj">
        <project-card></project-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script lang="ts" setup>
import { Ref, ref } from 'vue';
import projectCard from '@/components/projectCard.vue';

interface Tab {
  name: string;
  value: number;
}

let selectedTab: Ref<number> = ref(0);

let tabs: Ref<Tab[]> = ref([
  {
    name: '全部文件',
    value: 0,
  },
  {
    name: '我创建的',
    value: 1,
  },
  {
    name: '我团队的',
    value: 2,
  },
]);
</script>
