<template>
  <v-text-field
    label="主机地址"
    variant="underlined"
    v-model="options.address"
  ></v-text-field>
  <v-text-field
    label="用户名"
    variant="underlined"
    v-model="options.username"
  ></v-text-field>
  <v-text-field
    label="密码"
    variant="underlined"
    v-model="options.password"
  ></v-text-field>
  <v-text-field
    label="表名"
    variant="underlined"
    v-model="options.table"
  ></v-text-field>
  <h4 class="my-3">作为</h4>

  <v-radio-group v-model="options.radios" color="secondary">
    <v-radio label="训练集" :value="1"></v-radio>
    <v-radio label="测试集" :value="2"></v-radio>
    <v-radio label="按比例划分(训练集/测试集)" :value="3"></v-radio>
  </v-radio-group>
  <v-slider v-model="options.slider" v-if="options.radios === 3">
    <template v-slot:prepend>
      {{ 100 - Math.floor(options.slider) }}
    </template>
    <template v-slot:append>
      {{ Math.floor(options.slider) }}
    </template>
  </v-slider>
  <v-btn block variant="tonal" color="primary">导入</v-btn>
</template>

<script setup lang="ts">
import { useVueFlow } from '@vue-flow/core';
import { Ref, ref } from 'vue';

const options = ref({
  address: '',
  username: '',
  password: '',
  table: '',
  radios: 0,
  slider: 50,
});

</script>

<style scoped></style>
