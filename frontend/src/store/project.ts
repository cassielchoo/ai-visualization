import { defineStore } from 'pinia';

import { Ref, ref } from 'vue';

export const useProjectStore = defineStore('project', () => {
  const id: Ref<string> = ref('');
  return { id };
});
