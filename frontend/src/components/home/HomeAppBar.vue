<template>
  <v-app-bar color="background" elevation="0">
    <template v-slot:prepend>
      <v-img :src="isDark ? darkLogo : lightLogo" width="130" cover></v-img>
    </template>
    <template v-slot:append>
      <v-tabs v-model="tab" color="primary">
        <v-tab v-for="menu of menus" :key="menu.title" :value="menu.title">
          <v-icon>{{ menu.icon }}</v-icon>
          <span style="font-size: 1rem">{{ menu.title }}</span>
        </v-tab>
      </v-tabs>

      <v-btn
        variant="text"
        :icon="isDark ? 'mdi-weather-night' : 'mdi-white-balance-sunny'"
        @click="toggleTheme"
      ></v-btn>
      <v-btn @click="dialog = true" v-if="!store.token">登录</v-btn>
      <v-btn :to="{ name: 'project' }" v-else>进入控制台</v-btn>

      <v-dialog v-model="dialog" width="348" persistent>
        <login-box
          @to-register="dialogType = 'register'"
          v-if="dialogType === 'login'"
          @close="dialog = false"
        ></login-box>
        <register-box
          @to-login="dialogType = 'login'"
          v-if="dialogType === 'register'"
          @close="dialog = false"
        ></register-box>
      </v-dialog>
    </template>
  </v-app-bar>
</template>

<script setup lang="ts">
import lightLogo from '@/assets/light_logo_blue.png';
import darkLogo from '@/assets/dark_logo.png';
import LoginBox from '@/components/home/LoginBox.vue';
import RegisterBox from '@/components/home/RegisterBox.vue';
import { ComputedRef, Ref, computed, onMounted, ref } from 'vue';
import { useTheme } from 'vuetify/lib/framework.mjs';

import { useAppStore } from '@/store/app';
import { getUserInfo } from '@/service/users';

const store = useAppStore();

const theme = useTheme();
const isDark: ComputedRef<boolean> = computed(
  () => theme.global.current.value.dark,
);

const tab = ref('首页');

const dialog = ref(false);
const dialogType: Ref<'login' | 'register'> = ref('login');
function toggleTheme() {
  theme.global.name.value = theme.global.current.value.dark ? 'light' : 'dark';
}

const menus = [
  {
    title: '首页',
    icon: 'mdi-home',
  },
  {
    title: '拖拉拽',
    icon: 'mdi-gesture-tap',
  },
  {
    title: '零代码',
    icon: 'mdi-emoticon-cool',
  },
  {
    title: '协同',
    icon: 'mdi-run-fast',
  },
  {
    title: '开放',
    icon: 'mdi-handshake-outline',
  },
];

onMounted(async () => {
  const res = await getUserInfo();
  store.user = res.data!;
});
</script>
