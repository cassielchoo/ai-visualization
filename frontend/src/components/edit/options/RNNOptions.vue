<template>
  <v-text-field
    type="number"
    label="学习率 Learning Rate"
    variant="underlined"
    v-model="learningRate"
  ></v-text-field>
  <v-text-field
    type="number"
    label="批次大小 Batch Size"
    variant="underlined"
    v-model="batchSize"
  ></v-text-field>
  <v-text-field
    type="number"
    label="隐藏单元个数 Num of Hidden Units"
    variant="underlined"
    v-model="hiddenNum"
  ></v-text-field>
</template>

<script setup lang="ts">
import { Ref, ref } from 'vue';

const learningRate: Ref<number> = ref(0);
const batchSize: Ref<number> = ref(0);
const hiddenNum: Ref<number> = ref(0);
</script>

<style scoped></style>
