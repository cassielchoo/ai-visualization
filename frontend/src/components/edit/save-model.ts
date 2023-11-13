import { saveModel } from '@/service/model';
import html2canvas from 'html2canvas';

export const handleSaveModel = async (modelId: string, dataJson: string) => {
  const canvas = (
    await html2canvas(document.getElementById('diagram')!, {
      backgroundColor: null, //画出来的图片有白色的边框,不要可设置背景为透明色（null）
      useCORS: true, //支持图片跨域
      scale: 1, //设置放大的倍数});
    })
  ).toDataURL('image/jpeg');

  saveModel({
    modelId,
    datajson: dataJson,
    thumbnailUrl: canvas,
  });
};