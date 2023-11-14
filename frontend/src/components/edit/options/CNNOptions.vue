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
  <v-text-field
    type="number"
    label="遍历次数 Num of Epochs"
    variant="underlined"
    v-model="options.nEpochs"
  ></v-text-field>
  <v-text-field
    type="number"
    label="隐藏神经元个数 Num of Hidden Nodes"
    variant="underlined"
    v-model="options.numHiddenNodes"
  ></v-text-field>
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
import { CNNModelProps } from '@/service/request';

const props = defineProps<{
  options: CNNModelProps;
}>();

const emits = defineEmits(['save']);
const options = ref({ ...props.options });

const saveOptions = () => {
  emits('save', options.value);
};
</script>

<style scoped></style>
