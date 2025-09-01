import request from "@/utils/request";


// 新增应用页面
export function addPage(pageData: any) {
	return request.post('/cms/app/page/add', pageData)
}


// 编辑应用页面
export function editPage(pageData: any) {
	return request.post('/cms/app/page/edit', pageData)
}


// 获取应用页面信息
export function getPage(pageId: number) {
	return request.post('/cms/app/page/get', {
		pageId: pageId,
	})
}


// 删除应用页面
export function deletePage(pageId: number) {
	return request.post('/cms/app/page/delete', {
		pageId: pageId,
	})
}


// 根据应用ID获取页面列表
export function findPages(appId: number, keyword: string, pageInfo: any) {
	return request.post('/cms/app/page/list', {
		appId: appId,
		keyword: keyword,
		pageNo: pageInfo.pageNo,
		pageSize: pageInfo.pageSize,
	})
}


// 根据关键字查询应用页面列表
export function queryAppPages(appId: number, keyword: string) {
	return request.post('/cms/app/page/query', {
		appId: appId,
		keyword: keyword,
	})
}


// 生成页面代码
export function generatePageCode() {
	return request.get('/cms/app/page/page-code')
}



// 获取页面关联的FCI列表
export function relateFciList(pageId: number) {
	return request.post('/cms/app/page/relate-fci-list', {
		pageId: pageId,
	})
}


// 关联FCI实例
export function mapFciList(pageId: number, fciIdList: Array<number>) {
	return request.post('/cms/app/page/map-fci', {
		pageId: pageId,
		fciIdList: fciIdList,
	})
}