<template>
  <v-app-bar density="compact" color="background">
    <v-tabs v-model="selectedTab" class="mx-10">
      <v-tab
        v-for="tab of tabs"
        :key="tab.value"
        :value="tab.value"
        variant="plain"
        slider-color="primary"
        style="font-size: 1.1rem"
        :to="{ name: tab.to }"
      >
        {{ tab.name }}
      </v-tab>
    </v-tabs>

    <v-spacer></v-spacer>

    <v-btn-toggle v-model="sortType" density="compact" class="mx-2" mandatory>
      <v-btn rounded="false">
        升序
        <v-icon>mdi-sort-ascending</v-icon>
      </v-btn>

      <v-btn rounded="false">
        降序
        <v-icon>mdi-sort-descending</v-icon>
      </v-btn>
    </v-btn-toggle>
  </v-app-bar>

  <v-main style="background-color: rgb(var(--v-theme-background-content))">
    <v-row class="mt-5 ml-5" v-if="route.name === 'model'">
      <v-col cols="12" md="3">
        <create-project-dialog></create-project-dialog>
      </v-col>
      <v-col cols="12" md="3">
        <create-with-template-dialog></create-with-template-dialog>
      </v-col>
    </v-row>
    <v-row class="mt-5 ml-5" v-if="route.name === 'dataset'">
      <v-col cols="12" md="3"><upload-data-dialog></upload-data-dialog></v-col>
    </v-row>
    <router-view></router-view>
  </v-main>
</template>

<script lang="ts" setup>
import { Ref, ref } from 'vue';
import CreateProjectDialog from '@/components/stage/project/CreateProjectDialog.vue';
import CreateWithTemplateDialog from '@/components/stage/project/CreateWithTemplateDialog.vue';
import UploadDataDialog from '@/components/stage/project/UploadDataDialog.vue';
import { useRoute } from 'vue-router';
const route = useRoute();

interface Tab {
  name: string;
  value: number;
  to: string;
}

const selectedTab: Ref<number> = ref(0);

const tabs: Ref<Tab[]> = ref([
  {
    name: '我的模型',
    value: 0,
    to: 'model',
  },
  {
    name: '我的数据',
    value: 1,
    to: 'dataset',
  },
]);

const sortType: Ref<number> = ref(0);
</script>
