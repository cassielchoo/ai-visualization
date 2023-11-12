// Utilities
import { defineStore } from 'pinia';
import { Ref, ref } from 'vue';

export const useAppStore = defineStore('app', () => {
  const token: Ref<string> = ref(localStorage.getItem('TOKEN') ?? '');

  const userId: Ref<string> = ref(localStorage.getItem('USERID') ?? '');

  const saveLoginInfo = (t: string, id: string) => {
    token.value = t;
    userId.value = id;
    localStorage.setItem('TOKEN', t);
    localStorage.setItem('USERID', id);
  };

  const deleteLoginInfo = () => {
    localStorage.removeItem('TOKEN');
    localStorage.removeItem('USERID');
    token.value = '';
    userId.value = '';
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
  };
});
