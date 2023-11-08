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
        <v-btn @click="dialog = true">登录</v-btn>

        <v-dialog v-model="dialog" width="348" persistent>
          <login-box @to-register="dialogType='register'" v-if="dialogType==='login'" @close="dialog=false"></login-box>
          <register-box @to-login="dialogType='login'" v-if="dialogType==='register'" @close="dialog=false"></register-box>
        </v-dialog>
      </template>
    </v-app-bar>
    <default-view />
  </v-app>
</template>

<script setup lang="ts">
import DefaultView from './View.vue';
import lightLogo from '@/assets/light_logo.png';
import darkLogo from '@/assets/dark_logo.png';
import LoginBox from '@/components/LoginBox.vue';
import RegisterBox from '@/components/RegisterBox.vue';
import { ComputedRef, Ref, computed, ref } from 'vue';
import { useTheme } from 'vuetify/lib/framework.mjs';

const theme = useTheme();
const isDark: ComputedRef<boolean> = computed(
  () => theme.global.current.value.dark,
);

const dialog = ref(false);
const dialogType:Ref<'login' | 'register'>=ref('login')
function toggleTheme() {
  theme.global.name.value = theme.global.current.value.dark ? 'light' : 'dark';
}
</script>
