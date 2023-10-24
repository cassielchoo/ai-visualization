// Composables
import { createRouter, createWebHistory } from "vue-router";

const routes = [
  {
    path: '/',
    redirect: '/project',
    component: () => import('@/layouts/default/Default.vue'),
    children: [
      {
        path: 'project',
        name: 'project',
        meta: {
          title: '我的文件',
          icon: 'mdi-folder-outline',
        },
        component: () => import('@/views/Project.vue'),
      },
      {
        path: 'fav',
        name: 'fav',
        meta: {
          title: '我的收藏',
          icon: 'mdi-star-circle',
        },
        component: () => import('@/views/Project.vue'),
      },
      {
        path: 'team',
        name: 'team',
        meta: {
          title: '我的团队',
          icon: 'mdi-account-group-outline',
        },
        component: () => import('@/views/Project.vue'),
      },
      {
        path: 'newteam',
        name: 'newteam',
        meta: {
          title: '创建团队',
          icon: 'mdi-account-multiple-plus-outline',
        },
        component: () => import('@/views/Project.vue'),
      },
      {
        path: 'settings',
        name: 'settings',
        meta: {
          title: '设置',
          icon: 'mdi-cog',
        },
        component: () => import('@/views/Project.vue'),
      },
    ],
  },
  {
    path: '/edit',
    component: () => import('@/layouts/edit/Default.vue'),
  },
  {
    path: '/testedit',
    component: () => import('@/views/testEdit.vue'),
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

router.beforeEach((to, from) => {
  document.title = typeof to.meta.title === "string" ? to.meta.title : "EasyAI";
});

export default router;
