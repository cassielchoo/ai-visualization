<template>
  <v-main style="background-color: rgb(var(--v-theme-background-content))">
    <v-sheet class="mx-4 mt-12" elevation="2" rounded="lg">
      <v-row class="pa-4" v-for="(group, i) of groups" :key="group.name">
        <v-col>
          <v-avatar color="grey-darken-3" :image="group.avatar"></v-avatar><span class="mx-4">{{ group.name }}</span>
        </v-col>
        <v-col class="justify-end d-flex">
          <v-btn variant="text" color="error">退出团队</v-btn>
        </v-col>
        <v-divider v-if="i !== groups.length - 1"></v-divider>
      </v-row>
    </v-sheet>
  </v-main>
</template>

<script setup lang="ts">
const groups = [
  {
    name: '冷面小青龙',
    avatar:
      'https://gamepress.gg/grandorder/sites/grandorder/files/fgo-icon.jpg',
  },
  {
    name: '银河球棒侠',
    avatar:
      'https://fgo.kazemai.io/common/images/icon/faces/2050000.png',
  },
  {
    name: '开拓者小队',
    avatar:
      'https://fgo.kazemai.io/common/images/Servants/Status/204800/status_servant_1.png',
  },
  {
    name: '星穹列车组',
    avatar:
      'https://fgo.kazemai.io/common/images/Servants/Status/604700/status_servant_11.png',
  },
];
</script>

<style scoped></style>
