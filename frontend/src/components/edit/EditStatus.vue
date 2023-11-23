<template>
  <v-card
    rounded
    style="position: fixed; right: 1.6rem; bottom: 5rem"
    elevation="2"
  >
    <v-card-title style="color: gray; font-size: 1rem;"><span style="color: rgb(0, 0, 0); font-size: 1rem;">哆嗦熊</span> 正在编辑</v-card-title>
    <v-card-text>
      <v-avatar v-for="(avatar, i) of avatars" :key="i" class="mx-1" style="box-sizing:content-box;border-width: 4px;" :style="{borderStyle:avatar.editing? 'solid':'none',borderColor:avatar.editing?'rgb(var(--v-theme-success))':'none'}">
        <v-img :src="avatar.avatar" :alt="avatar.name"></v-img>
      </v-avatar>
    </v-card-text>
  </v-card>
</template>

<script setup lang="ts">
const avatars = [
  {
    name: 'zhuyu',
    avatar: 'https://webstatic.mihoyo.com/upload/op-public/2023/09/19/e54bca1450e95b35572ceee21dfe5615_1514116217685999711.png',
    editing: false,
  },
  {
    name: '哆嗦熊',
    avatar: 'https://fgo.kazemai.io/common/images/Servants/Status/505300/status_servant_3.png',
    editing: true,
  },
  {
    name: 'c',
    avatar: 'https://webstatic.mihoyo.com/upload/op-public/2023/01/13/27c6336e12ad5704d54057837d30604f_3876077510878698727.png',
    editing: false,
  },
  {
    name: 'd',
    avatar: 'https://webstatic.mihoyo.com/upload/op-public/2022/09/14/63201dab804cdd80c6c259e5f3df034d_1015235942753546049.png',
    editing: false,
  },
];
</script>

<style scoped></style>
