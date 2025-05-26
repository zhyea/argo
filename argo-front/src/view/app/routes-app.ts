import {ROUTE_NAMES} from "@/config";

export default [{
	name: 'AppEdit',
	path: '/app/:appId/edit',
	component: () => import('./AppEdit.vue'),
	meta: {
		title: '新增应用',
	},
}, {
	name: 'AppFcmList',
	path: '/app/:appId/app-fcm-list',
	component: () => import('../fcm/FcmList.vue'),
	meta: {
		title: '模型列表',
	},
}, {
	name: ROUTE_NAMES.fciListRouteName,
	path: '/app/:appId/fci-list',
	component: () => import('../fci/FciList.vue'),
	meta: {
		title: '模型列表',
	},
}, {
	name: 'AppFcmAdd',
	path: '/app/:appId/app-fcm-add',
	component: () => import('../fcm/FcmEditDrawer.vue'),
	meta: {
		title: '新增组件模型',
	},
}, {
	name: 'AppFcmEdit',
	path: '/app/:appId/app-fcm-edit/:fcmId',
	component: () => import('../fcm/FcmEditDrawer.vue'),
	meta: {
		title: '编辑组件模型',
	},
},]