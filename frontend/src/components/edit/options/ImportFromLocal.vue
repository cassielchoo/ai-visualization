<template>
  <v-select
    clearable
    placeholder="点击选择数据集"
    variant="underlined"
    :items="['iris_nolabels', 'mnist', 'isRiver', 'titanic']"
  ></v-select>

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
  <v-btn block variant="tonal" color="primary" @click="save">导入</v-btn>
</template>

<script setup lang="ts">
import { useVueFlow } from '@vue-flow/core';
import { Ref, ref } from 'vue';
import { useAppStore } from '@/store/app';

const appStore = useAppStore();
const files: Ref<File[]> = ref([]);
const radios: Ref<number> = ref(1);
const slider: Ref<number> = ref(50);
const save = () => {
  appStore.handleGlobalMessaging('导入成功');
};
</script>

<style scoped></style>
