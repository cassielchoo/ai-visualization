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
      v-if="type && getSelectedNodes.length === 0"
    >
        <v-card-title>预测数据</v-card-title>
        <v-card-text v-if="type === '卷积神经网络'" class="mt-2">
          <v-file-input
            density="compact"
            clearable
            label="上传文件"
            variant="underlined"
            hide-details
            accept="image/*"
            v-model="files"
          >
            <template v-slot:selection="{ fileNames }">
              <template v-for="(fileName, index) in fileNames" :key="fileName">
                <v-chip
                  v-if="index < 2"
                  color="primary"
                  label
                  size="small"
                  class="me-2"
                >
                  {{ fileName }}
                </v-chip>

                <span
                  v-else-if="index === 2"
                  class="text-overline text-grey-darken-3 mx-2"
                >
                  +{{ files.length - 2 }} File(s)
                </span>
              </template>
            </template>
          </v-file-input>
        </v-card-text>
        <v-card-text v-else-if="type === '循环神经网络'">
          <v-text-field
            label="输入文本"
            variant="underlined"
            v-model="riverName"
          ></v-text-field>
        </v-card-text>
        <v-card-text v-else>
          <v-text-field
            type="number"
            label="输入花萼长度"
            variant="underlined"
            v-model="sepalLength"
          ></v-text-field>
          <v-text-field
            type="number"
            label="输入花萼宽度"
            variant="underlined"
            v-model="sepalWidth"
          ></v-text-field>
          <v-text-field
            type="number"
            label="输入花瓣长度"
            variant="underlined"
            v-model="petalLength"
          ></v-text-field>
          <v-text-field
            type="number"
            label="输入花瓣宽度"
            variant="underlined"
            v-model="petalWidth"
          ></v-text-field>
        </v-card-text>
        <v-card-text v-if="showResult">
          预测结果： {{ result }}
        </v-card-text>
        <v-card-actions>
          <v-btn
            block
            variant="tonal"
            color="primary"
            class="mb-1"
            @click="submit"
          >
            预测
          </v-btn>
        </v-card-actions>
    </v-card>
  </aside>
</template>

<script lang="ts" setup>
import { pred } from '@/service/model';
import { useVueFlow } from '@vue-flow/core';
import { Ref, ref } from 'vue';
import { inject } from 'vue';
const { getSelectedNodes } = useVueFlow();

const type: Ref<string> = inject('type', ref(''));

const showResult = ref(false);
const result = ref('');

const files: Ref<File[]> = ref([]);
const riverName = ref();

const sepalLength = ref();
const sepalWidth = ref();
const petalLength = ref();
const petalWidth = ref();

const modelNameMap = {
  随机森林: 'randomforest',
  lightGBM: 'lightgbm',
  Xgboost: 'xgboost',
  Catboost: 'catboost',
  全连接神经网络: 'fullconnect',
  卷积神经网络: 'CNN',
  循环神经网络: 'RNN',
};

const submit = async () => {
  let res;
  if (type.value === '卷积神经网络') {
    res = await pred({
      modelType: modelNameMap[type.value],
      name: files.value[0].name.substring(
        0,
        files.value[0].name.lastIndexOf('.'),
      ),
    });
  } else if (type.value === '循环神经网络') {
    res = await pred({
      modelType: modelNameMap[type.value],
      name: riverName.value,
    });
  } else if (
    type.value === '随机森林' ||
    type.value === 'lightGBM' ||
    type.value === 'Xgboost' ||
    type.value === 'Catboost' ||
    type.value === '全连接神经网络'
  ) {
    res = await pred({
      modelType: modelNameMap[type.value],
      sepal_length: sepalLength.value,
      sepal_width: sepalWidth.value,
      petal_length: petalLength.value,
      petal_width: petalWidth.value,
    });
  }
  if (res?.code === 200) {
    showResult.value = true;
    result.value = res.data?.result!;
  }
};
</script>

<style></style>
