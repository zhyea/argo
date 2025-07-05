import {ROUTE_NAMES} from "@/config";

export const appMaintainRoutes = [
	{
		name: 'AppList',
		path: '/app-list',
		component: () => import('./AppList.vue'),
		meta: {
			title: '应用列表',
		},
	}
]

export const appSubRoutes = [
	{
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
	}
]