import HomePage from "@/view/home/Home.vue";
import LoginPage from "@/view/auth/Login.vue"
import AppHomePage from '@/view/app/AppHome.vue'
import NotFoundPage from '@/view/error/NotFound.vue'

import appRoutes from '@/view/app/routes'
import fcmRoutes from '@/view/fcm/routes'
import appHomeRoutes from '@/view/app/routes-app'


const routes = [
	{
		name: 'Home',
		path: '/',
		component: HomePage,
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
		component: LoginPage
	}, {
		name: 'AppHome',
		path: '/app-home',
		component: AppHomePage,
		meta: {
			title: '应用首页',
		},
		children: [
			...appHomeRoutes
		],
	},
	{
		path: "/:pathMatch(.*)*", component: NotFoundPage, name: 'notFound'
	}
]

export default routes