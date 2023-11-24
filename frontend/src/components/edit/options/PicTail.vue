<template>
  <v-row>
    <v-col cols="12"><span>图像大小</span></v-col>
    <v-col cols="6">
      <v-text-field
        type="number"
        label="高"
        variant="underlined"
        v-model="options.height"
      ></v-text-field>
    </v-col>
    <v-col cols="6">
      <v-text-field
        type="number"
        label="宽"
        variant="underlined"
        v-model="options.width"
      ></v-text-field>
    </v-col>
  </v-row>
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


const emits = defineEmits(['save']);
const options = ref({ ...props.options });

const saveOptions = () => {
  emits('save', options.value);
};
</script>

<style scoped></style>
