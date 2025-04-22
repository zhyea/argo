import request from "@/utils/request.js";
import {API} from "@/config/index.js";


/**
 * 发送登录请求
 */
export function doLogin(data) {

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


export function doPing(){

}


/**
 * 设置token
 */
export function setHttpToken(token) {
	request.defaults.headers.common.Authorization = `${token}`
}


/**
 * 设置token
 */
export function removeHttpToken() {
	request.defaults.headers.common.Authorization = ``
}