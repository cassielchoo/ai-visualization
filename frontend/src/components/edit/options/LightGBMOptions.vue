<template>
  <v-text-field
    type="number"
    label="学习率 Learning Rate"
    variant="underlined"
    v-model="options.learningRate"
  ></v-text-field>
  <v-text-field
    type="number"
    label="迭代次数 Num of Epochs"
    variant="underlined"
    v-model="options.nEpochs"
  ></v-text-field>
  <v-text-field
    type="number"
    label="最大深度 Max Depth"
    variant="underlined"
    v-model="options.depth"
  ></v-text-field>

  <v-select
    label="目标函数 Objective Function"
    variant="underlined"
    :items="objFunctions"
    v-model="options.objFunc"
  ></v-select>
    <v-row>
    <v-col><v-btn block variant="text" color="primary">恢复默认</v-btn></v-col>
    <v-col><v-btn block variant="tonal" color="primary" @click="saveOptions">保存</v-btn></v-col>
  </v-row>
</template>

<script setup lang="ts">
import { Ref, ref } from 'vue';
import objFunctions from './objective-functions'
import { LightGBMProps } from '@/service/request';

const props = defineProps<{
  options: LightGBMProps;
}>();

const emits = defineEmits(['save']);
const options = ref({ ...props.options });

const saveOptions = () => {
  emits('save', options.value);
};
</script>

<style scoped></style>
