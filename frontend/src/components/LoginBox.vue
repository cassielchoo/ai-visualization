<template>
  <div>
    <v-card elevation="8" max-width="448" rounded="lg">
      <v-toolbar color="rgba(0, 0, 0, 0)" theme="dark">
        <template v-slot:prepend>
          <v-btn variant="text" color="primary" @click="emits('to-register')">
            去注册
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

        <v-form>
          <div class="text-medium-emphasis">用户名</div>

          <v-text-field
            density="compact"
            placeholder="输入用户名"
            prepend-inner-icon="mdi-identifier"
            variant="outlined"
            v-model="loginForm.userId"
            :rules="rules.useridRules"
          ></v-text-field>

          <div
            class="text-medium-emphasis d-flex align-center justify-space-between"
          >
            密码
            <a
              class="text-caption text-decoration-none text-blue"
              href="#"
              rel="noopener noreferrer"
              target="_blank"
            >
              忘记密码?
            </a>
          </div>

          <v-text-field
            :append-inner-icon="visible ? 'mdi-eye-off' : 'mdi-eye'"
            :type="visible ? 'text' : 'password'"
            density="compact"
            placeholder="输入密码"
            prepend-inner-icon="mdi-lock-outline"
            variant="outlined"
            @click:append-inner="visible = !visible"
            v-model="loginForm.userPasshash"
            :rules="rules.passwordRules"
          ></v-text-field>

          <v-btn
            block
            class="mt-2 mb-8"
            color="blue"
            size="large"
            variant="tonal"
            @click="submit"
            :loading="loginLoading"
          >
            登录
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
import { userLogin } from '@/service/users';
import { UserLoginProps } from '@/service/interface';
import { useRouter } from 'vue-router';

const router = useRouter();

const emits = defineEmits(['to-register', 'close']);

const theme = useTheme();
const isDark: ComputedRef<boolean> = computed(
  () => theme.global.current.value.dark,
);

const loginForm: Ref<UserLoginProps> = ref({
  userId: '',
  userPasshash: '',
});

const visible: Ref<boolean> = ref(false);

const rules = {
  useridRules: [
    (value: string) => {
      if (value) return true;
      return '用户名不能为空';
    },
  ],
  passwordRules: [
    (value: string) => {
      if (value) return true;
      return '密码不能为空';
    },
  ],
};

const loginLoading: Ref<boolean> = ref(false);

const submit = async () => {
  loginLoading.value = true;
  const res = await userLogin(loginForm.value);
  loginLoading.value = false;
  if (res.code === 200) router.push({ name: 'project' });
};
</script>
