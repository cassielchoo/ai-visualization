<template>
  <v-main :style="theme.global.current.value.dark?'background-color: #1A1A1A' : 'background-color: #f9f9f9'">
    <router-view />
  </v-main>
</template>

<script lang="ts" setup>
import { useTheme } from "vuetify";
const theme = useTheme();
</script>
