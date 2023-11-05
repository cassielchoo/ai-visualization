<template>
  <v-app>
    <v-app-bar color="background" elevation="0">
      <template v-slot:prepend>
        <v-img :src="isDark ? darkLogo : lightLogo" width="130" cover></v-img>
      </template>
    </v-app-bar>
    <default-view />
  </v-app>
</template>

<script setup lang="ts">
import DefaultView from './View.vue';
import lightLogo from '@/assets/light_logo.png';
import darkLogo from '@/assets/dark_logo.png';
import { ComputedRef, computed } from 'vue';
import { useTheme } from 'vuetify/lib/framework.mjs';

const theme = useTheme();
let isDark: ComputedRef<boolean> = computed(
  () => theme.global.current.value.dark,
);
</script>
