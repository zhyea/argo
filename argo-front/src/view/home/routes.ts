import fcmRoutes from '@/view/fcm/routes'
import {appMaintainRoutes, appSubRoutes} from '@/view/app/routes'
import appPageRoutes from '@/view/page/routes'
import userRoutes from '@/view/user/routes'


export default [
	{
		name: 'Home',
		path: '/',
		component: () => import('@/view/home/Home.vue'),
		meta: {
			title: '首页',
		},
		children: [
			...appMaintainRoutes,
			...fcmRoutes,
			...userRoutes,
		],
	}, {
		name: 'Login',
		path: '/login',
		component: () => import('@/view/auth/Login.vue'),
	}, {
		name: 'AppHome',
		path: '/app/:appId',
		component: () => import('@/view/home/Home.vue'),
		meta: {
			title: '应用首页',
		},
		children: [
			...appSubRoutes,
			...appPageRoutes,
		],
	}, {
		name: 'FciDetail',
		path: '/fci/:fciId',
		component: () => ('@/view/fci/FciDetail.vue'),
	},
	{
		path: "/:pathMatch(.*)*",
		component: () => ('@/view/error/NotFound.vue'),
		name: 'notFound'
	}
]

//export default routeConfig