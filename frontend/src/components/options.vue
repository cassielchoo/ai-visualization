<template>
  <aside style="z-index: 99">
    <v-card
      :color="toolbarColor"
      rounded="xl"
      style="
        overflow: auto;
        top: 4.5rem;
        bottom: 50%;
        right: 1.6rem;
        width: 16rem;
        position: fixed;
      "
      variant="flat"
    >
    <h4 class="ma-3">从csv导入</h4>
    <v-file-input class="ma-3" density="compact" clearable label="上传文件" variant="underlined"></v-file-input>
  </v-card>
  </aside>
</template>

<script lang="ts" setup>
import { ComputedRef, computed } from 'vue';
import { useTheme } from 'vuetify';

const theme = useTheme();

let toolbarColor: ComputedRef<string> = computed(() =>
  theme.global.current.value.dark ? '121212' : '#f1f3f4',
);
</script>

<style></style>
