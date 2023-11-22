<template>
  <div style="display: flex; flex-flow: column; height: 100%">
    <v-row style="padding: 0; margin: 0">
      <v-col cols="6">
        <div class="d-flex justify-center align-center" style="height: 100%;">
          <div style="">
            <v-img :src="logo" width="400"></v-img>
            <v-card-title style="font-size: 2.7rem">
              基于拖拉拽建模的可视化AI模型开发平台
            </v-card-title>
            <v-card-text style="color: rgb(112, 112, 112);font-size: 1.5rem;">
              <p>帮助有数据挖掘基础但不擅长写代码的用户或资深从业人员</p>
              <p>通过图形化的拖拉拽式建模方式，直接参与构建数据模型</p>
              <p>让模型的构建、优化、落地应用等各个方面更加高效。</p>
            </v-card-text>
          </div>
        </div>
      </v-col>
      <v-col cols="6">
        <div class="d-flex justify-center align-center" style="height: 100%;">
          <div>
            <v-img :src="home1" width="750" class="mt-12 ml-12"></v-img>
          </div>
        </div>
      </v-col>
    </v-row>
  </div>
  
  <div style="height: 500px;">
    <HeadBannerDiagram></HeadBannerDiagram>
  </div>
  
</template>

<script lang="ts" setup>
import home1 from '@/assets/home1.svg';
import logo from '@/assets/light_logo_blue.png';
import HeadBannerDiagram from '@/components/home/HeadBannerDiagram.vue';
</script>

<style></style>
