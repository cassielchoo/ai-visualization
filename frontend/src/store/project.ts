import { Model } from '@/types/model';
import { defineStore } from 'pinia';

import { Ref, ref } from 'vue';

export const useProjectStore = defineStore('project', () => {
  const isReady = ref(true);

  const modelType=ref('')

  const modelInfo: Ref<Model> = ref({
    UserName: '',
    modelId: '',
    lastEditTime: '',
    isFavourite: '0',
    thumbnailUrl: '',
    modelName: '',
    userId: '',
    dataJson: '',
    creatTime: '',
    modelURL: '',
  });
  const cloudStatusText: Ref<string> = ref('已保存');
  return { cloudStatusText, modelInfo, isReady, modelType };
});
