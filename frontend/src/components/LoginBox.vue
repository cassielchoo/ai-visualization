<template>
  <div>
    <v-card class="pa-8" elevation="8" max-width="448" rounded="lg">
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
          prepend-inner-icon="mdi-email-outline"
          variant="outlined"
          v-model="loginForm.userid"
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
          v-model="loginForm.password"
          :rules="rules.passwordRules"
        ></v-text-field>

        <v-btn
          block
          class="mt-2 mb-8"
          color="blue"
          size="large"
          variant="tonal"
          @click="submit"
        >
          登录
        </v-btn>
      </v-form>

      <v-card-text class="text-center">
        <a
          class="text-blue text-decoration-none"
          href="#"
          rel="noopener noreferrer"
          target="_blank"
        >
          去注册
          <v-icon icon="mdi-chevron-right"></v-icon>
        </a>
      </v-card-text>
    </v-card>
  </div>
</template>

<script lang="ts" setup>
import { ComputedRef, Ref, computed, ref } from 'vue';
import lightLogo from '@/assets/light_logo.png';
import darkLogo from '@/assets/dark_logo.png';
import { useTheme } from 'vuetify/lib/framework.mjs';
import { userLogin } from '@/service/users';

const theme = useTheme();
let isDark: ComputedRef<boolean> = computed(
  () => theme.global.current.value.dark,
);

interface LoginForm {
  userid: string;
  password: string;
}

let loginForm: Ref<LoginForm> = ref({
  userid: '',
  password: '',
});

let visible: Ref<boolean> = ref(false);

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

const submit = async () => {
  const res = await userLogin({
    userId: loginForm.value.userid,
    userPasshash: loginForm.value.password,
  });
  console.log(res);
};
</script>
