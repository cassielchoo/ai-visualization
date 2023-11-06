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
    label="遍历次数 Num of Epochs"
    variant="underlined"
    v-model="epochNum"
  ></v-text-field>
  <v-text-field
    type="number"
    label="隐藏层中神经元个数 Num of Hidden Nodes"
    variant="underlined"
    v-model="hiddenNum"
  ></v-text-field>
</template>

<script setup lang="ts">
import { Ref, ref } from 'vue';

let learningRate: Ref<number> = ref(0);
let batchSize: Ref<number> = ref(0);
let epochNum: Ref<number> = ref(0);
let hiddenNum: Ref<number> = ref(0);

</script>

<style scoped>
</style>