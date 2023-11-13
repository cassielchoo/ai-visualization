<template>
  <v-app>
    <v-app-bar color="background" elevation="0" height="45">
      <template v-slot:prepend>
        <v-app-bar-nav-icon
          class="mr-1"
          density="comfortable"
          icon="mdi-arrow-left"
          @click="goBack"
        ></v-app-bar-nav-icon>
        <span style="font-size: 20px">{{ projStore.modelInfo.modelName }}</span>
      </template>

      <app-bar-menus />

      <v-spacer></v-spacer>
      <v-btn
        variant="text"
        @click="1"
        color="success"
        density="comfortable"
        class="mx-1"
      >
        <v-icon>mdi-fast-forward</v-icon>
        训练
      </v-btn>
      <v-btn variant="text" @click="1" class="mx-1" density="comfortable">
        <v-icon>mdi-graph</v-icon>
        预测
      </v-btn>
      <v-spacer></v-spacer>
      <template v-slot:append>
        <v-btn
          :color="cloudStatus.color"
          density="comfortable"
          variant="text"
          @click="1"
          width="120"
        >
          <v-icon>{{ cloudStatus.icon }}</v-icon>
          <span v-if="showStatusText" class="mx-1">
            {{ projStore.cloudStatusText }}
          </span>
        </v-btn>
        <v-btn
          :icon="isDark ? 'mdi-weather-night' : 'mdi-white-balance-sunny'"
          variant="text"
          @click="toggleTheme"
          density="comfortable"
        ></v-btn>

        <v-list-item
          density="compact"
          :prepend-avatar="appStore.user.userPhoto"
          class="mx-3"
          variant="flat"
          @click="1"
        >
          {{ appStore.user.userName }}
        </v-list-item>
      </template>
    </v-app-bar>

    <default-view />
  </v-app>
</template>

<script lang="ts" setup>
import { getUserInfo } from '@/service/users';
import DefaultView from './View.vue';
import AppBarMenus from '@/components/edit/AppBarMenus.vue';
import { computed, ComputedRef, onMounted, ref, Ref } from 'vue';
import { useRouter } from 'vue-router';
import { useTheme } from 'vuetify';
import { useProjectStore } from '@/store/project';
import { useAppStore } from '@/store/app';

const theme = useTheme();
const router = useRouter();
const projStore = useProjectStore();
const appStore = useAppStore();


const showStatusText: Ref<boolean> = ref(true);

const cloudStatus = computed(() => {
  if (projStore.cloudStatusText === '已保存')
    return {
      icon: 'mdi-cloud-check-outline',
      color: 'success',
    };
  if (projStore.cloudStatusText === '正在保存') {
    return {
      icon: 'mdi-cloud-sync-outline',
      color: 'warning',
    };
  }
  return {
    icon: 'mdi-cloud-off-outline',
    color: 'error',
  };
});

const goBack = () => {
  router.push({
    name: 'project',
  });
};

const isDark: ComputedRef<boolean> = computed(
  () => theme.global.current.value.dark,
);

function toggleTheme() {
  theme.global.name.value = theme.global.current.value.dark ? 'light' : 'dark';
}

onMounted(async () => {
  const res = await getUserInfo();
  appStore.user = res.data!;
});
</script>
