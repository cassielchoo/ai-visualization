<template>
  <v-text-field
    type="number"
    label="学习率 Learning Rate"
    variant="underlined"
    v-model="learningRate"
  ></v-text-field>
  <v-text-field
    type="number"
    label="迭代次数 Num of Epochs"
    variant="underlined"
    v-model="epochNum"
  ></v-text-field>
  <v-text-field
    type="number"
    label="最大深度 Max Depth"
    variant="underlined"
    v-model="maxDepth"
  ></v-text-field>

  <v-select
    label="目标函数 Objective Function"
    variant="underlined"
    :items="objFunctions"
    v-model="objFunction"
  ></v-select>
</template>

<script setup lang="ts">
import { Ref, ref } from 'vue';
import objFunctions from './objective-functions'

const learningRate: Ref<number> = ref(0);
const epochNum: Ref<number> = ref(0);
const maxDepth: Ref<number> = ref(0);
const objFunction: Ref<string> = ref('');
</script>

<style scoped></style>
