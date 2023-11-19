<template>
  <v-navigation-drawer
    floating
    v-model="drawer"
    width="240"
    elevation="2"
    color="background"
  >
    <template v-slot:prepend>
      <v-img
        :src="store.isDark ? darkLogo : lightLogo"
        style="display: block; margin: 1.25rem auto; cursor: pointer"
        width="200"
        @click="router.push({ name: 'home' })"
      ></v-img>
    </template>

    <v-list nav class="my-8">
      <div v-for="(item, i) in items" :key="i">
        <v-divider v-if="i === 2" class="mx-10 my-5"></v-divider>
        <v-list-item
          class="pl-8 ma-3"
          :value="item"
          color="primary"
          :prepend-icon="item.icon"
          variant="flat"
          base-color="background"
        >
          <v-list-item-title style="font-size: 1.2rem" class="py-3">
            {{ item.title }}
          </v-list-item-title>
        </v-list-item>
      </div>
    </v-list>
    <template v-slot:append>
      <div class="pa-5">
        <v-divider class="mx-6 my-3"></v-divider>
        <v-btn
          block
          variant="plain"
          prepend-icon="mdi-exit-to-app"
          style="font-size: 1rem"
          class="py-3"
          @click="logout"
        >
          退出登录
        </v-btn>
      </div>
    </template>
  </v-navigation-drawer>
  <v-app-bar  elevation="0" height="80" density="compact" :color="store.isDark?'#181818':'white'">
    <v-app-bar-nav-icon
      v-if="showDrawerBtn"
      @click.stop="drawer = !drawer"
    ></v-app-bar-nav-icon>

    <v-text-field
      :loading="searchLoading"
      v-model="search"
      rounded
      placeholder="搜索项目"
      clearable
      prepend-inner-icon="mdi-magnify"
      flat
      hide-details
      class="ml-10"
      density="compact"
      variant="solo-filled"
      :bg-color="store.isDark?'#232323':'#f4f4f4'"
    ></v-text-field>
    <v-spacer></v-spacer>

    <v-btn
      variant="text"
      :icon="store.isDark ? 'mdi-weather-night' : 'mdi-white-balance-sunny'"
      @click="store.toggleTheme"
    ></v-btn>

    <v-badge :content="notifCount" color="error" v-model="showCount">
      <v-btn variant="text" icon="mdi-bell-outline"></v-btn>
    </v-badge>

    <v-list-item
      variant="flat"
      @click="1"
      :prepend-avatar="store.user.userPhoto"
      class="mx-3"
    >
      {{ store.user.userName }}
    </v-list-item>
  </v-app-bar>
</template>

<script lang="ts" setup>
import { Ref, computed, ref, ComputedRef, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { useDisplay, useTheme } from 'vuetify';
import { useAppStore } from '@/store/app';

import { getUserInfo } from '@/service/users';

import lightLogo from '@/assets/light_logo_blue.png';
import darkLogo from '@/assets/dark_logo.png';

const store = useAppStore();
const router = useRouter();

const { mobile } = useDisplay();
const theme = useTheme();

onMounted(async () => {
  const Router = useRouter().options.routes[0].children;
  for (const route of Router!) {
    items.value.push({
      title: typeof route.meta?.title === 'string' ? route.meta?.title : '',
      path: route.path,
      icon: typeof route.meta?.icon === 'string' ? route.meta?.icon : '',
    });
  }

  const res = await getUserInfo();
  store.user = res.data!;
});

interface Item {
  title: string;
  path: string;
  icon: string;
}

const drawer: Ref<boolean> = ref(true);
const items: Ref<Item[]> = ref([]);
const showDrawerBtn: ComputedRef<boolean> = computed(() => {
  return mobile.value;
});

const search: Ref<string> = ref('');
const searchLoading: Ref<boolean> = ref(false);


const showCount: ComputedRef<boolean> = computed(() => notifCount.value > 0);
const notifCount: Ref<number> = ref(0);

const logout = () => {
  store.deleteLoginInfo();
  router.push({ name: 'home' });
};
</script>

<style scoped>

</style>
