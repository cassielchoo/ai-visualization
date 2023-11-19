// Utilities
import { defineStore } from 'pinia';
import { ComputedRef, Ref, computed, ref } from 'vue';
import { User } from '@/types/user';
import vuetify from '@/plugins/vuetify';

export const useAppStore = defineStore('app', () => {
  const token: Ref<string> = ref(localStorage.getItem('TOKEN') ?? '');

  const isDark: ComputedRef<boolean> = computed(
    () => vuetify.theme.global.current.value.dark,
  );

  const toggleTheme = () => {
    vuetify.theme.global.name.value = vuetify.theme.global.current.value.dark
      ? 'light'
      : 'dark';
  };

  const saveLoginInfo = (t: string) => {
    token.value = t;
    localStorage.setItem('TOKEN', t);
  };

  const user: Ref<User> = ref({
    userId: '',
    userName: '',
    userPasshash: '',
    userPhone: '',
    userPhoto: '',
  });

  const deleteLoginInfo = (msg: string = '登出成功，正在跳转首页') => {
    localStorage.clear();
    token.value = '';
    handleGlobalMessaging(msg);
  };

  const message: Ref<string> = ref('');
  const showSnackBar: Ref<boolean> = ref(false);

  const handleGlobalMessaging = (m: string) => {
    message.value = m;
    showSnackBar.value = true;
  };

  return {
    saveLoginInfo,
    deleteLoginInfo,
    handleGlobalMessaging,
    message,
    showSnackBar,
    token,
    user,
    isDark,
    toggleTheme,
  };
});
