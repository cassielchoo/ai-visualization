<template>
  <v-app>
    <v-app-bar
      :color="isDark ? '#1A1A1A' : '#f9f9f9'"
      :title="projectName"
      elevation="0"
      height="60"
    >
      <template v-slot:prepend>
        <v-app-bar-nav-icon
          icon="mdi-arrow-left"
          @click="goBack"
        ></v-app-bar-nav-icon>
      </template>

      <v-spacer></v-spacer>

      <v-btn
        :icon="isDark ? 'mdi-weather-night' : 'mdi-white-balance-sunny'"
        variant="text"
        @click="toggleTheme"
      ></v-btn>

      <v-list-item
        :prepend-avatar="user.avatar"
        class="mx-3"
        variant="flat"
        @click="1"
      >
        {{ user.name }}
      </v-list-item>
    </v-app-bar>

    <default-view />
  </v-app>
</template>

<script lang="ts" setup>
import DefaultView from './View.vue';
import { computed, ComputedRef, ref, Ref } from 'vue';
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
