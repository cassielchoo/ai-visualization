<template>
  <v-btn :block="true" prepend-icon="mdi-help" variant="plain">
    怎么使用
    <v-tooltip
      activator="parent"
      open-delay="200"
      style="color:aquamarine;color"
      v-model="show"
    >
      <v-sheet class="py-3" rounded="xl" theme="dark" style="line-height: 2rem">
        <!-- <video
                :src="demoMp4"
                width="550"
                autoplay
              ></video> -->
        <v-row><v-img :src="demoGif" width="300"></v-img></v-row>
        <v-row>1.从左侧工具目录内选择相应模块</v-row>
        <v-row>2.将所选择的模块进行连线</v-row>
        <v-row>3.点击相应模块来设置相关参数</v-row>
        <v-row>4.点击相应按钮进行模型训练预测</v-row>
      </v-sheet>
    </v-tooltip>
  </v-btn>
</template>

<script lang="ts" setup>
import demoMp4 from '@/assets/howtouse_demo.mp4';

import demoGif from '@/assets/howtouse_demo.gif';
import { ref } from 'vue';

const show = ref(true);
</script>

<style>
#v-tooltip-1 {
  --v-theme-surface-variant: 33,33,33
}
</style>
