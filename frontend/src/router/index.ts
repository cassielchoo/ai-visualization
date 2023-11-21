// Composables
import { createRouter, createWebHistory } from 'vue-router';

const routes = [
  {
    path: '/',
    redirect: '/home',
    meta: {
      requiresAuth: true,
    },
    component: () => import('@/layouts/stage/Default.vue'),
    children: [
      {
        path: 'project',
        name: 'project',
        redirect: '/project/model',

        meta: {
          title: '我的文件',
          icon: 'mdi-folder-outline',
        },
        component: () => import('@/views/Project/index.vue'),
        children: [
          {
            path: 'model',
            name: 'model',
            meta: {
              title: '我的文件',
            },
            component: () => import('@/components/stage/ModelFlow.vue'),
          },
          {
            path: 'dataset',
            name: 'dataset',
            meta: {
              title: '我的文件',
            },
            component: () => import('@/components/stage/DatasetFlow.vue'),
          },
        ],
      },
      {
        path: 'fav',
        name: 'fav',
        redirect: '/fav/model',

        meta: {
          title: '我的收藏',
          icon: 'mdi-star-circle',
        },
        component: () => import('@/views/Fav/index.vue'),
        children: [
          {
            path: 'model',
            name: 'favmodel',
            meta: {
              title: '我的收藏',
              type: 'fav',
            },
            component: () => import('@/components/stage/ModelFlow.vue'),
          },
          {
            path: 'dataset',
            name: 'favdataset',
            meta: {
              title: '我的收藏',
              type: 'fav',
            },
            component: () => import('@/components/stage/DatasetFlow.vue'),
          },
        ],
      },
      {
        path: 'shop',
        name: 'shop',
        redirect: '/shop/model',
        meta: {
          title: '分享社区',
          icon: 'mdi-shopping',
        },
        component: () => import('@/views/Shop/index.vue'),
        children: [
          {
            path: 'model',
            name: 'sharedmodel',
            meta: {
              title: '分享社区',
              type: 'shared',
            },
            component: () => import('@/components/stage/ModelFlow.vue'),
          },
          {
            path: 'dataset',
            name: 'shareddataset',
            meta: {
              title: '分享社区',
              type: 'shared',
            },
            component: () => import('@/components/stage/DatasetFlow.vue'),
          },
        ],
      },
      {
        path: 'team',
        name: 'team',
        meta: {
          title: '我的团队',
          icon: 'mdi-account-group-outline',
        },
        component: () => import('@/views/Project/index.vue'),
      },
      {
        path: 'newteam',
        name: 'newteam',
        meta: {
          title: '创建团队',
          icon: 'mdi-account-multiple-plus-outline',
        },
        component: () => import('@/views/Project/index.vue'),
      },
      {
        path: 'settings',
        name: 'settings',
        meta: {
          title: '设置',
          icon: 'mdi-cog',
        },
        component: () => import('@/views/Project/index.vue'),
      },
    ],
  },
  {
    path: '/edit/:id',
    name: 'edit',
    component: () => import('@/layouts/edit/Default.vue'),
  },
  {
    path: '/home',
    name: 'home',
    component: () => import('@/layouts/home/Default.vue'),
  },
  {
    path: '/test',
    name: 'test',
    component: () => import('@/views/test.vue'),
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

router.beforeEach((to, from) => {
  document.title = typeof to.meta.title === 'string' ? to.meta.title : 'EasyAI';

  if (to.meta.requiresAuth) {
    if (!localStorage.getItem('TOKEN')) {
      router.push({ name: 'home' });
      alert('请先登录');
    }
  }
});

export default router;
