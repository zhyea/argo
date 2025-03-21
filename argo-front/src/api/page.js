import request from "@/utils/request.js";


// 新增应用页面
export function addPage(pageData) {
	return request.post('/cms/app/page/add', pageData)
}


// 编辑应用页面
export function editPage(pageData) {
	return request.post('/cms/app/page/edit', pageData)
}


// 获取应用页面信息
export function getPage(pageId) {
	return request.post('/cms/app/page/get', {
		pageId: pageId,
	})
}


// 删除应用页面
export function deletePage(pageId) {
	return request.post('/cms/app/page/delete', {
		pageId: pageId,
	})
}


// 根据应用ID获取页面列表
export function findPages(appId, keyword, pageInfo) {
	return request.post('/cms/app/page/list', {
		appId: appId,
		keyword: keyword,
		pageNo: pageInfo.pageNo,
		pageSize: pageInfo.pageSize,
	})
}


// 根据关键字查询应用页面列表
export function queryAppPages(appI, keyword) {
	return request.post('/cms/app/page/query', {
		appId: appI,
		keyword: keyword,
	})
}


// 生成页面代码
export function generatePageCode() {
	return request.get('/cms/app/page/page-code')
}


// 关联FCI实例
export function mapFciList(pageId, fciIdList) {
	return request.post('/cms/app/page/map-fci', {
		pageId: pageId,
		fciIdList: fciIdList,
	})
}