<template>
    <v-text-field type="number" label="簇个数 Num of Dusters" variant="underlined" v-model="options.NUmClusters"></v-text-field>
      <v-row>
    <v-col><v-btn block variant="text" color="primary">恢复默认</v-btn></v-col>
    <v-col><v-btn block variant="tonal" color="primary" @click="saveOptions">保存</v-btn></v-col>
  </v-row>
</template>

<script setup lang="ts">
import { Ref, ref } from 'vue';
import {kMeansModelProps} from "@/types/model";
const props = defineProps<{
  options: kMeansModelProps;
}>();

const emits = defineEmits(['save']);
const options = ref({ ...props.options });

const saveOptions = () => {
  emits('save', options.value);
};
</script>

<style scoped>

</style>
