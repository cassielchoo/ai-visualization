<template>
  <v-app-bar elevation="2" height="45">
    <template v-slot:prepend>
      <v-app-bar-nav-icon
        class="mr-1"
        density="comfortable"
        icon="mdi-arrow-left"
        @click="goBack"
      ></v-app-bar-nav-icon>
      <span style="font-size: 20px">{{ projStore.modelInfo.modelName }}</span>
    </template>

    <v-spacer></v-spacer>
    <v-btn
      variant="text"
      @click="1"
      color="success"
      density="comfortable"
      class="mx-1"
    >
      <v-icon>mdi-fast-forward</v-icon>
      训练

      <v-dialog v-model="showTrainDialog" activator="parent" width="400">
        <v-sheet
          elevation="12"
          max-width="600"
          rounded="lg"
          width="100%"
          class="pa-6"
        >
          <p class="mb-5" style="font-size: x-large">是否开始训练</p>

          <v-divider class="mb-4"></v-divider>

          <div class="text-end">
            <v-btn
              class="text-none"
              color="primary"
              rounded
              variant="text"
              width="90"
              type="submit"
              @click="showTrainDialog = false"
            >
              取消
            </v-btn>
            <v-btn
              class="text-none"
              color="primary"
              rounded
              variant="flat"
              width="90"
              @click="submit"
            >
              确定
            </v-btn>
          </div>
        </v-sheet>
      </v-dialog>
    </v-btn>

    <v-btn variant="text" @click="pred" class="mx-1" density="comfortable">
      <v-icon>mdi-graph</v-icon>
      预测
      <template v-slot:append v-if="openPred">
        <v-icon>mdi-close</v-icon>
      </template>
    </v-btn>

    <v-btn variant="text" color="success" class="mx-1" density="comfortable" @click="editStatus=2" v-if="editStatus===1">
      <v-icon>mdi-hand-back-right</v-icon>
      申请编辑
    </v-btn>

    <v-btn variant="flat" color="success" class="mx-1" density="comfortable" @click="editStatus=1" v-if="editStatus===2">
      正在编辑
    </v-btn>

    <v-btn variant="flat" color="error" class="mx-1" density="comfortable" v-if="editStatus===-1">
      不可申请编辑
    </v-btn>

    <v-spacer></v-spacer>
    <template v-slot:append>
      <v-btn
        :color="cloudStatus.color"
        density="comfortable"
        variant="text"
        @click="1"
        width="120"
      >
        <v-icon>{{ cloudStatus.icon }}</v-icon>
        <span v-if="showStatusText" class="mx-1">
          {{ projStore.cloudStatusText }}
        </span>
      </v-btn>

      <v-btn
        variant="text"
        density="comfortable"
        :icon="
          appStore.isDark ? 'mdi-weather-night' : 'mdi-white-balance-sunny'
        "
        @click="appStore.toggleTheme"
      ></v-btn>
      <v-list-item
        density="compact"
        :prepend-avatar="appStore.user.userPhoto"
        class="mx-3"
        variant="flat"
        @click="1"
      >
        {{ appStore.user.userName }}
      </v-list-item>
    </template>
  </v-app-bar>
</template>

<script lang="ts" setup>
import { getUserInfo } from '@/service/users';
import { computed, onMounted, ref, Ref } from 'vue';
import { useRouter } from 'vue-router';
import { useProjectStore } from '@/store/project';
import { useAppStore } from '@/store/app';
import {
  kMeansModel,
  randomForestModel,
  catBoostModel,
  CNNModel,
  fullConnectModel,
  lightGBMModel,
  RNNModel,
  xgBoostModel,
} from '@/service/model';
import { GraphNode, useVueFlow } from '@vue-flow/core';
import { handleSaveModel } from '@/components/edit/save-model';

const { nodes, toObject, addNodes, findNode } = useVueFlow();

const router = useRouter();
const projStore = useProjectStore();
const appStore = useAppStore();

const showStatusText: Ref<boolean> = ref(true);

const showTrainDialog: Ref<boolean> = ref(false);

const editStatus = ref(1);

const submit = async () => {
  showTrainDialog.value = false;
  projStore.isReady = false;
  const node: GraphNode = nodes.value.find(
    (n: GraphNode) => n.data.category === 'model',
  );

  addNodes([
    {
      id: nodes.value.length.toString(),
      type: 'results',
      position: {
        x: node.position.x + 400,
        y: node.position.y,
      },
      data: {
        hasOptions: false,
        category: 'results',
        results: {},
      },
      label: '训练结果',
    },
  ]);

  let res;
  switch (node.label) {
    case '随机森林':
      res = await randomForestModel(node.data.options);

      break;
    case 'lightGBM':
      res = await lightGBMModel(node.data.options);
      break;
    case 'Xgboost':
      res = await xgBoostModel(node.data.options);
      break;
    case 'Catboost':
      res = await catBoostModel(node.data.options);
      break;
    case 'K-means':
      res = await kMeansModel(node.data.options);
      break;
    case '全连接神经网络':
      res = await fullConnectModel(node.data.options);
      break;
    case '卷积神经网络':
      res = await CNNModel(node.data.options);
      break;
    case '循环神经网络':
      res = await RNNModel(node.data.options);
      break;
  }

  if (res?.code === 200) {
    const node: GraphNode = findNode((nodes.value.length - 1).toString());
    node.data.results = res?.data;
    await handleSaveModel(
      projStore.modelInfo.modelId,
      JSON.stringify(toObject() ?? {}),
    );
    projStore.isReady = true;
  }
};

const openPred = ref(false);

const pred = () => {
  const node: GraphNode = nodes.value.find(
    (n: GraphNode) => n.data.category === 'model',
  );
  if (openPred.value) {
    projStore.modelType = '';
  } else {
    projStore.modelType = node.label as string;
  }
  openPred.value = !openPred.value;
};

const cloudStatus = computed(() => {
  if (projStore.cloudStatusText === '已保存')
    return {
      icon: 'mdi-cloud-check-outline',
      color: 'success',
    };
  if (projStore.cloudStatusText === '正在保存') {
    return {
      icon: 'mdi-cloud-sync-outline',
      color: 'warning',
    };
  }
  return {
    icon: 'mdi-cloud-off-outline',
    color: 'error',
  };
});

const goBack = () => {
  router.push({
    name: 'project',
  });
};

onMounted(async () => {
  const res = await getUserInfo();
  appStore.user = res.data!;
});
</script>
