<template>
  <div v-if="showToolBar" style="z-index: 99">
    <v-card
      rounded="xl"
      variant="flat"
      :color="toolbarColor"
      style="
        z-index: 99;
        width: 16rem;
        overflow: auto;
        top: 4.5rem;
        bottom: 1.8rem;
        left: 1.6rem;
        position: fixed;
      "
    >
      <v-card-title class="d-flex justify-space-between">
        <span class="mt-1">
          <v-btn icon variant="plain">
            <v-icon class="mb-1 mx-1" icon="mdi-menu"></v-icon>
          </v-btn>
          工具目录
        </span>
        <v-btn icon variant="plain" @click="showToolBar = false">
          <v-icon class="mt-1" icon="mdi-arrow-left-circle-outline"></v-icon>
        </v-btn>
      </v-card-title>
      <v-card-subtitle>
        <v-text-field
          :loading="searchLoading"
          v-model="search"
          rounded
          placeholder="搜索项目"
          clearable
          prepend-inner-icon="mdi-magnify"
          flat
          variant="solo-filled"
          density="compact"
        ></v-text-field>
      </v-card-subtitle>
    </v-card>
    <v-card
      :color="toolbarColor"
      rounded="xl"
      variant="flat"
      style="
        z-index: 99;
        width: 16rem;
        overflow: auto;
        top: 11.5rem;
        bottom: 1.8rem;
        left: 1.6rem;
        position: fixed;
      "
    >
      <v-list v-model:opened="open" :bg-color="toolbarColor">
        <v-list-group
          v-for="tool of toolbar"
          :key="tool.value"
          :value="tool.value"
        >
          <template v-slot:activator="{ props }">
            <v-list-item
              v-bind="props"
              :prepend-icon="tool.icon"
              :title="tool.title"
              class="ma-2"
              color="primary"
              variant="plain"
            ></v-list-item>
          </template>

          <v-list-item
            v-for="(child, i) in tool.children"
            :key="i"
            :title="child"
            :value="child"
            color="primary"
            variant="plain"
          ></v-list-item>
        </v-list-group>
      </v-list>
    </v-card>
    <v-card
      :color="toolbarColor"
      rounded="xl"
      variant="flat"
      style="
        z-index: 99;
        width: 16rem;
        top: 49rem;
        bottom: 1.8rem;
        left: 1.6rem;
        position: fixed;
      "
    >
      <div class="pa-5">
        <v-divider class="mx-6 my-3"></v-divider>
        <v-btn block variant="plain" prepend-icon="mdi-help">怎么使用</v-btn>
      </div>
    </v-card>
  </div>
  <canvas-pad></canvas-pad>
</template>

<script lang="ts" setup>
import canvasPad from '@/components/canvasPad.vue';
import { Ref, computed, ref, ComputedRef } from 'vue';
import { useRouter } from 'vue-router';
import { useDisplay, useTheme } from 'vuetify';

const { mobile } = useDisplay();
const theme = useTheme();

let showToolBar: Ref<boolean> = ref(true);

let open: Ref<string[]> = ref([]);

interface ToolBar {
  value: string;
  title: string;
  icon: string;
  children: string[];
}

let toolbar: Ref<ToolBar[]> = ref([
  {
    value: 'import',
    title: '数据导入',
    icon: 'mdi-file-download-outline',
    children: ['从数据库导入', '从csv导入', '从excel导入', '非结构化数据导入'],
  },
  {
    value: 'preprocess',
    title: '数据预处理',
    icon: 'mdi-folder-outline',
    children: [
      '缺失值处理',
      '异常值处理',
      '数据筛选',
      '增删数据',
      '数据合并',
      '数据集划分',
      '文本分词',
      '图像分割',
      '灰度处理',
    ],
  },
  {
    value: 'feature',
    title: '特征工程',
    icon: 'mdi-check-all',
    children: [
      '数据归一化',
      '数据标准化',
      '数据离散化',
      'onehot编码',
      'label编码',
      '描述性统计',
      '词向量转化',
      '相似度计算',
      '图像增强',
    ],
  },
  {
    value: 'model',
    title: '模型选择',
    icon: 'mdi-memory',
    children: [
      '随机森林',
      'lightGBM',
      'Xgboost',
      'Catboost',
      'K-means',
      '全连接神经网络',
      '卷积神经网络',
      '循环神经网络',
      '图神经网络',
    ],
  },
  {
    value: 'idictator',
    title: '评估指标',
    icon: 'mdi-chart-line',
    children: [
      '精准率计算',
      '召回率计算',
      'F1值计算',
      'AUC计算',
      'ROC计算',
      'MSE计算',
      'MAPE计算',
    ],
  },
]);

let search: Ref<string> = ref('');
let searchLoading: Ref<boolean> = ref(false);

let toolbarColor: ComputedRef<string> = computed(() =>
  theme.global.current.value.dark ? '121212' : '#f1f3f4',
);
</script>

<style></style>
