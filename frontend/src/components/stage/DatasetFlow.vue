<template>
  <v-container class="pa-8 pt-0">
    <v-sheet class="pa-4 mt-8" elevation="2" rounded="lg">
      <v-row v-for="dataset in datasets" :key="dataset.dataId">
        <v-col class="mx-3 my-auto">{{ dataset.dataName }}</v-col>
        <v-col class="mx-3 my-auto">{{ dataset.dataDescribe }}</v-col>
        <v-col class="justify-end d-flex">
          <v-btn
            icon
            class="mx-2"
            color="error"
            variant="text"
            @click="toggleFavorite(dataset)"
          >
            <v-icon
              :icon="
                dataset.isFavourite === '0' ? 'mdi-heart-outline' : 'mdi-heart'
              "
            ></v-icon>
          </v-btn>

          <v-btn
            variant="text"
            color="success"
            :icon="dataset.isShared === '1' ? 'mdi-share' : 'mdi-share-outline'"
            @click="handleShare(dataset)"
          ></v-btn>

          <v-btn icon variant="text"><v-icon>mdi-download</v-icon></v-btn>

          <v-dialog width="800" persistent>
            <template v-slot:activator="{ props }">
              <v-btn
                icon
                v-bind="props"
                variant="text"
                
              >
                <v-icon>mdi-eye</v-icon>
              </v-btn>
            </template>

            <template v-slot:default="{ isActive }">
              <PreviewCSV :dataId="dataset.dataId"  @close="isActive.value=false"></PreviewCSV>
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
import {
  getDatasetById,
  getDatasetList,
  setDatasetFav,
  setDatasetShared,
} from '@/service/user-dataset';
import PreviewCSV from './PreviewCSV.vue';
import { useRoute } from 'vue-router';
const route = useRoute();

const datasets: Ref<BriefDataset[]> = ref([]);

const toggleFavorite = async (dataset: BriefDataset) => {
  const newValue = dataset.isFavourite === '1' ? '0' : '1';
  const res = await setDatasetFav({
    isFavourite: newValue,
    dataId: dataset.dataId,
  });
  if (res.code === 200) {
    dataset.isFavourite = newValue;
  }
};

const handleShare = async (dataset: BriefDataset) => {
  const newValue = dataset.isShared === '0' ? '1' : '0';
  const res = await setDatasetShared({
    dataId: dataset.dataId,
    isShared: newValue,
  });
  if (res.code === 200) {
    dataset.isShared = newValue;
  }
};

onMounted(async () => {
  const ds = await getDatasetList(
    route.meta.type as 'fav' | 'shared' | undefined,
  );
  datasets.value = ds.data ?? [];
});
</script>
