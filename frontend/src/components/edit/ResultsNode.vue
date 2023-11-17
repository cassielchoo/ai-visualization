<template>
  <v-card color="background" rounded="xl" class="node-results" variant="flat">
    <v-card-title>训练结果</v-card-title>
    <v-card-text class="my-2">
      <div style="height: 16rem" v-if="hasLoss">
        <div id="chart1" style="width: 100%; height: 100%"></div>
      </div>
      <v-sheet class="pa-3" rounded="lg">
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
  </v-card>
</template>

<script setup lang="ts">
import { useVueFlow } from '@vue-flow/core';
import { onMounted } from 'vue';
import * as echarts from 'echarts';
import {
  CatBoostModelResData,
  CNNResData,
  FullConnectResData, kMeansModelResData,
  LightGBMResData, RandomForestModelResData,
  RNNResData,
  XgBoostResData
} from "@/types/model";
const { onPaneReady } = useVueFlow();

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
};

const hasLoss='loss' in props.data

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
      data: Object.keys(hasLoss ? props.data.loss : []),
    },
    yAxis: {
      type: 'value',
    },
    series: [
      {
        data: Object.values(hasLoss ? props.data.loss : []),
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

onPaneReady(() => {});

onMounted(() => {
  if (hasLoss) {
    init();
  }
});
</script>

<style scoped>
.node-results {
  width: 25rem;
}
</style>
