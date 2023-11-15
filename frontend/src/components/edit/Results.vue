<template>
  <aside style="z-index: 99">
    <v-card
      color="background"
      rounded="xl"
      style="
        overflow: auto;
        top: 4.5rem;
        right: 1.6rem;
        width: 20rem;
        position: fixed;
      "
      variant="flat"
    >
      <v-card-title>训练结果</v-card-title>
      <v-card-text class="my-2">
        <div style="height: 20rem">
          <div id="chart1" style="width: 100%; height: 100%"></div>
        </div>
      </v-card-text>
    </v-card>
  </aside>
</template>

<script setup lang="ts">
import { GraphNode, useVueFlow } from '@vue-flow/core';
import { onMounted } from 'vue';
import * as echarts from 'echarts';
const { getNodes, onPaneReady } = useVueFlow();






const init = () => {
  var myChart = echarts.init(document.getElementById('chart1'));

  // Draw the chart
  myChart.setOption({
    title: {
      text: 'Loss',
    },
    tooltip: {},
    xAxis: {
      type: 'category',
      data: [120, 200, 150],
    },
    yAxis: {
      type: 'value',
    },
    series: [
      {
        data: [120, 200, 150],
        type: 'line',
      },
    ],
  });
  window.addEventListener('resize', function () {
    myChart.resize();
  });
};

onPaneReady(() => {
});

onMounted(() => {
  console.log(getNodes.value);



  init();
});
</script>

<style scoped></style>
