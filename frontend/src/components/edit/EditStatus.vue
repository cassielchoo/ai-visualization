<template>
  <v-card
    rounded
    style="position: fixed; right: 1.6rem; bottom: 5rem"
    elevation="2"
  >
    <v-card-title style="color: gray; font-size: 1rem">
      <span :style="{color: projStore.currentEditing!=='无人'?'rgb(0, 0, 0)':'gray'}">{{ projStore.currentEditing }}</span>
      正在编辑
    </v-card-title>
    <v-card-text>
      <v-avatar
        v-for="(avatar, i) of projStore.currentOnline"
        :key="i"
        class="mx-1"
        style="box-sizing: content-box; border-width: 4px"
        :style="{
          borderStyle: avatar.editing ? 'solid' : 'none',
          borderColor: avatar.editing ? 'rgb(var(--v-theme-success))' : 'none',
        }"
      >
        <v-img :src="avatar.avatar" :alt="avatar.name"></v-img>
      </v-avatar>
    </v-card-text>
  </v-card>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { useProjectStore } from '@/store/project';
const projStore=useProjectStore()
</script>

<style scoped></style>
