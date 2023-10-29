<template>
  <h3 class="ma-5">从 csv/excel 文件导入</h3>
  <v-file-input
    class="ma-3"
    density="compact"
    clearable
    label="上传文件"
    variant="underlined"
    accept=".csv,application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
    v-model="file"
  ></v-file-input>

  作为

  <v-radio-group v-model="radios">
  <v-radio label="训练集" value="one"></v-radio>
  <v-radio label="测试集" value="two"></v-radio>
  <v-radio label="按比例划分" value="three"></v-radio>
  
</v-radio-group>

</template>

<script setup lang="ts">
import { Ref, ref } from 'vue';

let file: Ref<File | undefined> = ref();
let radios: Ref<string> = ref('');
</script>

<style scoped></style>
