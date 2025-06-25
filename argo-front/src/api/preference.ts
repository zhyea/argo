import request from "@/utils/request";


// 设置最后访问的app
export function setLastVisitedApp(appId: number) {
	return request.post('/admin/user/last-visit-app', {
		appId: appId,
	})
}


// 获取最后访问的app
export async function getLastVisitedApp() {
	const res = await request.get('/admin/user/last-visit-app')

	if (!res || !res.data) {
		return null;
	}
	return res.data as number
}