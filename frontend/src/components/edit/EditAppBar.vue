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
      <template v-slot:append v-if="projStore.modelType">
        <v-icon>mdi-close</v-icon>
      </template>
    </v-btn>

    <!-- <v-dialog width="500" transition="dialog-top-transition">
      <template v-slot:activator="{ props }">
        <v-btn
          v-bind="props"
          variant="text"
          color="success"
          class="mx-1"
          density="comfortable"
          v-if="projStore.editStatus === 1"
        >
          <v-icon>mdi-hand-back-right</v-icon>
          申请编辑
        </v-btn>
      </template>

      <template v-slot:default="{ isActive }">
        <v-card title="是否申请编辑">
          <v-card-text>点击确定将进入编辑状态，其他人将无法编辑。</v-card-text>

          <v-card-actions>
            <v-spacer></v-spacer>

            <v-btn @click="isActive.value = false">取消</v-btn>
            <v-btn
              @click="
                (projStore.editStatus = 2),
                  (isActive.value = false),
                  setActive()
              "
            >
              确定
            </v-btn>
          </v-card-actions>
        </v-card>
      </template>
    </v-dialog>

    <v-dialog width="500" transition="dialog-top-transition">
      <template v-slot:activator="{ props }">
        <v-btn
          v-bind="props"
          variant="flat"
          color="success"
          class="mx-1"
          density="comfortable"
          v-if="projStore.editStatus === 2"
        >
          正在编辑
        </v-btn>
      </template>

      <template v-slot:default="{ isActive }">
        <v-card title="是否退出编辑模式">
          <v-card-text>退出编辑模式后，其他人将可以自由申请编辑</v-card-text>

          <v-card-actions>
            <v-spacer></v-spacer>

            <v-btn @click="isActive.value = false">取消</v-btn>
            <v-btn
              @click="
                (projStore.editStatus = 1), (isActive.value = false), deactive()
              "
            >
              确定
            </v-btn>
          </v-card-actions>
        </v-card>
      </template>
    </v-dialog>

    <v-btn
      variant="flat"
      color="error"
      class="mx-1"
      density="comfortable"
      v-if="projStore.editStatus === -1"
    >
      不可申请编辑
    </v-btn> -->

    <v-spacer></v-spacer>
    <template v-slot:append>
      <v-btn
        :color="cloudStatus.color"
        density="comfortable"
        variant="text"
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
import { computed, onDeactivated, onMounted, ref, Ref } from 'vue';
import { useRouter } from 'vue-router';
import { useProjectStore } from '@/store/project';
import { useAppStore } from '@/store/app';
import { nanoid } from 'nanoid';

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
import { handleSaveModel } from './save-model';

const { nodes, toObject, addNodes, findNode, removeSelectedElements } =
  useVueFlow();

const router = useRouter();
const projStore = useProjectStore();
const appStore = useAppStore();

const showStatusText: Ref<boolean> = ref(true);

const showTrainDialog: Ref<boolean> = ref(false);

const submit = async () => {
  showTrainDialog.value = false;
  projStore.isReady = false;
  const node: GraphNode = nodes.value.find(
    (n: GraphNode) => n.data.category === 'model',
  )!;

  if (node) {
    const id = nanoid();

    addNodes([
      {
        id,
        type: 'results',
        position: {
          x: node.position.x + 500,
          y: node.position.y - 300,
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
      const node: GraphNode = findNode(id)!;
      node.data.results = res?.data;
      projStore.isReady = true;
    }
  } else {
    appStore.handleGlobalMessaging('请先选择模型类型');
  }
};

const pred = () => {
  removeSelectedElements();
  const modelNode: GraphNode | undefined = nodes.value.find(
    (n: GraphNode) => n.data.category === 'model',
  );
  const resultsNode: GraphNode | undefined = nodes.value.find(
    (n: GraphNode) => n.data.category === 'results',
  );

  if (resultsNode && modelNode) {
    if (projStore.modelType) {
      projStore.modelType = '';
    } else {
      projStore.modelType = modelNode.label as string;
    }
  } else if (!modelNode) {
    appStore.handleGlobalMessaging('请先选择模型类型并训练');
  } else if (!resultsNode) {
    appStore.handleGlobalMessaging('请先训练模型');
  }
};

let autoSave = setInterval(async () => {
  const res = await handleSaveModel(
    projStore.modelInfo.modelId,
    JSON.stringify(toObject() ?? {}),
  );
  if (res.code === 200) {
    projStore.cloudStatusText = '已保存';
  } else {
    projStore.cloudStatusText = '保存失败';
  }

}, 5000);

onDeactivated(() => {
  clearInterval(autoSave);
});

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

const setActive = () => {
  projStore.setActive('zhuyu');
};

const deactive = () => {
  projStore.setActive('无人');
  projStore.editStatus = 1;
};

onMounted(async () => {
  const res = await getUserInfo();
  appStore.user = res.data!;

  // setTimeout(() => {
  //   projStore.setActive('哆嗦熊');
  //   projStore.editStatus = -1;
  // }, 2000);

  // setTimeout(() => {
  //   addNodes({
  //     id: nanoid(),
  //     type: 'default',
  //     position: {
  //       x: 134,
  //       y: 280,
  //     },
  //     data: {
  //       color: '#73d1d0',
  //       accentColor: '#376c6b',
  //       hasOptions: false,
  //     },
  //     label: '从csv/excel导入',
  //     style: (el: GraphNode) => {
  //       if (el.selected)
  //         return {
  //           '--vf-node-text': 'white',
  //           '--vf-node-bg': '#73d1d0',
  //           '--vf-node-color': '#73d1d0',
  //           'border-color': '#376c6b' + '!important',
  //           'font-size': '1.5rem',
  //         };
  //       return {
  //         '--vf-node-text': 'white',
  //         '--vf-node-bg': '#73d1d0',
  //         '--vf-node-color': '#73d1d0',
  //         'font-size': '1.5rem',
  //       };
  //     },
  //   });
  // }, 4000);

  // setTimeout(() => {
  //   projStore.setActive('无人');
  //   projStore.editStatus = 1;
  // }, 6000);
});
</script>
