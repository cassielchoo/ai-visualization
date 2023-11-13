// Utilities
import { defineStore } from 'pinia';
import { Ref, ref } from 'vue';
import { User } from '@/types/user';


export const useAppStore = defineStore('app', () => {
  const token: Ref<string> = ref(localStorage.getItem('TOKEN') ?? '');

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

  const deleteLoginInfo = () => {
    localStorage.clear();
    token.value = '';
    handleGlobalMessaging('登出成功，正在跳转首页');
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
  };
});
