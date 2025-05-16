import request from "@/utils/request.ts";


// 获取模型列表
export function findFcmList(appId, keyword, pageInfo) {
	return request.post('/cms/fcm/list', {
		appId: appId,
		keyword: keyword,
		pageNo: pageInfo.pageNo,
		pageSize: pageInfo.pageSize,
	})
}


// 获取模型详情
export function getFcm(fcmId) {
	return request.post('/cms/fcm/get', {
		fcmId: fcmId,
	})
}


// 新增模型记录
export function addFcm(formData) {
	return request.post('/cms/fcm/add', formData)
}


// 编辑模型记录
export function editFcm(formData) {
	return request.post('/cms/fcm/edit', formData)
}


// 删除模型记录
export function delFcm(fcmId) {
	console.log(fcmId)
	return request.post('/cms/fcm/delete', {
		fcmId: fcmId,
	})
}