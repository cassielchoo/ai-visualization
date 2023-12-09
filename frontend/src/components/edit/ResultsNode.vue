<template>
  <v-card
    :color="appStore.isDark ? '#212121' : '#fafafa'"
    rounded="xl"
    class="node-results"
    variant="flat"
    elevation="4"
  >
    <div v-if="projStore.isReady">
      <v-card-title>训练结果</v-card-title>
      <v-card-text class="my-2">
        <div style="height: 16rem" v-if="hasLoss">
          <div id="chart1" style="width: 100%; height: 100%"></div>
        </div>
        <v-sheet
          class="pa-5"
          rounded="xl"
          :color="appStore.isDark ? '#121212' : '#e9e9e9'"
        >
          <v-row>
            <v-col cols="12" v-for="(val, key) in data.performance" :key="key">
              <span>
                {{ cnTrans[key] }} ({{ key }}) :
                {{ typeof val === 'number' ? val : val }}
              </span>
            </v-col>
          </v-row>
        </v-sheet>
      </v-card-text>
    </div>
    <div
      v-else
      class="d-flex justify-center align-center"
      style="height: 26rem"
    >
      <v-progress-circular
        :size="40"
        color="primary"
        indeterminate
        class="mx-2"
      ></v-progress-circular>
      <span style="font-size: 1.5rem">正在训练中</span>
    </div>
  </v-card>
</template>

<script setup lang="ts">
import { useVueFlow } from '@vue-flow/core';
import { onMounted, watch } from 'vue';
import * as echarts from 'echarts';
import {
  CatBoostModelResData,
  CNNResData,
  FullConnectResData,
  kMeansModelResData,
  LightGBMResData,
  RandomForestModelResData,
  RNNResData,
  XgBoostResData,
} from '@/types/model';
import { useProjectStore } from '@/store/project';
import { computed } from 'vue';
import { useAppStore } from '@/store/app';
const projStore = useProjectStore();
const appStore = useAppStore();
const { toObject } = useVueFlow();
const props = defineProps<{
  data:
    | kMeansModelResData
    | RandomForestModelResData
    | CatBoostModelResData
    | CNNResData
    | FullConnectResData
    | LightGBMResData
    | RNNResData
    | XgBoostResData;
}>();

const cnTrans = {
  f1score: 'F1值',
  precision: '精准率',
  recall: '召回率',
  SE: '簇内误差平方和',
  cluster_0: '属于该簇的数据序号',
  cluster_1: '属于该簇的数据序号',
  cluster_2: '属于该簇的数据序号',
  center0: '聚类中心',
  center1: '聚类中心',
  center2: '聚类中心',
};

const hasLoss = computed(() => 'loss' in props.data);

const init = () => {
  var myChart = echarts.init(document.getElementById('chart1'));

  // Draw the chart
  myChart.setOption({
    title: {
      text: 'Loss',
      padding: [0, 20, 10, 10],
    },
    tooltip: {},
    xAxis: {
      boundaryGap: true,

      type: 'category',
      data: Object.keys('loss' in props.data ? props.data.loss : []),
    },
    yAxis: {
      type: 'value',
    },
    series: [
      {
        data: Object.values('loss' in props.data ? props.data.loss : []),
        type: 'line',
      },
    ],
    grid: {
      top: '13%',
      left: '2%',
      right: '2%',
      bottom: '8%',
      containLabel: true,
    },
  });
  window.addEventListener('resize', function () {
    myChart.resize();
  });
};

onMounted(() => {
  watch(
    () => projStore.isReady,
    (isReady) => {
      if (isReady) {
        if (hasLoss.value) {
          init();
        }
        stop();
      }
    },
    { deep: true, flush: 'post', immediate: true },
  );
});
</script>

<style scoped>
.node-results {
  width: 25rem;
}
</style>
