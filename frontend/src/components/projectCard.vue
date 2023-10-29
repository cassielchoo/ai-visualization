<template>
  <v-card max-width="500" min-width="224" class="rounded-xl" variant="flat">
    <v-img
      :src="info.cover"
      height="250px"
      cover
      class="ma-3 rounded-xl"
    ></v-img>

    <v-card-title>{{ info.title }}</v-card-title>

    <v-card-actions>
      <v-list-item class="w-100" variant="flat">
        <template v-slot:prepend>
          <v-avatar
            color="grey-darken-3"
            image="https://avataaars.io/?avatarStyle=Transparent&topType=ShortHairShortCurly&accessoriesType=Prescription02&hairColor=Black&facialHairType=Blank&clotheType=Hoodie&clotheColor=White&eyeType=Default&eyebrowType=DefaultNatural&mouthType=Default&skinColor=Light"
          ></v-avatar>
        </template>

        <v-list-item-title>{{ info.name }}</v-list-item-title>

        <v-list-item-subtitle>{{ info.date }}</v-list-item-subtitle>

        <template v-slot:append>
          <v-btn
            class="justify-self-end"
            icon
            variant="plain"
            @click="handleLike"
          >
            <v-icon
              :icon="info.isLiked ? 'mdi-heart' : 'mdi-heart-outline'"
            ></v-icon>
          </v-btn>
        </template>
      </v-list-item>
    </v-card-actions>
  </v-card>
</template>

<script setup lang="ts">
import { Ref, ref } from 'vue';

interface projectInfo {
  cover: string;
  title: string;
  avatar: string;
  name: string;
  date: string;
  isLiked: boolean;
}

let info: Ref<projectInfo> = ref({
  cover: 'https://cdn.vuetifyjs.com/images/cards/sunshine.jpg',
  title: 'Top western road trips',
  avatar: 'https://cdn.vuetifyjs.com/images/cards/sunshine.jpg',
  name: '小明',
  date: '2023/10/21',
  isLiked: false,
});

let handleLike = () => {
  info.value.isLiked = !info.value.isLiked;
};
</script>

<style scoped></style>
