<template>
  <!-- 普通大小工具栏 -->
  <aside v-if="!mini" style="z-index: 99">
    <v-card
      :color="toolbarColor"
      rounded="xl"
      style="
        z-index: 99;
        overflow: auto;
        top: 4.5rem;
        bottom: 1.8rem;
        left: 1.6rem;
        position: :fixed;
      "
      
      class="cards"

      :class="{ shake: !mini }"

      variant="flat"
    >
      <v-card-title class="d-flex justify-space-between">
        <span class="mt-1">
          <v-btn icon variant="plain">
            <v-icon class="mb-1 mx-1" icon="mdi-menu"></v-icon>
          </v-btn>
          工具目录
        </span>
        <v-btn icon variant="plain" @click="narrowTool">
          <v-icon class="mt-1" icon="mdi-arrow-left-circle-outline"></v-icon>
        </v-btn>
      </v-card-title>
      <v-card-subtitle>
        <v-text-field
          v-model="search"
          :clearable="true"
          :flat="true"
          :loading="searchLoading"
          density="compact"
          placeholder="搜索项目"
          prepend-inner-icon="mdi-magnify"
          rounded
          variant="solo-filled"
        ></v-text-field>
      </v-card-subtitle>
    </v-card>
    <v-card
      :color="toolbarColor"
      rounded="xl"
      class="cards"
      :class="{ shake: !mini }"
      style="
        z-index: 99;
        overflow: auto;
        top: 12rem;
        bottom: 6.5rem;
        left: 1.6rem;
        position: :fixed;
      "
      variant="flat"
    >
      <v-list v-model:opened="open" :bg-color="toolbarColor">
        <v-list-group
          v-for="tool of toolbar"
          :key="tool.value"
          :value="tool.value"
          class="nodes"
        >
          <template v-slot:activator="{ props }">
            <v-list-item
              :prepend-icon="tool.icon"
              :title="tool.title"
              class="ma-2"
              color="primary"
              v-bind="props"
              variant="plain"
            ></v-list-item>
          </template>

          <v-list-item
            @dragstart="onDragStart($event, tool, child)"
            :draggable="true"
            v-for="child in tool.children"
            :key="child"
            :title="child"
            :value="child"
            color="primary"
            variant="plain"
            @click="addNode(tool, child)"
          ></v-list-item>
        </v-list-group>
      </v-list>
    </v-card>
    <v-card
      :color="toolbarColor"
      class="pa-3 cards"
      rounded="xl"
      style="z-index: 99; bottom: 1.8rem; left: 1.6rem; position: fixed"
      variant="flat"
      :class="{ shake: !mini }"
    >
      <v-divider class="mx-8 my-3"></v-divider>
      <v-btn :block="true" prepend-icon="mdi-help" variant="plain">
        怎么使用
      </v-btn>
    </v-card>
  </aside>

  <!-- 迷你工具栏 -->
</template>

<script lang="ts" setup>
import { computed, ComputedRef, ref, Ref } from 'vue';
import { useTheme } from 'vuetify';
const emit = defineEmits(['addnode']);

const theme = useTheme();

const mini: Ref<boolean> = ref(false);

const open: Ref<string[]> = ref([]);

interface Tool {
  value: string;
  type: string;
  title: string;
  icon: string;
  color: string;
  children: string[];
}

const onDragStart = (event: DragEvent, tool: Tool, child: string) => {
  if (event.dataTransfer) {
    event.dataTransfer.setData('type', tool.type);
    event.dataTransfer.setData('color', tool.color);
    event.dataTransfer.setData('name', child);

    event.dataTransfer.setData('application/vueflow', tool.type);

    event.dataTransfer.effectAllowed = 'move';
  }
};

const toolbar: Ref<Tool[]> = ref([
  {
    value: 'import',
    type: 'input',
    title: '数据导入',
    icon: 'mdi-file-download-outline',
    color: '#6bd2d1',
    children: ['从数据库导入', '从csv导入', '从excel导入', '非结构化数据导入'],
  },
  {
    value: 'preprocess',
    type: 'default',
    title: '数据预处理',
    icon: 'mdi-folder-outline',
    color: '#c8a4ee',
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
    type: 'default',
    title: '特征工程',
    icon: 'mdi-check-all',
    color: '#fa6e9a',
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
    type: 'default',
    title: '模型选择',
    icon: 'mdi-memory',
    color: '#f7c15d',
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
    value: 'indictator',
    type: 'output',
    title: '评估指标',
    icon: 'mdi-chart-line',
    color: '#8aef6d',
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

const search: Ref<string> = ref('');
const searchLoading: Ref<boolean> = ref(false);

const toolbarColor: ComputedRef<string> = computed(() =>
  theme.global.current.value.dark ? '121212' : '#f1f3f4',
);

const addNode = (tool: Tool, child: string) => {
  emit('addnode', {
    type: tool.type,
    label: child,
    color: tool.color,
    method: 'click',
  });
};

const narrowTool = () => {
  mini.value = true;
  open.value = [];
};

const expandTool = (value?: string) => {
  mini.value = false;
  open.value = value ? [value] : [];
};
</script>

<style>
.shake {
  animation: shake 0.82s cubic-bezier(0.36, 0.07, 0.19, 0.97) both;
}
@keyframes shake {
  0% {
    width: 16rem;
  }
  25% {
    width: 13rem;
  }
  50% {
    width: 10rem;
  }
  75% {
    width: 7rem;
  }
  100% {
    width: 5rem;
  }
}
.cards {
  width: 16rem;
  transition: width 2s linear;
  -webkit-transition: width 2s;
}
</style>
