<template>
  <v-card max-width="500" min-width="224" class="rounded-xl" variant="flat">
    <v-img
      :src="proj.thumbnailUrl"
      height="250px"
      cover
      class="ma-3 rounded-xl"
      @click="toEdit"
      style="cursor: pointer"
    ></v-img>

    <v-card-title>{{ proj.modelName ||  '未命名项目' }}</v-card-title>

    <v-card-actions>
      <v-list-item class="w-100" variant="flat">
        <template v-slot:prepend>
          <v-avatar
            color="grey-darken-3"
            :image="store.user.userPhone"
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
              :icon="proj.isFavourite==='0' ? 'mdi-heart-outline' : 'mdi-heart'"
            ></v-icon>
          </v-btn>
        </template>
      </v-list-item>
    </v-card-actions>
  </v-card>
</template>

<script setup lang="ts">
import { BriefModel } from '@/types/model';
import { useAppStore } from '@/store/app';

import { useRouter } from 'vue-router';

const router=useRouter()

const props=defineProps<{ proj: BriefModel }>();

const store = useAppStore()

const emit = defineEmits(['toggleFavorite']);

const toEdit = () => {
  router.push('/edit/'+ props.proj.modelId)
};



let toggleFavorite = () => {
  emit('toggleFavorite');
};
</script>

<style scoped></style>
