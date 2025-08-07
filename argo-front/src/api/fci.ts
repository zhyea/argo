import request from "@/utils/request";

// 获取组件列表
export function findFciList(appId: number, keyword: string, pageInfo: any) {
	return request.post('/cms/fci/list', {
		appId: appId,
		keyword: keyword,
		pageNo: pageInfo.pageNo,
		pageSize: pageInfo.pageSize,
	})
}


// 获取组件详情
export function getFci(fciId: number) {
	return request.post('/cms/fci/get', {
		fciId: fciId,
	})
}


// 新增组件记录
export function addFci(formData: any) {
	return request.post('/cms/fci/add', formData)
}


// 编辑组件记录
export function editFci(formData: any) {
	return request.post('/cms/fci/edit', formData)
}


// 删除组件记录
export function delFci(fciId: number) {
	return request.post('/cms/fci/delete', {
		fciId: fciId,
	})
}


// 获取组件属性列表
export function findFciProps(fciId: number) {
	return request.post('/cms/fci/prop/list', {
		fciId: fciId,
	})
}


export function getFciProp(fciPropId: number) {
	return request.post('/cms/fci/prop/get', {
		fciPropId: fciPropId,
	})
}


export function addFciProp(formData: any) {
	return request.post('/cms/fci/prop/add', formData)
}


export function editFciProp(formData: any) {
	return request.post('/cms/fci/prop/edit', formData)
}


export function delFciProp(fciPropId: number) {
	return request.post('/cms/fci/prop/delete', {
		propId: fciPropId,
	})
}