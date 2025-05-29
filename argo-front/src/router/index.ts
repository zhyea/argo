import {createRouter, createWebHistory, RouteRecordRaw} from 'vue-router'

import routeConfig from './route-config'
import {ROUTE_NAMES} from "@/config";
import {getCachedToken} from "@/utils/cache";
import {useAuthStore} from "@/store/auth";

//-创建路由对象
const router = createRouter({
	history: createWebHistory(`${import.meta.env.BASE_URL}`),
	routes: routeConfig as RouteRecordRaw[],
})


//全局守卫  访问非Login界面时，验证是否已登录
router.beforeEach((to, from, next) => {

	console.log('全局守卫', to, from);

	let token = getCachedToken()

	const authStore = useAuthStore();
	if (token && !authStore.token) {
		authStore.setToken(token);
	}

	if (!token && to.name !== ROUTE_NAMES.loginRouteName) {
		next({name: ROUTE_NAMES.loginRouteName});
		return;
	} else if (token && to.name === ROUTE_NAMES.loginRouteName) {
		// 已登录，不允许进入登录页，否则去Home页
		next({name: ROUTE_NAMES.homeRouteName});
	} else {
		next();
	}

})

export default router
