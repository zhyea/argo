import config from '@/config'
import {defineStore} from "pinia"
import {findAllApps} from "@/api/app";

export const useConfStore = defineStore("app", {

	state: () => ({
		appList: Array<any>(),
	}),

	actions: {
		// 获取app列表
		get(): Array<any> {
			return this.appList
		},

		// 刷新app列表
		async refresh(): Promise<Array<any>> {
			const response = await findAllApps()
			this.appList = response.data;
			return this.appList
		},

		// 删除app
		delete(appId: number) {
			const idx = this.appList.findIndex(item => item.appId === appId)
			if (idx < 0) {
				return
			}
			this.appList.splice(idx, 1)
		}
	}
});