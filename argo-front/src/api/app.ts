import request from '@/utils/request'

// 获取应用列表
export async function findAllApps() {
	return await request.get('/cms/app/all')
}


// 获取应用列表
export async function findAppList(keyword: string, pageNo: number, pageSize: number) {
	return await request.post('/cms/app/list', {keyword, pageNo, pageSize})
}


// 根据关键字查询应用列表
export function queryApps(keyword: string) {
	return request.post('/cms/app/query', {
		keyword: keyword,
	})
}


// 获取应用详情
export function getApp(appId: number) {
	return request.post('/cms/app/get', {
		appId: appId,
	})
}


// 新增应用记录
export function addApp(formData: any) {
	return request.post('/cms/app/add', formData)
}


// 编辑应用记录
export function editApp(formData: any) {
	return request.post('/cms/app/edit', formData)
}


// 删除应用记录
export function delApp(appId: number) {
	return request.post('/cms/app/delete', {
		appId: appId,
	})
}


// 生成应用编码
export function generateAppCode() {
	return request.get('/cms/app/app-code')
}