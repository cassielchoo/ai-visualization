<template>
  <v-container class="pa-8">
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

          <v-btn icon variant="text" @click="parseCSV">
            <v-icon>mdi-eye</v-icon>
          </v-btn>
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
  getDatasetList,
  setDatasetFav,
  setDatasetShared,
} from '@/service/user-dataset';
import * as papa from 'papaparse';

const parseCSV = () => {
  console.log(papa);
};

const props = defineProps<{ type?: 'fav' | 'shared' }>();

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
  const ds = await getDatasetList(props.type);
  datasets.value = ds.data ?? [];
});
</script>
