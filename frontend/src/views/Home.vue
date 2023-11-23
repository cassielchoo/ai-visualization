<template>
  <v-row
    style="padding: 0; margin: 0; height: calc(100vh - 64px)"
    class="align-center justify-center"
  >
    <v-col cols="5">
      <v-img :src="logo" width="400"></v-img>
      <v-card-title style="font-size: 2.7rem">
        基于拖拉拽建模的可视化AI模型开发平台
      </v-card-title>
      <v-card-text style="color: rgb(112, 112, 112); font-size: 1.5rem">
        <p>帮助有数据挖掘基础但不擅长写代码的用户或资深从业人员</p>
        <p>通过图形化的拖拉拽式建模方式，直接参与构建数据模型</p>
        <p>让模型的构建、优化、落地应用等各个方面更加高效。</p>
      </v-card-text>
    </v-col>
    <v-col cols="5">
      <div class="d-flex justify-center align-center" style="height: 100%">
        <div>
          <v-img :src="home1" width="750" class="mt-12 ml-12"></v-img>
        </div>
      </div>
    </v-col>
  </v-row>
  <v-row style="height: calc(100vh - 64px)" class="align-center justify-center">
    <v-col cols="5">
      <v-card-title style="font-size: 2rem">
        基于拖拉拽，模型构建更加快捷
      </v-card-title>
      <v-card-text style="font-size: 1.2rem">
        Vue Flow comes with built-in features like zoom & pan and dedicated
        controls, single & multi-selections, draggable elements, customizable
        nodes and edges and a bunch of event handlers.
      </v-card-text>
    </v-col>
    <v-col cols="5">
      <div
        style="
          border-style: solid;
          height: 25rem;
          width: 100%;
          border-radius: 8px;
        "
      ></div>
    </v-col>
  </v-row>
  <v-row style="height: calc(100vh - 64px)" class="align-center justify-center">
    <v-col cols="5">
      <div
        style="
          border-style: solid;
          height: 25rem;
          width: 100%;
          border-radius: 8px;
        "
      ></div>
    </v-col>
    <v-col cols="5">
      <v-card-title style="font-size: 2rem">零代码亦可轻松上手</v-card-title>
      <v-card-text style="font-size: 1.2rem">
        Vue Flow comes with built-in features like zoom & pan and dedicated
        controls, single & multi-selections, draggable elements, customizable
        nodes and edges and a bunch of event handlers.
      </v-card-text>
    </v-col>
  </v-row>
  <v-row style="height: calc(100vh - 64px)" class="align-center justify-center">
    <v-col cols="5">
      <v-card-title style="font-size: 2rem">支持团队协同开发</v-card-title>
      <v-card-text style="font-size: 1.2rem">
        Vue Flow comes with built-in features like zoom & pan and dedicated
        controls, single & multi-selections, draggable elements, customizable
        nodes and edges and a bunch of event handlers.
      </v-card-text>
    </v-col>
    <v-col cols="5">
      <div
        style="
          border-style: solid;
          height: 25rem;
          width: 100%;
          border-radius: 8px;
        "
      ></div>
    </v-col>
  </v-row>
  <v-row style="height: calc(100vh - 64px)" class="align-center justify-center">
    <v-col cols="5">
      <div
        style="
          border-style: solid;
          height: 25rem;
          width: 100%;
          border-radius: 8px;
        "
      ></div>
    </v-col>
    <v-col cols="5">
      <v-card-title style="font-size: 2rem">模型数据开源社区</v-card-title>
      <v-card-text style="font-size: 1.2rem">
        Vue Flow comes with built-in features like zoom & pan and dedicated
        controls, single & multi-selections, draggable elements, customizable
        nodes and edges and a bunch of event handlers.
      </v-card-text>
    </v-col>
  </v-row>
  <v-footer>
    <v-row class="justify-center">
      <v-col cols="12" class="text-center">
        {{ new Date().getFullYear() }} —
        <strong>EASYAI</strong>
      </v-col>
    </v-row>
  </v-footer>
</template>

<script lang="ts" setup>
import home1 from '@/assets/home1.svg';
import logo from '@/assets/light_logo_blue.png';
import HeadBannerDiagram from '@/components/home/HeadBannerDiagram.vue';

const links = ['首页', '关于我们', '团队', '服务', '联系我们'];
</script>

<style></style>
