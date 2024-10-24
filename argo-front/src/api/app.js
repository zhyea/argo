import request from '@/utils/request.js'

// 获取应用列表
export function findAppList() {
	return request.get('/cms/app/all')
}


// 获取应用详情
export function getApp(appId) {
	return request.post('/cms/app/get', {
		appId: appId,
	})
}


// 新增应用记录
export function addApp(formData) {
	return request.post('/cms/app/add', formData)
}


// 编辑应用记录
export function editApp(formData) {
	return request.post('/cms/app/edit', formData)
}


// 删除应用记录
export function delApp(appId) {
	return request.post('/cms/app/delete', {
		appId: appId,
	})
}