<template>
  <div class="text-center">
    <v-btn style="font-size: 1rem" variant="flat" block size="large">
      上传数据
      <template v-slot:append>
        <v-icon>mdi-plus-circle-outline</v-icon>
      </template>

      <v-dialog v-model="dialog" activator="parent" width="400">
        <v-sheet
          elevation="12"
          max-width="600"
          rounded="lg"
          width="100%"
          class="pa-6"
        >
          <p class="mb-5" style="font-size: x-large">创建项目</p>

          <v-file-input
            class="mb-5"
            clearable
            label="上传文件"
            variant="filled"
            hide-details
            accept=".csv,application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
            v-model="files"
          >
            <template v-slot:selection="{ fileNames }">
              <template v-for="(fileName, index) in fileNames" :key="fileName">
                <v-chip
                  v-if="index < 2"
                  color="deep-purple-accent-4"
                  label
                  size="small"
                  class="me-2"
                >
                  {{ fileName }}
                </v-chip>

                <span
                  v-else-if="index === 2"
                  class="text-overline text-grey-darken-3 mx-2"
                >
                  +{{ files.length - 2 }} File(s)
                </span>
              </template>
            </template>
            <template v-slot:append>
              <v-btn block variant="tonal" color="primary">预览</v-btn>
            </template>
          </v-file-input>

          <v-divider class="mb-4"></v-divider>

          <div class="text-end">
            <v-btn
              class="text-none"
              color="primary"
              rounded
              variant="flat"
              width="90"
              @loading="loading"
            >
              完成
            </v-btn>
          </div>
        </v-sheet>
      </v-dialog>
    </v-btn>
  </div>
</template>

<script setup lang="ts">
import { Ref, ref } from 'vue';

const dialog = ref(false);
const loading = ref(false);

interface Form {
  name: string;
  type: 's' | 't';
}

const files: Ref<File[]> = ref([]);
</script>

<style scoped></style>
