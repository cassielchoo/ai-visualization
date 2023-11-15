<template>
  <aside style="z-index: 99">
    <v-card
      color="background"
      rounded="xl"
      style="
        overflow: auto;
        top: 4.5rem;
        right: 1.6rem;
        width: 23rem;
        position: fixed;
      "
      variant="flat"
      v-if="true"
    >
      <v-card-title>训练结果</v-card-title>
      <v-card-text class="my-2">
        <div style="height: 16rem">
          <div id="chart1" style="width: 100%; height: 100%"></div>
        </div>
        <v-sheet class="pa-3" rounded="lg">
          <v-row>
            <v-col
              cols="12"
              v-for="(val, key, index) in data.performance"
              :key="key"
            >
              <span>
                {{ cnTrans[index] }} ({{ key }}) : {{ val.toFixed(4) }}
              </span>
            </v-col>
          </v-row>
        </v-sheet>
      </v-card-text>
    </v-card>
  </aside>
</template>

<script setup lang="ts">
import { GraphNode, useVueFlow } from '@vue-flow/core';
import { onMounted } from 'vue';
import * as echarts from 'echarts';
const { getNodes, onPaneReady } = useVueFlow();

const data = {
  loss: {
    '44': 0.09212889387144722,
    '45': 0.09195470404002813,
    '46': 0.09162558122368851,
    '47': 0.09137154970239524,
    '48': 0.09108825343438764,
    '49': 0.09084222110778904,
    '10': 0.09222726171695003,
    '11': 0.09243722653275874,
    '12': 0.09285413217075694,
    '13': 0.0932212942595325,
    '14': 0.09372011006911198,
    '15': 0.09380780214218246,
    '16': 0.09430041359148814,
    '17': 0.09480028810399249,
    '18': 0.09470740656092533,
    '19': 0.09533640387584583,
    '0': 0.31420073210572447,
    '1': 0.22322012586428402,
    '2': 0.2033062337863151,
    '3': 0.14127539418887153,
    '4': 0.13024939897297597,
    '5': 0.11196731085380876,
    '6': 0.101888178746188,
    '7': 0.09734249259875753,
    '8': 0.09406441656903831,
    '9': 0.09284618549058277,
    '20': 0.09516795357703803,
    '21': 0.09535164315998597,
    '22': 0.09555869779839479,
    '23': 0.09576717153790051,
    '24': 0.09563584078065185,
    '25': 0.09590720381084489,
    '26': 0.0954676437980981,
    '27': 0.09570710867350947,
    '28': 0.09529637876061166,
    '29': 0.09501319629447916,
    '30': 0.0955237624905164,
    '31': 0.09515847809512884,
    '32': 0.0950837985500481,
    '33': 0.09453041919705178,
    '34': 0.09422498923365147,
    '35': 0.09430524974072195,
    '36': 0.09380772327468118,
    '37': 0.09367894051764052,
    '38': 0.09367635730123358,
    '39': 0.09337039060659794,
    '40': 0.09305944121313509,
    '41': 0.09277364050773665,
    '42': 0.0923510040244975,
    '43': 0.09244197962738092,
  },
  performance: {
    'F1 Score': 1.0,
    Precision: 1.0,
    Recall: 1.0,
  },
};

const cnTrans = ['F1值', '精准率', '召回率'];

const init = () => {
  var myChart = echarts.init(document.getElementById('chart1'));

  // Draw the chart
  myChart.setOption({
    title: {
      text: 'Loss',
      padding:[0,20,10,10]
    },
    tooltip: {},
    xAxis: {
      type: 'category',
      data: Object.keys(data.loss),
    },
    yAxis: {
      type: 'value',
    },
    series: [
      {
        data: Object.values(data.loss),
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
  console.log(getNodes.value);

  init();
});
</script>

<style scoped></style>
