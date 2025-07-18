import request from "@/utils/request";
import {API} from "@/config";


/**
 * 发送登录请求
 */
export function doLogin(data: any) {

	return request.post(
		API.LOGIN,
		{
			...data,
		})

}


/**
 * 发送退出请求
 */
export function doLogout() {
	return request.post(API.LOGOUT)
}


/**
 * 发送ping请求
 */
export function doPing() {
	return request.post(API.LOGOUT)
}
