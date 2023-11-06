<template>
  <v-file-input
    density="compact"
    clearable
    label="上传文件"
    variant="underlined"
    hide-details
    accept=".csv,application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
    v-model="files"
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
    <template v-slot:append>
      <v-btn block variant="tonal" color="primary">预览</v-btn>
    </template>
  </v-file-input>

  <h4 class="my-3">作为</h4>

  <v-radio-group v-model="radios" color="secondary">
    <v-radio label="训练集" :value="1"></v-radio>
    <v-radio label="测试集" :value="2"></v-radio>
    <v-radio label="按比例划分(训练集/测试集)" :value="3"></v-radio>
  </v-radio-group>
  <v-slider v-model="slider" v-if="radios === 3">
    <template v-slot:prepend>
      {{ 100 - Math.floor(slider) }}
    </template>
    <template v-slot:append>
      {{ Math.floor(slider) }}
    </template>
  </v-slider>
  <v-btn block variant="tonal" color="primary">导入</v-btn>
</template>

<script setup lang="ts">
import { Ref, ref } from 'vue';

let files: Ref<File[]> = ref([]);
let radios: Ref<number> = ref(1);
let slider: Ref<number> = ref(50);
</script>

<style scoped></style>
