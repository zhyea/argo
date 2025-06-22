import axios from 'axios';
import {ElMessage} from "element-plus";
import router from '@/router'
import config, {ROUTE_NAMES} from "@/config";
import {setHttpToken} from "@/api/auth";
import {useAuthStore} from "@/store/auth";
import {cacheToken, getCachedToken, removeCachedToken} from "@/utils/cache";

// axios.defaults.withCredentials = true
// axios.defaults.crossDomain = true


//1. 创建axios对象
const axiosInst = axios.create({
	withCredentials: true,
	baseURL: `${import.meta.env.VITE_BASE_URL}`,
	timeout: import.meta.env.VITE_REQUEST_TIMEOUT,
	// 请求头
	headers: {
		"Content-Type": "application/json;charset=UTF-8",
	},
});


//2. 请求拦截器
axiosInst.interceptors.request.use(cfg => {
		/*let token = getCachedToken()
		if (token) {
			console.log(`token:${token}`)
			cfg.headers.Authorization = `${token}`
		}*/
		return cfg;
	},
	error => {
		Promise.reject(error).then(() => {
			console.log(error)
		});
	}
);


//3. 响应拦截器
axiosInst.interceptors.response.use(
	resp => {
		const authStore = useAuthStore()

		const result = resp.data

		//判断code码
		const code = result.code;
		if (code === 0) {
			if (result?.authToken) {
				console.log(`received new authToken:${result.authToken}`)
				authStore.setToken(result.authToken)
			}
			return result;
		} else if (code === 100 || code === 102 || code === 103) {
			// 执行跳转到登录页
			console.log("token was removed")
			authStore.removeToken()
			router.push({name: ROUTE_NAMES.loginRouteName}).then(() => {
				console.log(name)
			})
		} else {
			let msg = (result.msg ? result.msg : resp.statusText)
			ElMessage.warning({
				message: msg,
				duration: 1500,
			})
			return Promise.reject(msg);
		}
	},
	error => {
		let resp = error.response
		if (!resp) {
			ElMessage.warning({
				message: '服务器开小差了',
				duration: 1500,
			})
		} else {
			let msg = (resp.data.msg ? resp.data.msg : resp.statusText)
			ElMessage.warning({
				message: msg,
				duration: 1500,
			})
		}

		return Promise.reject(error);
	}
);


export default axiosInst;