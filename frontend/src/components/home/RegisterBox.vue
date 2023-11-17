<template>
  <div>
    <v-card elevation="8" max-width="448" rounded="lg">
      <v-toolbar color="rgba(0, 0, 0, 0)" theme="dark">
        <template v-slot:prepend>
          <v-btn variant="text" color="primary" @click="emits('to-login')">
            返回登录
            <v-icon icon="mdi-chevron-right"></v-icon>
          </v-btn>
        </template>

        <template v-slot:append>
          <v-btn
            icon="mdi-close"
            variant="plain"
            @click="emits('close')"
          ></v-btn>
        </template>
      </v-toolbar>

      <div class="px-8 py-3">
        <v-img
          class="mx-auto mb-3"
          max-width="228"
          :src="isDark ? darkLogo : lightLogo"
        ></v-img>

        <v-form @submit.prevent="submit" v-model="isValid">
          <div class="text-medium-emphasis">用户名</div>

          <v-text-field
            density="compact"
            placeholder="输入用户名"
            prepend-inner-icon="mdi-identifier"
            variant="outlined"
            v-model="registerForm.userId"
            :rules="rules.useridRules"
          ></v-text-field>

          <div
            class="text-medium-emphasis d-flex align-center justify-space-between"
          >
            密码
          </div>

          <v-text-field
            :append-inner-icon="visible ? 'mdi-eye-off' : 'mdi-eye'"
            :type="visible ? 'text' : 'password'"
            density="compact"
            placeholder="输入密码"
            prepend-inner-icon="mdi-lock-outline"
            variant="outlined"
            @click:append-inner="visible = !visible"
            v-model="registerForm.userPasshash"
            :rules="rules.passwordRules"
          ></v-text-field>

          <div
            class="text-medium-emphasis d-flex align-center justify-space-between"
          >
            手机号
          </div>

          <v-text-field
            type="text"
            density="compact"
            placeholder="输入手机号"
            prepend-inner-icon="mdi-phone"
            variant="outlined"
            v-model="registerForm.userPhone"
            :rules="rules.phoneRules"
          ></v-text-field>

          <div
            class="text-medium-emphasis d-flex align-center justify-space-between"
          >
            昵称
          </div>

          <v-text-field
            type="text"
            density="compact"
            placeholder="输入昵称"
            prepend-inner-icon="mdi-account"
            variant="outlined"
            v-model="registerForm.userName"
            :rules="rules.nameRules"
          ></v-text-field>

          <v-btn
            block
            class="mt-2 mb-8"
            color="blue"
            size="large"
            variant="tonal"
            type="submit"
            :loading="registerLoading"
          >
            注册
          </v-btn>
        </v-form>
      </div>
    </v-card>
  </div>
</template>

<script lang="ts" setup>
import { ComputedRef, Ref, computed, ref } from 'vue';
import lightLogo from '@/assets/light_logo.png';
import darkLogo from '@/assets/dark_logo.png';
import { useTheme } from 'vuetify/lib/framework.mjs';
import { userRegister } from '@/service/users';
import { UserRegisterProps } from '@/service/types/request';

const emits = defineEmits(['to-login', 'close']);

const theme = useTheme();
const isDark: ComputedRef<boolean> = computed(
  () => theme.global.current.value.dark,
);

const registerForm: Ref<UserRegisterProps> = ref({
  userId: '',
  userPasshash: '',
  userPhone: '',
  userName: '',
});

const visible: Ref<boolean> = ref(false);

const rules = {
  useridRules: [
    (value: string) => {
      if (!value) return '用户名不能为空';
      if (isNaN(Number(value))) return '用户名必须是数字';
      if (value.length > 16 || value.length < 8)
        return '用户名长度应在8-16个字符之间';
      return true;
    },
  ],
  passwordRules: [
    (value: string) => {
      if (!value) return '密码不能为空';
      if (value.length > 12 || value.length < 6)
        return '密码长度应在6-12个字符之间';
      return true;
    },
  ],
  phoneRules: [
    (value: string) => {
      if (value) return true;
      return '手机号不能为空';
    },
  ],
  nameRules: [
    (value: string) => {
      if (!value) return '昵称不能为空';
      if (value.length > 8) return '昵称长度不能大于8';
      return true;
    },
  ],
};

const isValid: Ref<boolean> = ref(false);

const registerLoading: Ref<boolean> = ref(false);

const submit = async () => {
  if (isValid.value) {
    registerLoading.value = true;
    await userRegister(registerForm.value);
    registerLoading.value = false;
  }
};
</script>
@/service/request @/types/request
