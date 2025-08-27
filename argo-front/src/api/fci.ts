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
export function findFciProps(fciId: number, keyword: string, pageInfo: any) {
	return request.post('/cms/fci/prop/list', {
		fciId: fciId,
		keyword: keyword,
		pageNo: pageInfo.pageNo,
		pageSize: pageInfo.pageSize,
	})
}


export function getFciProp(fciPropId: number) {
	return request.post('/cms/fci/prop/get', {
		propId: fciPropId,
	})
}


export function addFciProp(formData: any) {
	return request.post('/cms/fci/prop/add', {
		switchFlag: formData.switchFlag,
		dataBindFlag: formData.dataBindFlag,
		dataUrl: formData.dataUrl,
		effectivePeriodType: formData.effectivePeriodType,
		effectiveStartTime: formData.effectiveStartTime,
		effectiveEndTime: formData.effectiveEndTime,
		fciId: formData.fciId,
		propKey: formData.propKey,
		propValue: formData.propValue,
		propValueSelector: formData.propValueSelector,
		remark: formData.remark,
	})
}


export function editFciProp(formData: any) {
	return request.post('/cms/fci/prop/edit', {
		id: formData.id,
		switchFlag: formData.switchFlag,
		dataBindFlag: formData.dataBindFlag,
		dataUrl: formData.dataUrl,
		effectivePeriodType: formData.effectivePeriodType,
		effectiveStartTime: formData.effectiveStartTime,
		effectiveEndTime: formData.effectiveEndTime,
		fciId: formData.fciId,
		propKey: formData.propKey,
		propValue: formData.propValue,
		propValueSelector: formData.propValueSelector,
		remark: formData.remark,
	})
}


export function delFciProp(fciPropId: number) {
	return request.post('/cms/fci/prop/delete', {
		propId: fciPropId,
	})
}


export function switchProp(fciPropId: number, switchFlag: number) {
	return request.post('/cms/fci/prop/switch', {
		propId: fciPropId,
		switchFlag: switchFlag
	})
}