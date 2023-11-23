import { BriefModel } from '@/types/model';

export const sortByTime = (projs: BriefModel[]) => {
  projs.sort((a, b) => {
    const t1 = new Date(a.lastEditTime);
    const t2 = new Date(b.lastEditTime);
    return t2.getTime() - t1.getTime();
  });
  return projs;
};