<template>
  <v-row style="margin: 0" class="pa-4">
    <v-col
      style="text-align: center; margin: auto 0"
      v-for="(item, i) in items"
      :key="item.title"
      :class="i !== items.length - 1 ? 'item' : ''"
      class="pa-6"
    >
      <p style="font-size: 1.5rem"><v-icon class="mb-2 mr-2" style="font-size: 1.5rem;">{{ item.icon }}</v-icon>{{ item.title }}</p>
      <p style="color: #9ca3af">{{ item.subtitle }}</p>
    </v-col>
  </v-row>
</template>

<script setup lang="ts">
const items = [
  {
    title: '零门槛',
    subtitle: '零代码亦可轻松上手',
      icon:'mdi-emoticon-cool'
  },
  {
    title: '拖拉拽',
    subtitle: '模型构建更加快捷',
    icon:'mdi-gesture-tap'
  },
  {
    title: '高效',
    subtitle: '支持团队协同开发',
      icon:'mdi-run-fast'
  },
  {
    title: '开放',
    subtitle: '模型数据开源社区',
      icon:'mdi-handshake-outline'
  },
];
</script>

<style scoped>
.item {
  border-right-style: solid;
  border-width: 1px;
}
</style>
