<template>
  <v-app>
    <v-navigation-drawer floating v-model="drawer">
      <v-img
        :src="isDark ? darkLogo : logo"
        style="display: block; margin: 20px auto"
        width="200"
      ></v-img>

      <v-list nav>
        <v-list-item
          rounded="xl"
          class="pl-10 ma-3"
          v-for="(item, i) in items"
          :key="i"
          :value="item"
          color="primary"
          :prepend-icon="item.icon"
          variant="plain"
        >
          <v-list-item-title class="text-subtitle-1">{{
            item.title
          }}</v-list-item-title>
        </v-list-item>
      </v-list>
      <template v-slot:append>
        <div class="pa-5">
          <v-btn
            block
            variant="plain"
            prepend-icon="mdi-exit-to-app"
            class="text-error"
          >
            退出登录
          </v-btn>
        </div>
      </template>
    </v-navigation-drawer>
    <v-app-bar
      :color="isDark ? '#1A1A1A' : '#f9f9f9'"
      elevation="0"
      height="100"
    >
      <v-app-bar-nav-icon
        v-if="showDrawerBtn"
        @click.stop="drawer = !drawer"
      ></v-app-bar-nav-icon>

      <v-text-field
        :loading="loading"
        v-model="search"
        rounded
        placeholder="搜索项目"
        clearable
        prepend-icon="mdi-magnify"
        flat
        class="mt-6 ml-10"
        variant="solo"
      ></v-text-field>
      <v-spacer></v-spacer>

      <v-btn
        variant="text"
        :icon="isDark ? 'mdi-weather-night' : 'mdi-white-balance-sunny'"
        @click="toggleTheme"
      ></v-btn>

      <v-badge :content="notifCount" color="error">
      <v-btn variant="text" icon="mdi-bell-outline"></v-btn>
      </v-badge>

      <v-list-item
        @click="1"
        prepend-avatar="https://cdn.vuetifyjs.com/images/john.jpg"
        rounded="xl"
        class="mx-3"
      >
        {{ user.fullName }}
      </v-list-item>
    </v-app-bar>

    <default-view />
  </v-app>
</template>

<script lang="ts" setup>
import DefaultView from "./View.vue";
import { Ref, computed, ref, ComputedRef, onMounted } from "vue";
import { useRouter } from "vue-router";
import { useDisplay, useTheme } from "vuetify";

import logo from "@/assets/logo.png";
import darkLogo from "@/assets/dark-logo.png";

const { mobile } = useDisplay();
const theme = useTheme();

onMounted(() => {
  const Router = useRouter().options.routes[0].children;
  for (const route of Router!) {
    items.value.push({
      title: typeof route.meta?.title === "string" ? route.meta?.title : "",
      path: route.path,
      icon: typeof route.meta?.icon === "string" ? route.meta?.icon : "",
    });
  }
});

function toggleTheme() {
  theme.global.name.value = theme.global.current.value.dark ? "light" : "dark";
}

interface Item {
  title: string;
  path: string;
  icon: string;
}

let drawer: Ref<boolean> = ref(true);
let items: Ref<Item[]> = ref([]);
let showDrawerBtn: ComputedRef<boolean> = computed(() => {
  return mobile.value;
});

let search: Ref<string> = ref("");
let loading: Ref<boolean> = ref(false);

let user = {
  initials: "JD",
  fullName: "John Doe",
  email: "john.doe@doe.com",
};

let isDark: ComputedRef<boolean> = computed(
  () => theme.global.current.value.dark
);

let notifCount:Ref<number>=ref(99)
</script>
