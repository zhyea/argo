import request from "@/utils/request.js";


// 获取枚举信息
export function loadEnums(enumRef) {
	return request.get('/common/enums').then(res => {
		return enumRef.value = res.data;
	});
}