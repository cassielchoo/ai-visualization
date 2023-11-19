<template>
  <div class="text-center">
    <v-btn
      style="font-size: 1rem"
      block
      size="large"
      variant="flat"
      class="btn"
      elevation="3"
    >
      上传数据集
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
          <v-form @submit.prevent="submit" v-model="isValid">
            <p class="mb-5" style="font-size: x-large">上传数据</p>

            <v-text-field
              variant="underlined"
              :rules="nonEmptyRule"
              v-model="form.name"
            >
              <template v-slot:prepend>数据集名称</template>
            </v-text-field>

            <v-file-input
              class="my-3"
              clearable
              label="上传文件"
              variant="filled"
              accept=".csv,application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
              v-model="form.files"
              :rules="nonEmptyRule"
            >
              <template v-slot:selection="{ fileNames }">
                <template
                  v-for="(fileName, index) in fileNames"
                  :key="fileName"
                >
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
                    +{{ form.files.length - 2 }} File(s)
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
                type="submit"
                @loading="loading"
              >
                完成
              </v-btn>
            </div>
          </v-form>
        </v-sheet>
      </v-dialog>
    </v-btn>
  </div>
</template>

<script setup lang="ts">
import { createDataset } from '@/service/user-dataset';
import { useAppStore } from '@/store/app';
import { Ref, ref } from 'vue';
const store = useAppStore();

const { handleGlobalMessaging } = store;
const dialog = ref(false);
const loading = ref(false);

const nonEmptyRule = [
  (value: File[]) => {
    if (value?.length > 0) return true;
    return '不能为空';
  },
];

interface Form {
  name: string;
  files: File[];
}

const form: Ref<Form> = ref({
  name: '',
  files: [],
});

const isValid: Ref<boolean> = ref(false);

const submit = async () => {
  if (isValid.value) {
    loading.value = true;
    const base64String = await fileToBase64();
    await createDataset({
      dataName: form.value.name,
      dataURL: base64String,
    });
    loading.value = false;
  }
};

const fileToBase64 = (): Promise<string> => {
  return new Promise((resolve, reject) => {
    // 创建一个新的 FileReader 对象
    const reader = new FileReader();
    // 读取 File 对象
    reader.readAsDataURL(form.value.files[0]);
    // 加载完成后
    reader.onload = function () {
      // 将读取的数据转换为 base64 编码的字符串
      const base64String = (reader.result as string).split(',')[1];
      // 解析为 Promise 对象，并返回 base64 编码的字符串
      resolve(base64String);
    };
    reader.onerror = function () {
      handleGlobalMessaging('读取文件失败');
      reject(new Error('读取文件失败'));
    };
  });
};
</script>

<style scoped>
.btn:hover {
  background-color: #71a8f8;
  color: white;
}
</style>
