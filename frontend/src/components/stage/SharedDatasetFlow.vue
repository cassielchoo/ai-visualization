<template>
  <v-container class="pa-8 pt-0">
    <v-sheet class="pa-4 mt-8" elevation="2" rounded="lg">
      <v-row v-for="dataset in sharedDatasets" :key="dataset.dataId">
        <v-col class="mx-3 my-auto">{{ dataset.dataName }}</v-col>
        <v-col class="mx-3 my-auto">{{ dataset.dataDescribe }}</v-col>
        <v-col class="justify-end d-flex">
          <v-btn icon variant="text" color="primary" @click="fork(dataset)">
            <v-icon>mdi-plus</v-icon>
          </v-btn>

          <v-btn icon variant="text"><v-icon>mdi-download</v-icon></v-btn>

          <v-dialog width="800" persistent>
            <template v-slot:activator="{ props }">
              <v-btn icon v-bind="props" variant="text">
                <v-icon>mdi-eye</v-icon>
              </v-btn>
            </template>

            <template v-slot:default="{ isActive }">
              <PreviewCSV
                :dataId="dataset.dataId"
                @close="isActive.value = false"
              ></PreviewCSV>
            </template>
          </v-dialog>
        </v-col>

        <v-divider></v-divider>
      </v-row>
    </v-sheet>
  </v-container>
</template>

<script lang="ts" setup>
import { Ref, ref } from 'vue';

import { onMounted } from 'vue';
import { BriefDataset } from '@/types/request';
import { forkDataset, getDatasetList } from '@/service/user-dataset';
import * as papa from 'papaparse';
import { useRoute } from 'vue-router';
import PreviewCSV from './PreviewCSV.vue';

const route = useRoute();



const sharedDatasets: Ref<BriefDataset[]> = ref([]);

const fork = async (dataset: BriefDataset) => {
  await forkDataset(dataset.dataId);
};

onMounted(async () => {
  const ds = await getDatasetList(
    route.meta.type as 'fav' | 'shared' | undefined,
  );
  sharedDatasets.value = ds.data ?? [];
});
</script>
