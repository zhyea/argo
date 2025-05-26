import appRoutes from '@/view/app/routes'
import fcmRoutes from '@/view/fcm/routes'
import appHomeRoutes from '@/view/app/routes-app'
import appPageRoutes from '@/view/page/routes'


export default [
	{
		name: 'Home',
		path: '/',
		component: () => import('@/view/home/Home.vue'),
		meta: {
			title: '首页',
		},
		children: [
			...appRoutes,
			...fcmRoutes,
		],
	}, {
		name: 'Login',
		path: '/login',
		component: () => import('@/view/auth/Login.vue'),
	}, {
		name: 'AppHome',
		path: '/app/:appId',
		component: () => import( '@/view/app/AppHome.vue'),
		meta: {
			title: '应用首页',
		},
		children: [
			...appHomeRoutes,
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