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
      使用模板创建新的项⽬
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
            <p class="mb-5" style="font-size: x-large">使用模板创建新的项⽬</p>

            <v-text-field variant="underlined" :rules="nonEmptyRule" v-model="form.name">
              <template v-slot:prepend>项目名称</template>
            </v-text-field>

            <v-select
              label="选择模板"
              :items="[
                'fullconnect',
                'randomforest',
                'lightgbm',
                'xgboost',
                'catboost',
                'k-means',
                'cnn',
                'rnn'
              ]"
              variant="underlined"
              v-model="form.template"
              :rules="nonEmptyRule"
            ></v-select>

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

              <v-select
            clearable
              placeholder="点击选择团队"
              variant="underlined"
              :items="['冷面小青龙', '银河球棒侠', '开拓者小队', '星穹列车组']"
              v-if="form.type==='t'"
            ></v-select>
            
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

interface Form {
  name: string;
  template: string;
  type: 's' | 't';
}

const nonEmptyRule = [
  (value: string) => {
    if (value) return true;
    return '不能为空';
  },
];

const form: Ref<Form> = ref({
  name: '',
  template:'',
  type: 's',
});

const isValid: Ref<boolean> = ref(false);

const submit = async () => {
  if (isValid.value) {
    loading.value = true;
    await createModel(form.value.name,form.value.template);
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
