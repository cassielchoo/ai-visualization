<template>
  <v-hover v-slot="{ isHovering, props }">
    <v-card
      max-width="500"
      min-width="224"
      class="rounded-xl"
      v-bind="props"
      elevation="6"
    >
      <v-img
        v-if="proj.thumbnailUrl"
        :src="proj.thumbnailUrl"
        height="250px"
        cover
        class="ma-3 rounded-xl"
        @click="toEdit"
        style="cursor: pointer"
      ></v-img>

      <v-sheet
        v-else
        class="align-center justify-center ma-3 rounded-xl d-flex"
        height="250px"
        :src="proj.thumbnailUrl"
        @click="toEdit"
        style="cursor: pointer; font-size: 2rem"
      >
        {{ proj.modelName }}
      </v-sheet>

      <v-card-title>{{ proj.modelName || '未命名项目' }}</v-card-title>

      <v-card-actions>
        <v-list-item class="w-100" variant="flat">
          <template v-slot:prepend>
            <v-avatar
              color="grey-darken-3"
              :image="store.user.userPhoto"
            ></v-avatar>
          </template>

          <v-list-item-title>{{ proj.UserName }}</v-list-item-title>

          <v-list-item-subtitle>{{ proj.lastEditTime }}</v-list-item-subtitle>

          <template v-slot:append>
            <v-btn
              class="justify-self-end"
              icon
              variant="plain"
              @click="toggleFavorite"
            >
              <v-icon
                :icon="
                  datasets.isFavourite === '0' ? 'mdi-heart-outline' : 'mdi-heart'
                "
              ></v-icon>
            </v-btn>
          </template>
        </v-list-item>
      </v-card-actions>
      <v-overlay
        scrim="background"
        :model-value="isHovering"
        contained
        class="align-center justify-center"
      >
        <v-tooltip text="进入" location="top" v-model="showToolTip">
          <template v-slot:activator="{ props }">
            <v-btn
              variant="elevated"
              icon
              color="primary"
              class="mx-1"
              @click="toEdit"
              v-bind="props"
            >
              <v-icon>mdi-arrow-right</v-icon>
            </v-btn>
          </template>
        </v-tooltip>

        <v-tooltip
          :text="proj.isFavourite === '0' ? '收藏' : '取消收藏'"
          location="top"
        >
          <template v-slot:activator="{ props }">
            <v-btn
              :variant="proj.isFavourite === '0' ? 'outlined' : 'elevated'"
              icon
              color="primary"
              class="mx-1"
              v-bind="props"
              @click="toggleFavorite"
            >
              <v-icon
                :icon="
                  proj.isFavourite === '0' ? 'mdi-heart-outline' : 'mdi-heart'
                "
              ></v-icon>
            </v-btn>
          </template>
        </v-tooltip>

        <v-tooltip text="删除" location="top" v-if="!deleteAlert">
          <template v-slot:activator="{ props }">
            <v-btn
              variant="elevated"
              icon
              color="error"
              class="mx-1"
              v-bind="props"
              @click="alertToDel"
            >
              <v-icon>mdi-delete-empty</v-icon>
            </v-btn>
          </template>
        </v-tooltip>

        <v-tooltip text="再次点击确认删除" location="top" v-else>
          <template v-slot:activator="{ props }">
            <v-btn
              variant="outlined"
              icon
              color="error"
              class="mx-1"
              v-bind="props"
              @click="handleDel"
            >
              <v-icon>mdi-delete-empty</v-icon>
            </v-btn>
          </template>
        </v-tooltip>
      </v-overlay>
    </v-card>
  </v-hover>
</template>

<script setup lang="ts">
import { BriefModel } from '@/types/model';
import { useAppStore } from '@/store/app';

import { useRouter } from 'vue-router';
import { ref } from 'vue';
import { BriefDataset } from '@/types/request';

const router = useRouter();

const props = defineProps<{ datasets: BriefDataset }>();

const store = useAppStore();

const emit = defineEmits(['togglefavorite', 'handledel']);

const showToolTip=ref(false)
const toEdit = () => {
  showToolTip.value=false
  router.push('/edit/' + props.datasets.dataSetId);
};

const deleteAlert = ref(false);

const toggleFavorite = () => {
  emit('togglefavorite');
};

const alertToDel = () => {
  deleteAlert.value = true;
  setTimeout(() => {
    deleteAlert.value = false;
  }, 3000);
};

const handleDel = () => {
  emit('handledel');
};
</script>

<style scoped></style>
