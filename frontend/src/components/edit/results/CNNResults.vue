<template>
  <div style="height: 20rem;">
    <div id="chart1" style="width: 100%; height: 100%"></div>
  </div>
</template>

<script setup lang="ts">
import * as echarts from 'echarts';
import { onMounted } from 'vue';

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
      data: ['A', 'B', 'C'],
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

onMounted(() => {
  init();
});
</script>

<style scoped></style>
