import {createRouter, createWebHistory, RouteRecordRaw} from 'vue-router'

import routeConfig from './route-config'
import config, {ROUTE_NAMES} from "@/config";
import {getCachedToken} from "@/utils/localforage";
import {useAuthStore} from "@/store/auth";

//-创建路由对象
const router = createRouter({
	history: createWebHistory(`${import.meta.env.BASE_URL}`),
	routes: routeConfig as RouteRecordRaw[],
})


//全局守卫  访问非Login界面时，验证是否已登录
router.beforeEach((to, from, next) => {

	//判断是否已登录 查sessionStorage中是否有token信息
	getCachedToken().then((token: string) => {

		if (!token) {
			next({name: ROUTE_NAMES.loginRouteName});
		}

		const authStore = useAuthStore();
		if (!authStore.token) {
			authStore.setToken(token);
		}

		if (to.name === ROUTE_NAMES.loginRouteName) {
			// 已登录，不允许进入登录页，否则去Home页
			next({name: ROUTE_NAMES.homeRouteName});
		} else {
			next();
		}

	}).catch((error) => {
		next({name: ROUTE_NAMES.loginRouteName});
	})

})

export default router
