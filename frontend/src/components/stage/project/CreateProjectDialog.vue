<template>
  <div class="text-center">
    <v-btn
      style="font-size: 1rem"
      block
      size="large"
      class="btn"
      variant="flat"

      elevation="3"
    >
      创建新的项⽬
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

          <v-form @submit.prevent="submit" v-model="isValid">
            <v-text-field
              variant="underlined"
              v-model="form.name"
              :rules="nonEmptyRule"
            >
              <template v-slot:prepend>项目名称</template>
            </v-text-field>

            <v-radio-group v-model="form.type" inline>
              <v-row>
                <v-col style="text-align: left">
                  <v-radio label="作为个人" value="s"></v-radio>
                </v-col>
                <v-col style="text-align: left">
                  <v-radio label="作为团队" value="t"></v-radio>
                </v-col>
              </v-row>
            </v-radio-group>

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
import { createModel } from '@/service/user-model';
import { Ref, ref } from 'vue';

const dialog = ref(false);
const loading = ref(false);

const nonEmptyRule = [
  (value: string) => {
    if (value) return true;
    return '不能为空';
  },
];

interface Form {
  name: string;
  type: 's' | 't';
}

const form: Ref<Form> = ref({
  name: '',
  type: 's',
});

const isValid: Ref<boolean> = ref(false);

const submit = async () => {
  if (isValid.value) {
    loading.value = true;
    await createModel(form.value.name);
    loading.value = false;
  }
};
</script>

<style scoped>
.btn:hover {
  background-color: #71a8f8;
  color: white;
}
</style>
