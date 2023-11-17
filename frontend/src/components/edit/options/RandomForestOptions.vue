<template>
    <v-text-field type="number" label="树的个数 Num of Trees" variant="underlined" v-model="options.NumTrees"></v-text-field>
    <v-text-field type="number" label="深度 Depth (0为不设限)" variant="underlined" v-model="options.depth"></v-text-field>
    <v-text-field type="number" label="随机种子 Seed" variant="underlined" v-model="options.NumSeed"></v-text-field>
    <v-text-field type="number" label="特征采样参数 Num of Attributes" variant="underlined" v-model="options.NumAttribute"></v-text-field>
  <v-row>
    <v-col><v-btn block variant="text" color="primary">恢复默认</v-btn></v-col>
    <v-col><v-btn block variant="tonal" color="primary" @click="saveOptions">保存</v-btn></v-col>
  </v-row>
</template>

<script setup lang="ts">
import { Ref, ref } from 'vue';
import {RandomForestModelProps} from "@/types/model";

const props = defineProps<{
  options: RandomForestModelProps;
}>();

const emits = defineEmits(['save']);
const options = ref({ ...props.options });

const saveOptions = () => {
  emits('save', options.value);
};
</script>

<style scoped>

</style>
