<template>
  <v-text-field
    type="number"
    label="学习率 Learning Rate"
    variant="underlined"
    v-model="options.eta"
  ></v-text-field>
  <v-text-field
    type="number"
    label="迭代次数 Num of Estimators"
    variant="underlined"
    v-model="options.nEstimators"
  ></v-text-field>
  <v-text-field
    type="number"
    label="最大深度 Max Depth (0为不设限)"
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
    <v-col>
      <v-btn block variant="tonal" color="primary" @click="saveOptions">
        保存
      </v-btn>
    </v-col>
  </v-row>
</template>

<script setup lang="ts">
import { Ref, ref } from 'vue';
import objFunctions from './objective-functions';

import {XgBoostModelProps} from "@/types/model";

const props = defineProps<{
  options: XgBoostModelProps;
}>();

const emits = defineEmits(['save']);
const options = ref({ ...props.options });

const saveOptions = () => {
  emits('save', options.value);
};
</script>

<style scoped></style>
