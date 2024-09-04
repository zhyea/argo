import HomePage from "@/view/home/Home.vue";
import LoginPage from "@/view/auth/Login.vue"
import NotFoundPage from '@/view/error/NotFound.vue'


const routes = [
	{
		name: 'Home',
		path: '/',
		component: HomePage,
		meta: {
			title: '首页',
		},
		children: [],
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