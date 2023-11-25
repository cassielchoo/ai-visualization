<template>
  <v-row
    style="padding: 0; margin: 0; height: calc(100vh - 64px)"
    class="align-center justify-center"
    id="0"
  >
    <v-col cols="6">
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
  <v-row style="height: 70vh" class="align-center justify-center" id="1">
    <v-col cols="5">
      <v-card-title style="font-size: 2rem">
        基于拖拉拽，模型构建更加快捷
      </v-card-title>
      <v-card-text style="font-size: 1.2rem">
        用户能够轻松地将各种小组件拖放至编辑面板，通过排序和连线的方式完成组合，并配置相关参数，或者选择使用默认参数进行模型的训练与测试。这些小组件包含了数据导入、数据预处理、特征工程、模型选择、评估指标等多个方面。在模型选择方面，用户可以从众多经典机器学习和深度学习模型中进行选择。此外，我们的系统支持广泛的任务类型，包括二分类、多分类、回归、文本分类和图像分类等。我们的团队致力于通过这些核心功能为用户提供卓越的使用体验。
      </v-card-text>
    </v-col>
    <v-col cols="5">
      <v-img
        :src="demoGif_light"
        width="100%"
        style="
          border-color: rgb(var(--v-theme-primary));
          border-style: solid;
          border-radius: 8px;
        "
      ></v-img>
    </v-col>
  </v-row>
  <v-row style="height: 70vh" class="align-center justify-center" id="2">
    <v-col cols="5">
      <div
        style="
          border-color: rgb(var(--v-theme-primary));
          border-style: solid;
          height: 30rem;
          width: 100%;
          border-radius: 8px;
        "
      >
        <HeadBannerDiagram></HeadBannerDiagram>
      </div>
    </v-col>
    <v-col cols="5">
      <v-card-title style="font-size: 2rem">零代码亦可轻松上手</v-card-title>
      <v-card-text style="font-size: 1.2rem">
        系统设计新颖，即使没有任何代码编辑经验，用户仍能毫不费力地搭建经典的机器学习和深度学习模型。这意味着更多的人可以参与模型的创建，无需深入了解编程细节，使整个过程更加可访问和高效。
      </v-card-text>
    </v-col>
  </v-row>
  <v-row style="height: 70vh" class="align-center justify-center" id="3">
    <v-col cols="5">
      <v-card-title style="font-size: 2rem">支持团队协同开发</v-card-title>
      <v-card-text style="font-size: 1.2rem">
        人工智能模型的构建、测试和调整通常需要跨足多个学科领域，如数学和统计学。鉴于此，我们系统推出了团队协作功能，使得这项任务不再是个人的独自奋斗。通过该功能，用户能够创建团队、团队建立模型项目，利用系统提供的模板进行团队开发，并支持团队成员协同工作。我们希望通过这一功能，使团队更便捷、高效地完成人工智能模型的构建、测试和调整。
      </v-card-text>
    </v-col>
    <v-col cols="5">
      <v-img
        :src="cooperate"
        width="100%"
        style="
          border-color: rgb(var(--v-theme-primary));
          border-style: solid;
          border-radius: 8px;
        "
      ></v-img>
    </v-col>
  </v-row>
  <v-row style="height: 70vh" class="align-center justify-center" id="4">
    <v-col cols="5">
      <v-img
        :src="shop"
        width="100%"
        style="
          border-color: rgb(var(--v-theme-primary));
          border-style: solid;
          border-radius: 8px;
        "
      ></v-img>
    </v-col>
    <v-col cols="5">
      <v-card-title style="font-size: 2rem">模型数据开源社区</v-card-title>
      <v-card-text style="font-size: 1.2rem">
        为了促进协作与共享知识，加速模型创新，加深思维的交流，系统面向所有用户提供了模型与数据集的分享社区，用户可以在此上传自己创建的模型或数据集，也可以在此寻找自己感兴趣的模型或者数据集。
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
import demoGif_light from '@/assets/howtouse_demo_light.gif';
import shop from '@/assets/shop.png';
import cooperate from '@/assets/cooperate.png';
import { ref } from 'vue';

const links = ['首页', '关于我们', '团队', '服务', '联系我们'];
</script>

<style></style>
