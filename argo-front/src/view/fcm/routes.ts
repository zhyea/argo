export default [{
	name: 'FcmList',
	path: '/fcm-list',
	component: () => import('./FcmList.vue'),
	meta: {
		title: '模型列表',
	},
}, {
	name: 'FcmAdd',
	path: '/fcm-add',
	component: () => import('./FcmEditDrawer.vue'),
	meta: {
		title: '新增组件模型',
	},
}, {
	name: 'FcmEdit',
	path: '/fcm-edit',
	component: () => import('./FcmEditDrawer.vue'),
	meta: {
		title: '编辑组件模型',
	},
}]