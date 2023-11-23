<template>
  <v-card
    max-width="500"
    min-width="224"
    class="rounded-xl"
    v-bind="props"
    elevation="6"
  >
    <v-img
      v-if="sharedproj.thumbnailUrl"
      :src="sharedproj.thumbnailUrl"
      height="250px"
      cover
      class="ma-3 rounded-xl"
    ></v-img>

    <v-sheet
      v-else
      class="align-center justify-center ma-3 rounded-xl d-flex"
      height="250px"
      :src="sharedproj.thumbnailUrl"
      style="cursor: pointer; font-size: 2rem"
    >
      {{ sharedproj.modelName }}
    </v-sheet>

    <v-card-title>{{ sharedproj.modelName || '未命名项目' }}</v-card-title>

    <v-card-actions>
      <v-list-item class="w-100" variant="flat">
        <template v-slot:prepend>
          <v-avatar
            color="grey-darken-3"
            :image="sharedproj.UserPhoto"
          ></v-avatar>
        </template>

        <v-list-item-title>{{ sharedproj.UserName }}</v-list-item-title>

        <v-list-item-subtitle>
          {{ sharedproj.lastEditTime }}
        </v-list-item-subtitle>

        <template v-slot:append>
          <v-tooltip text="添加为个人项目" location="top">
            <template v-slot:activator="{ props }">
              <v-btn
                class="justify-self-end"
                icon
                v-bind="props"
                variant="text"
                color="primary"
                @click="fork"
              >
                <v-icon icon="mdi-plus"></v-icon>
              </v-btn>
            </template>
          </v-tooltip>
        </template>
      </v-list-item>
    </v-card-actions>
  </v-card>
</template>

<script setup lang="ts">
import { forkModel } from '@/service/user-model';
import { BriefModel } from '@/types/model';

const props = defineProps<{ sharedproj: BriefModel }>();
const fork = async () => {
  await forkModel(props.sharedproj.modelId);
};
</script>

<style scoped></style>
