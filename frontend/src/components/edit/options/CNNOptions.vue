<template>
  <v-text-field
    type="number"
    label="学习率 Learning Rate"
    variant="underlined"
    v-model="options.learningRate"
  ></v-text-field>
  <v-text-field
    type="number"
    label="批次大小 Batch Size"
    variant="underlined"
    v-model="options.batchSize"
  ></v-text-field>
  <v-row>
    <v-col cols="12"><span>卷积核大小</span></v-col>
    <v-col cols="6">
      <v-text-field
        type="number"
        label="高"
        variant="underlined"
        v-model="options.kernelSize.height"
      ></v-text-field>
    </v-col>
    <v-col cols="6">
      <v-text-field
        type="number"
        label="宽"
        variant="underlined"
        v-model="options.kernelSize.width"
      ></v-text-field>
    </v-col>
  </v-row>
  <v-text-field
    type="number"
    label="训练轮次 Num of Epochs"
    variant="underlined"
    v-model="options.nEpochs"
  ></v-text-field>
  <v-text-field
    type="number"
    label="隐藏层数量 Num of Hidden Layers"
    variant="underlined"
    v-model="options.numHiddenLayers"
  ></v-text-field>
  <v-text-field
    type="number"
    label="隐藏神经元个数 Num of Hidden Nodes"
    variant="underlined"
    v-model="options.numHiddenNodes"
  ></v-text-field>
  <v-select
    label="激活函数 Activation Function"
    variant="underlined"
    :items="activFunc"
    v-model="options.activFunc"
  ></v-select>
  <v-row>
    <v-col><v-btn block variant="text" color="primary">恢复默认</v-btn></v-col>
    <v-col>
      <v-btn block variant="tonal" color="primary" @click="saveOptions">
        保存
      </v-btn>
    </v-col>
  </v-row>
</template>

<script setup lang="ts">
import { ref } from 'vue';

import { CNNModelProps } from '@/types/model';

const props = defineProps<{
  options: CNNModelProps;
}>();

const activFunc = ['Relu', 'Sigmoid', 'Tanh'];

const emits = defineEmits(['save']);
const options = ref({ ...props.options });

const saveOptions = () => {
  emits('save', options.value);
};
</script>

<style scoped></style>
