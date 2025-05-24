import {createRouter, createWebHistory, RouteRecordRaw} from 'vue-router'

import routeConfig from './route-config'
import config, {ROUTE_NAMES} from "@/config";

const routes = routeConfig

//-创建路由对象
const router = createRouter({
	history: createWebHistory(`${import.meta.env.BASE_URL}`),
	routes,
})


//全局守卫  访问非Login界面时，验证是否已登录
router.beforeEach((to, from, next) => {

	//判断是否已登录 查sessionStorage中是否有token信息
	let token = sessionStorage.getItem(config.TOKEN)

	if (to.name !== ROUTE_NAMES.loginRouteName && !token) {
		// 未登录，跳转到登录页
		next({name: ROUTE_NAMES.loginRouteName});
	} else if (to.name === ROUTE_NAMES.loginRouteName && token) {
		// 已登录，不允许进入登录页，否则去Home页
		next({name: ROUTE_NAMES.homeRouteName});
	} else {
		next()
	}

})

export default router
