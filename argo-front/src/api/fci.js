import request from "@/utils/request.js";

// 获取组件列表
export function findFciList(appId, keyword, pageInfo) {
	return request.post('/cms/fci/list', {
		appId: appId,
		keyword: keyword,
		pageNo: pageInfo.pageNo,
		pageSize: pageInfo.pageSize,
	})
}


// 获取组件详情
export function getFci(fciId) {
	return request.post('/cms/fci/get', {
		fciId: fciId,
	})
}


// 新增组件记录
export function addFci(formData) {
	return request.post('/cms/fci/add', formData)
}


// 编辑组件记录
export function editFci(formData) {
	return request.post('/cms/fci/edit', formData)
}


// 删除组件记录
export function delFci(fciId) {
	return request.post('/cms/fci/delete', {
		fciId: fciId,
	})
}