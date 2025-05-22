import request from "@/utils/request";


// 获取枚举信息
export async function loadEnums(enumRef: any, resolve: any) {
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
function readResponse(res: any): Map<any, any> {
	if (!res?.data) return new Map();

	return new Map(
		Object.entries(res.data).map(([key, val]) => [
			key,
			new Map(
				Object.entries(val as Record<string, any>).map(([k, v]) => [
					parseInt(k),
					v,
				])
			),
		])
	);
}