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
    isShared:'0',
    thumbnailUrl: '',
    modelName: '',
    userId: '',
    dataJson: '',
    creatTime: '',
    modelURL: '',
  });
  const cloudStatusText: Ref<string> = ref('已保存');

  const currentEditing=ref('无人')

  const editStatus = ref(2);

  const currentOnline = ref([
    {
      name: 'zhuyu',
      avatar:
        'https://webstatic.mihoyo.com/upload/op-public/2023/09/19/e54bca1450e95b35572ceee21dfe5615_1514116217685999711.png',
      editing: false,
    },
    {
      name: '哆嗦熊',
      avatar:
        'https://fgo.kazemai.io/common/images/Servants/Status/505300/status_servant_3.png',
      editing: false,
    },
    {
      name: 'c',
      avatar:
        'https://webstatic.mihoyo.com/upload/op-public/2023/01/13/27c6336e12ad5704d54057837d30604f_3876077510878698727.png',
      editing: false,
    },
    {
      name: 'd',
      avatar:
        'https://webstatic.mihoyo.com/upload/op-public/2022/09/14/63201dab804cdd80c6c259e5f3df034d_1015235942753546049.png',
      editing: false,
    },
  ]);

  const setActive = (name: string) => {
    currentEditing.value=name;
    currentOnline.value.forEach(p => {
      p.editing = false
      if(p.name===name) p.editing=true
    })
  }

  const $reset = () => {
    isReady.value = true;
    modelType.value='';
    modelInfo.value = {
      UserName: '',
      modelId: '',
      lastEditTime: '',
      isFavourite: '0',
      isShared: '0',
      thumbnailUrl: '',
      modelName: '',
      userId: '',
      dataJson: '',
      creatTime: '',
      modelURL: '',
    };
    cloudStatusText.value = '已保存';
    currentEditing.value = '无人';
    editStatus.value=2;
  }

  return {
    cloudStatusText,
    modelInfo,
    isReady,
    modelType,
    currentOnline,
    currentEditing,
    setActive,
    editStatus,
    $reset,
  };
});
