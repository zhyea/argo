import request from "@/utils/request.js";


// 获取枚举信息
export function loadEnums(enumRef, resolve) {
	return request.get('/common/enums').then(res => {
		if (!res || !res.data) {
			enumRef.value = new Map([]);
			return;
		}

		let result = new Map()

		new Map(Object.entries(res.data)).forEach((val, key) => {
			let m = new Map()
			new Map(Object.entries(val)).forEach((v, k) => {
				m.set(parseInt(k), v)
			})

			result.set(key, m);
		})

		enumRef.value = result;

		if (resolve) {
			resolve();
		}
	});
}