<template>
  <v-app>
    <v-app-bar
      :color="isDark ? '#1A1A1A' : '#f9f9f9'"
      elevation="0"
      height="60"
      :title="projectName"
    >
      <template v-slot:prepend>
        <v-app-bar-nav-icon
          @click="goBack"
          icon="mdi-arrow-left"
        ></v-app-bar-nav-icon>
      </template>

      <v-spacer></v-spacer>

      <v-btn
        variant="text"
        :icon="isDark ? 'mdi-weather-night' : 'mdi-white-balance-sunny'"
        @click="toggleTheme"
      ></v-btn>

      <v-list-item
        variant="flat"
        @click="1"
        :prepend-avatar="user.avatar"
        class="mx-3"
      >
        {{ user.name }}
      </v-list-item>
    </v-app-bar>

    <div v-if="showToolBar">
    <v-card
    rounded="xl"
    variant="flat"
    :color="toolbarColor"
    style="
      width: 16rem;
      overflow: auto;
      top: 4.5rem;
      bottom: 1.8rem;
      left: 1.6rem;
      position: absolute;
    "
  >
    <v-card-title class="d-flex justify-space-between">
      <span class="mt-1">
        <v-btn icon variant="plain">
          <v-icon class="mb-1 mx-1" icon="mdi-menu"></v-icon>
        </v-btn>
        工具目录
      </span>
      <v-btn icon variant="plain" @click="showToolBar=false">
        <v-icon class="mt-1" icon="mdi-arrow-left-circle-outline"></v-icon>
      </v-btn>
    </v-card-title>
    <v-card-subtitle>
      <v-text-field
        :loading="searchLoading"
        v-model="search"
        rounded
        placeholder="搜索项目"
        clearable
        prepend-inner-icon="mdi-magnify"
        flat
        variant="solo-filled"
        density="compact"
      ></v-text-field>
    </v-card-subtitle>
  </v-card>
  <v-card
    :color="toolbarColor"
    rounded="xl"
    variant="flat"
    style="
      width: 16rem;
      overflow: auto;
      top: 11.5rem;
      bottom: 1.8rem;
      left: 1.6rem;
      position: absolute;
    "
  >
    <v-list v-model:opened="open" :bg-color="toolbarColor">
      <v-list-group
        v-for="tool of toolbar"
        :key="tool.value"
        :value="tool.value"
      >
        <template v-slot:activator="{ props }">
          <v-list-item
            v-bind="props"
            :prepend-icon="tool.icon"
            :title="tool.title"
            class="ma-2"
            color="primary"
            variant="plain"
          ></v-list-item>
        </template>

        <v-list-item
          v-for="(child, i) in tool.children"
          :key="i"
          :title="child"
          :value="child"
          color="primary"
          variant="plain"
        ></v-list-item>
      </v-list-group>
    </v-list>
  </v-card>
  <v-card
    :color="toolbarColor"
    rounded="xl"
    variant="flat"
    style="
      width: 16rem;
      top: 49rem;
      bottom: 1.8rem;
      left: 1.6rem;
      position: absolute;
    "
  >
    <div class="pa-5">
      <v-divider class="mx-6 my-3"></v-divider>
      <v-btn block variant="plain" prepend-icon="mdi-help">怎么使用</v-btn>
    </div>
  </v-card>
  </div>

    <default-view />
  </v-app>
</template>

<script lang="ts" setup>
import DefaultView from './View.vue';
import { Ref, computed, ref, ComputedRef } from 'vue';
import { useRouter } from 'vue-router';
import { useTheme } from 'vuetify';

const theme = useTheme();
const router = useRouter();

let projectName: Ref<string> = ref('项目1');

let goBack = () => {
  router.push({
    name: 'project',
  });
};

interface User {
  name: string;
  avatar: string;
}
let user: Ref<User> = ref({
  name: 'John Doe',
  avatar: 'https://cdn.vuetifyjs.com/images/john.jpg',
});

let isDark: ComputedRef<boolean> = computed(
  () => theme.global.current.value.dark,
);

function toggleTheme() {
  theme.global.name.value = theme.global.current.value.dark ? 'light' : 'dark';
}

</script>
