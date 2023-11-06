<template>
  <v-app>
    <v-app-bar color="background" elevation="0">
      <template v-slot:prepend>
        <v-img :src="isDark ? darkLogo : lightLogo" width="130" cover></v-img>
      </template>
      <template v-slot:append>
        <v-btn
          variant="text"
          :icon="isDark ? 'mdi-weather-night' : 'mdi-white-balance-sunny'"
          @click="toggleTheme"
        ></v-btn>
        <v-btn>登录</v-btn>
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

function toggleTheme() {
  theme.global.name.value = theme.global.current.value.dark ? 'light' : 'dark';
}
</script>
