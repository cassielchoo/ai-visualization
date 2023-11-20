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
    <v-container class="pa-8">
      <v-row class="mt-5">
        <v-col
          cols="12"
          sm="6"
          lg="4"
          xl="3"
          v-for="proj of projs"
          :key="proj.modelId"
        >
          <model-card
            :proj="proj"
            @togglefavorite="toggleFavorite(proj)"
            @handledel="handleDel(proj.modelId)"
          ></model-card>
        </v-col>
      </v-row>
    </v-container>
  </v-main>

  <v-main
    style="background-color: rgb(var(--v-theme-background-content))"
    v-if="selectedTab === 1"
  >
    <v-container class="pa-8">
      <v-sheet class="pa-4 mt-8" elevation="2" rounded="lg">
        <v-row v-for="(dataset, i) in datasets" :key="dataset.dataId">
          <v-col class="mx-3 my-auto">{{ dataset.dataName }}</v-col>
          <v-col class="justify-end d-flex">
            <v-btn
              icon
              class="mx-2"
              color="error"
              variant="text"
              @click="toggleFavoriteForDataset(dataset)"
            >
              <v-icon
                :icon="
                  dataset.isFavourite === '0'
                    ? 'mdi-heart-outline'
                    : 'mdi-heart'
                "
              ></v-icon>
            </v-btn>
            <v-btn icon variant="text"><v-icon>mdi-download</v-icon></v-btn>

            <v-btn icon variant="text" @click="parseCSV">
              <v-icon>mdi-eye</v-icon>
            </v-btn>
          </v-col>

          <v-divider v-if="i !== datasets.length - 1"></v-divider>
        </v-row>
      </v-sheet>
    </v-container>
  </v-main>
</template>

<script lang="ts" setup>
import { Ref, ref } from 'vue';
import ModelCard from '@/components/stage/project/ModelCard.vue';
import CreateProjectDialog from '@/components/stage/project/CreateProjectDialog.vue';
import CreateWithTemplateDialog from '@/components/stage/project/CreateWithTemplateDialog.vue';
import UploadDataDialog from '@/components/stage/project/UploadDataDialog.vue';
import { onMounted } from 'vue';
import { delModel, getFavModelList, getSharedModelList, setModelFav } from '@/service/user-model';
import { BriefModel } from '@/types/model';
import { BriefDataset } from '@/types/request';
import { getFavDatasetList, getSharedDatasetList, setDatasetFav } from '@/service/user-dataset';
import { useAppStore } from '@/store/app';
import * as papa from 'papaparse';

const parseCSV = () => {
  console.log(papa);
};

const appStore = useAppStore();

interface Tab {
  name: string;
  value: number;
}

const selectedTab: Ref<number> = ref(0);

const tabs: Ref<Tab[]> = ref([
  {
    name: '模型',
    value: 0,
  },
  {
    name: '数据集',
    value: 1,
  },
]);

const sortType: Ref<number> = ref(0);

const projs: Ref<BriefModel[]> = ref([]);
const datasets: Ref<BriefDataset[]> = ref([]);

const toggleFavorite = async (proj: BriefModel) => {
  proj.isFavourite = proj.isFavourite === '1' ? '0' : '1';
  await setModelFav({
    isFavourite: proj.isFavourite,
    modelId: proj.modelId,
  });
};

const toggleFavoriteForDataset = async (dataset: BriefDataset) => {
  dataset.isFavourite = dataset.isFavourite === '1' ? '0' : '1';
  await setDatasetFav({
    isFavourite: dataset.isFavourite,
    dataId: dataset.dataId,
  });
};

const handleDel = async (modelId: string) => {
  await delModel(modelId);
};

onMounted(async () => {
  const md = await getSharedModelList();
  const ds = await getSharedDatasetList();
  projs.value = md.data ?? [];
  datasets.value = ds.data ?? [];
});
</script>
