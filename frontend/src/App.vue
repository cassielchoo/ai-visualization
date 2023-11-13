<template>
  <router-view></router-view>
  <v-snackbar v-model="store.showSnackBar" timeout="2000" location="top">
    {{ store.message }}

    <template v-slot:actions>
      <v-btn color="primary" variant="text" @click="store.showSnackBar = false">
        关闭
      </v-btn>
    </template>
  </v-snackbar>
</template>

<script setup lang="ts">
import { useAppStore } from '@/store/app';

const store = useAppStore();


</script>

<style>
body {
  font-family: HarmonySANS;
  -webkit-user-select: none;
  -moz-user-select: none;
  -o-user-select: none;
  user-select: none;
}

::-webkit-scrollbar {
  width: 5px;
  height: 5px;
}
::-webkit-scrollbar-thumb {
  border-radius: 5px;
  -webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.1);
  background-color: rgba(0, 0, 0, 0.1);
}
::-webkit-scrollbar-track {
  border-radius: 5px;
  -webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.1);
  background-color: rgba(0, 0, 0, 0.1);
}
</style>
