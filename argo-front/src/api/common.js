import request from "@/utils/request.ts";


// 获取枚举信息
export function loadEnums(enumRef, resolve) {
	return request.get('/common/enums').then(res => {

		enumRef.value = readResponse(res);

		if (resolve) {
			resolve();
		}
	});
}


// 获取全部枚举信息
export async function loadEnumMap() {
	const res = await request.get('/common/enums')
	return readResponse(res)
}


/**
 * 读取响应信息
 *
 * @param res 响应信息
 * @returns {Map<any, any>} 枚举信息
 */
function readResponse(res) {

	let result = new Map()

	if (!res || !res.data) {
		return result;
	}

	new Map(Object.entries(res.data)).forEach((val, key) => {
		let m = new Map()
		new Map(Object.entries(val)).forEach((v, k) => {
			m.set(parseInt(k), v)
		})

		result.set(key, m);
	})

	return result;
}