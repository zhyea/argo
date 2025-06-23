import {defineStore} from "pinia"
import {findAllApps} from "@/api/app";
import {cacheAppList, cacheCurrentApp, getCachedAppList, getCachedCurrentApp} from "@/utils/cache";

export const useAppStore = defineStore("app", {

	state: () => ({
		appList: Array<any>(),
		currentApp: null,
	}),

	actions: {
		// 获取全部的app
		async fetchAllApps(): Promise<Array<any>> {
			const response = await findAllApps()
			console.log("response", response)
			this.appList = response.data;
			cacheAppList(this.appList)
			return this.appList
		},


		// 刷新app列表
		async refresh(): Promise<Array<any>> {
			return await this.fetchAllApps()
		},


		// 获取app列表
		async getAppList(): Promise<Array<any>> {
			const r = this.appList || getCachedAppList();
			if (!r || r.length === 0) {
				await this.refresh();
			}
			return this.appList;
		},


		// 删除app
		async delete(appId: number) {
			const appList = await this.getAppList();
			const idx = appList.findIndex(item => item.id === appId)
			if (idx < 0) {
				return
			}
			this.appList = appList.splice(idx, 1)
			cacheAppList(this.appList)
		},


		// 设置当前app
		async changeCurrent(appId: number) {
			const appList = await this.getAppList();
			this.currentApp = appList.find(item => item.appId === appId)
			cacheCurrentApp(this.currentApp)
		},


		// 获取当前app
		getCurrent() {
			return this.currentApp || getCachedCurrentApp()
		}
	}
});