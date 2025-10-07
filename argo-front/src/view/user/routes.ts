// 用户管理相关路由

export default [
  {
    name: 'UserMain',
    path: '/user-main',
    component: () => import('../home/Home.vue'),
    meta: {
      title: '用户管理',
    },
    children: [
      {
        name: 'UserList',
        path: '/user-list',
        component: () => import('./UserList.vue'),
        meta: {
          title: '用户列表',
        },
      },
      {
        name: 'UserAdd',
        path: '/user-add',
        component: () => import('./UserEditDrawer.vue'),
        meta: {
          title: '新增用户',
        },
      },
    ],
  },
];