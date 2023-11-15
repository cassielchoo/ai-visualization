<template>
  <!-- 普通大小工具栏 -->
  <transition name="narrow">
    <aside v-if="!mini" style="z-index: 99">
      <v-card
        color="background"
        rounded="xl"
        class="cards"
        style="overflow: auto; top: 4.5rem; bottom: 1.8rem; position: fixed"
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
            hide-details
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
        color="background"
        rounded="xl"
        style="overflow: auto; top: 12rem; bottom: 6.5rem; position: fixed"
        class="cards"
        variant="flat"
      >
        <v-list v-model:opened="open" bg-color="background">
          <v-list-group
            v-for="toolclass of toolbardata"
            :key="toolclass.value"
            :value="toolclass.value"
          >
            <template v-slot:activator="{ props }">
              <v-list-item
                :prepend-icon="toolclass.icon"
                :title="toolclass.title"
                v-bind="props"
                variant="plain"
                tabindex="-1"
              ></v-list-item>
            </template>

            <div class="px-7">
              <v-sheet
                @dragstart="onDragStart($event, toolclass, tool)"
                :draggable="true"
                v-for="tool in toolclass.children"
                :key="tool.name"
                :value="tool.name"
                style="padding-inline-start: 2rem !important; cursor: grab"
                class="px-7 d-flex justify-center align-center my-2"
                @click="addNode(toolclass, tool)"
                height="50"
                rounded
                :color="toolclass.color"
              >
                <v-tooltip
                  activator="parent"
                  open-delay="1000"
                  max-width="250"
                  v-if="tool.descr"
                >
                  {{ tool.descr }}
                </v-tooltip>

                <span style="color: white">{{ tool.name }}</span>
              </v-sheet>
            </div>
          </v-list-group>
        </v-list>
      </v-card>
      <v-card
        color="background"
        class="pa-3 cards"
        rounded="xl"
        style="bottom: 1.8rem; position: fixed"
        variant="flat"
      >
        <v-divider class="mx-8 my-3"></v-divider>
        <v-btn :block="true" prepend-icon="mdi-help" variant="plain">
          怎么使用
          <v-tooltip
            activator="parent"
            open-delay="200"
            style="color:aquamarine;color"
            theme="light"
          >
            <v-sheet
              class="py-3"
              rounded="xl"
              theme="dark"
              color="#212121"
              style="line-height: 2rem"
            >
              <!-- <video
                :src="demoMp4"
                width="550"
                autoplay
              ></video> -->
              <v-row><v-img :src="demoGif" width="300"></v-img></v-row>
              <div class="px-3 mt-4">
                <v-row>1.从左侧工具目录内选择相应模块</v-row>
                <v-row>2.将所选择的模块进行连线</v-row>
                <v-row>3.点击相应模块来设置相关参数</v-row>
                <v-row>4.点击相应按钮进行模型训练预测</v-row>
              </div>
            </v-sheet>
          </v-tooltip>
        </v-btn>
      </v-card>
    </aside>
  </transition>

  <!-- 迷你工具栏 -->
  <transition name="expand">
    <aside v-if="mini" style="z-index: 99">
      <v-card
        color="background"
        rounded="xl"
        style="
          z-index: 99;
          overflow: auto;
          top: 4.5rem;
          bottom: 1.8rem;
          left: 1.6rem;
          position: fixed;
        "
        class="d-flex align-start flex-column align-center"
        variant="flat"
      >
        <v-btn icon variant="plain">
          <v-icon icon="mdi-menu"></v-icon>
        </v-btn>
        <v-btn icon variant="plain" @click="expandTool()">
          <v-icon icon="mdi-arrow-right-circle-outline"></v-icon>
        </v-btn>
        <v-btn icon variant="plain">
          <v-icon icon="mdi-magnify"></v-icon>
        </v-btn>

        <v-btn
          icon
          variant="plain"
          v-for="toolclass of toolbardata"
          :key="toolclass.value"
          :prepend-icon="toolclass.icon"
          @click="expandTool(toolclass.value)"
          class="ma-2"
        >
          <v-icon :icon="toolclass.icon"></v-icon>
        </v-btn>

        <v-btn icon variant="plain" class="mb-1" style="margin-top: auto">
          <v-icon>mdi-help</v-icon>
        </v-btn>
      </v-card>
    </aside>
  </transition>
</template>

<script lang="ts" setup>
import demoGif from '@/assets/howtouse_demo.gif';
import demoMp4 from '@/assets/howtouse_demo.mp4';

import { GraphNode, useVueFlow } from '@vue-flow/core';
import { ref, Ref } from 'vue';
import { toolbardata, Tool, ToolClass } from './toolbardata';
import { useProjectStore } from '@/store/project';
import { handleSaveModel } from './save-model';
const store = useProjectStore();

let mini: Ref<boolean> = ref(false);

let open: Ref<string[]> = ref([]);

const onDragStart = (event: DragEvent, toolclass: ToolClass, tool: Tool) => {
  if (event.dataTransfer) {
    event.dataTransfer.setData('category', toolclass.value);
    event.dataTransfer.setData('type', toolclass.type);
    event.dataTransfer.setData('color', toolclass.color);
    event.dataTransfer.setData('accentColor', toolclass.accentColor);
    event.dataTransfer.setData('name', tool.name);
    event.dataTransfer.setData('data', tool.hasOptions.toString());
    event.dataTransfer.setData('options', JSON.stringify(tool.options ?? {}));

    event.dataTransfer.setData('application/vueflow', toolclass.type);

    event.dataTransfer.effectAllowed = 'move';
  }
};

let search: Ref<string> = ref('');
let searchLoading: Ref<boolean> = ref(false);

const { addNodes, nodes, dimensions, toObject } = useVueFlow();

const addNode = (toolclass: ToolClass, tool: Tool) => {
  if (
    toolclass.value !== 'model' ||
    nodes.value.filter((node: GraphNode) => node.data.category === 'model')
      .length === 0
  )
    addNodes([
      {
        id: nodes.value.length.toString(),
        type: toolclass.type,
        position: {
          x: dimensions.value.width / 2,
          y: dimensions.value.height / 2,
        },
        data: {
          color: toolclass.color,
          accentColor: toolclass.accentColor,
          hasOptions: tool.hasOptions.toString() === 'true',
          options: tool.options,
          category: toolclass.value,
        },
        label: tool.name,
        style: (el: GraphNode) => {
          if (el.selected)
            return {
              '--vf-node-text': 'white',
              '--vf-node-bg': toolclass.color,
              '--vf-node-color': toolclass.color,
              'border-color': toolclass.accentColor + '!important',
            };
          return {
            '--vf-node-text': 'white',
            '--vf-node-bg': toolclass.color,
            '--vf-node-color': toolclass.color,
          };
        },
      },
    ]);

  handleSaveModel(store.modelInfo.modelId, JSON.stringify(toObject() ?? {}));
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
.cards {
  width: 16rem;
  left: 1.6rem;
}

.narrow-enter-active,
.narrow-leave-active {
  transition: all 0.5s ease;
}

.narrow-enter-from,
.narrow-leave-to {
  opacity: 0;
}

.expand-enter-active,
.expand-leave-active {
  transition: all 0.5s ease;
}

.expand-enter-from,
.expand-leave-to {
  opacity: 0;
}

.node {
  border-style: solid;
}

#v-tooltip-41 {
  --v-theme-surface-variant: 33, 33, 33;
}
</style>
