<template>
  <v-file-input
    density="compact"
    clearable
    label="上传文件"
    variant="underlined"
    hide-details
    accept=".zip"
    v-model="files"
    class="my-4"
  >
    <template v-slot:selection="{ fileNames }">
      <template v-for="(fileName, index) in fileNames" :key="fileName">
        <v-chip
          v-if="index < 2"
          color="deep-purple-accent-4"
          label
          size="small"
          class="me-2"
        >
          {{ fileName }}
        </v-chip>

        <span
          v-else-if="index === 2"
          class="text-overline text-grey-darken-3 mx-2"
        >
          +{{ files.length - 2 }} File(s)
        </span>
      </template>
    </template>
  </v-file-input>

  <v-btn block variant="tonal" color="primary" @click="save">导入</v-btn>
</template>

<script setup lang="ts">
import { Ref, ref } from 'vue';
import { useAppStore } from '@/store/app';

const appStore=useAppStore()
const files: Ref<File[]> = ref([]);

const save = () => {
  appStore.handleGlobalMessaging('导入成功')
}
</script>

<style scoped></style>
