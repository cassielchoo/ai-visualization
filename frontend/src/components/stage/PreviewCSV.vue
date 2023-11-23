<template>
  <v-card width="800" v-if="!loading">
    <v-card-text>
      <!-- <v-row v-for="(item, i) of data" :key="i">
        <v-col v-for="(block, j) of item" :key="j">{{ block }}</v-col>
      </v-row> -->

      <v-data-table-virtual
        :headers="tableHeaders"
        :items="items"
        height="600"
      ></v-data-table-virtual>
      <span>...</span>
    </v-card-text>
    <v-card-actions class="d-flex justify-end">
      <v-btn @click="emit('close')">关闭</v-btn>
    </v-card-actions>
  </v-card>

  <v-card
    v-else
    width="800"
    height="800"
    class="d-flex justify-center align-center"
  >
    <div>
      <v-progress-circular
        :size="40"
        color="primary"
        indeterminate
        class="mx-2 mb-2"
      ></v-progress-circular>
      <span style="font-size: 1.5rem">正在加载</span>
    </div>
  </v-card>
</template>

<script setup lang="ts">
import { Ref, onMounted, ref } from 'vue';
import { VDataTableVirtual } from 'vuetify/lib/labs/components.mjs';
import * as papa from 'papaparse';
import { getDatasetById } from '@/service/user-dataset';

const emit = defineEmits(['close']);
const props = defineProps(['dataId']);

const tableHeaders: Ref<{ title: string; value: string }[]> = ref([]);

const items: Ref<{}[]> = ref([]);

const formatData = (previewData: string[][]) => {
  const data = previewData.slice(1);
  const fnData = [];

  for (let header of previewData[0]) {
    tableHeaders.value.push({ title: header, value: header });
  }

  for (let d of data) {
    let obj: { [key in (typeof tableHeaders.value)[number]['title']]: string } =
      {};
    for (let i in d) {
      obj[previewData[0][i]] = d[i];
    }
    fnData.push(obj);
  }
  items.value = fnData;
};

const parseCSV = async () => {
  const res = await getDatasetById(props.dataId);

  // 解码Base64字符串为二进制数据
  var binaryData = atob(res.data?.dataURL!);

  // 将二进制数据转换为Uint8Array
  var uint8Array = new Uint8Array(binaryData.length);
  for (var i = 0; i < binaryData.length; i++) {
    uint8Array[i] = binaryData.charCodeAt(i);
  }

  // 创建 Blob 对象
  var blob = new Blob([uint8Array], { type: 'application/octet-stream' });

  // 创建 File 对象
  var fileName = 'example.txt'; // 设置文件名
  var file = new File([blob], fileName, { type: 'application/octet-stream' });

  papa.parse(file, {
    complete: function (results) {
      let previewData: string[][] = [];
      previewData = results.data.slice(0, 11) as string[][];
      formatData(previewData);
    },
  });
};

const loading = ref(false);

onMounted(async () => {
  loading.value = true;
  await parseCSV();
  loading.value = false;
});
</script>

<style scoped></style>
