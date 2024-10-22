import HomePage from "@/view/home/Home.vue";
import LoginPage from "@/view/auth/Login.vue"
import NotFoundPage from '@/view/error/NotFound.vue'

import appRoutes from '@/view/app/routes'


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
		],
	}, {
		name: 'Login',
		path: '/login',
		component: LoginPage
	},
	{
		path: "/:pathMatch(.*)*", component: NotFoundPage, name: 'notFound'
	}
]

export default routes