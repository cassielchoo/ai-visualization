<template>
  <v-app-bar color="background" elevation="0">
    <template v-slot:prepend>
      <v-img :src="isDark ? darkLogo : lightLogo" width="130" cover></v-img>
    </template>
    <template v-slot:append>
      <v-tabs v-model="tab" color="primary">
        <v-tab
          v-for="menu of menus"
          :key="menu.title"
          :value="menu.id"
          @click="toDetail(menu.id)"
        >
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
import { onDeactivated } from 'vue';

const store = useAppStore();

const theme = useTheme();
const isDark: ComputedRef<boolean> = computed(
  () => theme.global.current.value.dark,
);

const tab = ref('0');

const dialog = ref(false);
const dialogType: Ref<'login' | 'register'> = ref('login');
function toggleTheme() {
  theme.global.name.value = theme.global.current.value.dark ? 'light' : 'dark';
}

const toDetail = (id: string) => {
  const scrollDom = document.getElementById(id);
  if (scrollDom) {
    scrollDom.scrollIntoView({
      behavior: 'smooth', // 平滑过渡
      block: 'center', // 上边框与视窗顶部平齐。默认值
    });
  }
};

function updateCurrentSection() {
  // 获取当前滚动位置
  const scrollPosition = window.scrollY;

  // 根据滚动位置确定当前锚点
  for (const menu of menus) {
    const element = document.getElementById(menu.id);
    if (
      element &&
      element.offsetTop - element.offsetHeight / 2 <= scrollPosition &&
      element.offsetTop + element.offsetHeight / 2 > scrollPosition
    ) {
      tab.value = menu.id;
      break;
    }
  }
}

onMounted(async () => {
  const res = await getUserInfo();
  store.user = res.data!;
  // 监听滚动事件，更新当前锚点
  window.addEventListener('scroll', updateCurrentSection);

  // 在组件加载后初始化当前锚点
  updateCurrentSection();
});

const menus = [
  {
    title: '首页',
    icon: 'mdi-home',
    id: '0',
  },
  {
    title: '拖拉拽',
    icon: 'mdi-gesture-tap',
    id: '1',
  },
  {
    title: '零代码',
    icon: 'mdi-emoticon-cool',
    id: '2',
  },
  {
    title: '协同',
    icon: 'mdi-run-fast',
    id: '3',
  },
  {
    title: '开放',
    icon: 'mdi-handshake-outline',
    id: '4',
  },
];

onDeactivated(() => {
  window.removeEventListener('scroll', updateCurrentSection);
});
</script>
