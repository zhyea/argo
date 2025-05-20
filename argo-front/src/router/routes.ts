import HomePage from "@/view/home/Home.vue";
import LoginPage from "@/view/auth/Login.vue"
import AppHomePage from '@/view/app/AppHome.vue'
import NotFoundPage from '@/view/error/NotFound.vue'
import FciDetailPage from "@/view/fci/FciDetail.vue";


import appRoutes from '@/view/app/routes'
import fcmRoutes from '@/view/fcm/routes'
import appHomeRoutes from '@/view/app/routes-app'
import appPageRoutes from '@/view/page/routes'


export interface RouteItem {
	name: string;
	path: string;
	fullPath?: string;
	component: any;
	meta?: RouteMeta;
	children?: RouteItem[];

}


export interface RouteMeta {
	title: string;
	cache?: boolean;
	notClosable?: boolean;
}


const routes: RouteItem[] = [
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
		path: '/app/:appId',
		component: AppHomePage,
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
		component: FciDetailPage,
	},
	{
		path: "/:pathMatch(.*)*",
		component: NotFoundPage,
		name: 'notFound'
	}
]

export default routes