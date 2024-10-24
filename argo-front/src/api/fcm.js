import request from "@/utils/request.js";


// 获取模型列表
export function findFcmList(pageReq) {
	return request.post('/cms/fcm/list', {
		appId: pageReq.appId,
		keyword: pageReq.keyword,
		pageNo: pageReq.pageNo,
		pageSize: pageReq.pageSize,
	})
}


// 获取模型详情
export function getFcm(fcmId) {
	return request.get('/cms/fcm/get', {
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
	return request.post('/cms/fcm/delete', {
		fcmId: fcmId,
	})
}