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
      <v-col cols="12" sm="4">
        <create-project-dialog v-model="showCreateProjectDialog" />
      </v-col>
      <v-col cols="12" sm="4">
        <create-with-template-dialog v-model="showCreateWithTemplateDialog" />
      </v-col>
      <v-col cols="12" sm="4">
        <upload-data-dialog v-model="showUploadDataDialog" />
      </v-col>
    </v-row>
    <v-row class="mt-5">
      <v-col
        cols="12"
        sm="6"
        lg="4"
        xl="3"
        v-for="proj of projs"
        :key="proj.modelId"
      >
        <project-card
          :proj="proj"
          @togglefavorite="toggleFavorite(proj)"
          @handledel="handleDel(proj.modelId)"
        ></project-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script lang="ts" setup>
import { Ref, ref } from 'vue';
import ProjectCard from '@/components/stage/project/ModelCard.vue';
import CreateProjectDialog from '@/components/stage/project/CreateProjectDialog.vue';
import CreateWithTemplateDialog from '@/components/stage/project/CreateWithTemplateDialog.vue';
import UploadDataDialog from '@/components/stage/project/UploadDataDialog.vue';
import { onMounted } from 'vue';
import { delModel, getModelList, setModelFav } from '@/service/user-model';
import { BriefModel } from '@/types/model';

interface Tab {
  name: string;
  value: number;
}

const selectedTab: Ref<number> = ref(0);

const showCreateProjectDialog = ref(false);
const showCreateWithTemplateDialog = ref(false);
const showUploadDataDialog = ref(false);

const tabs: Ref<Tab[]> = ref([
  {
    name: '全部文件',
    value: 0,
  },
  {
    name: '我的模型',
    value: 1,
  },
  {
    name: '我的数据',
    value: 2,
  },
]);

const projs: Ref<BriefModel[]> = ref([]);

const toggleFavorite = async (proj: BriefModel) => {
  proj.isFavourite = proj.isFavourite === '1' ? '0' : '1';
  await setModelFav({
    isFavourite: proj.isFavourite,
    modelId: proj.modelId,
  });
};

const handleDel = async (modelId: string) => {
  await delModel(modelId);
};

onMounted(async () => {
  const res = await getModelList();
  projs.value = res.data ?? [];
});
</script>
