import request from "@/utils/request.js";


// 获取枚举信息
export function loadEnums(enumRef, resolve) {
	return request.get('/common/enums').then(res => {
		if (!res || !res.data) {
			enumRef.value = new Map([]);
			return;
		}

		let result = new Map()

		new Map(Object.entries(res.data)).forEach((v, k) => {
			result.set(k, new Map(Object.entries(v)));
		})
		
		enumRef.value = result;

		if (resolve) {
			resolve();
		}
	});
}