<template>
  <v-container class="pa-8 pt-0">
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
</template>

<script lang="ts" setup>
import { Ref, ref } from 'vue';
import ModelCard from '@/components/stage/project/ModelCard.vue';

import { onMounted } from 'vue';
import { delModel, getModelList, setModelFav } from '@/service/user-model';
import { BriefModel } from '@/types/model';

import { useRoute } from 'vue-router';
const route = useRoute();

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
  const md = await getModelList(
    route.meta.type as 'fav' | 'shared' | undefined,
  );
  projs.value = md.data ?? [];
});
</script>
