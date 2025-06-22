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
		async getAppList() {
			const r = this.appList || getCachedAppList();
			if (!r || r.length === 0) {
				await this.refresh();
			}
			return this.appList;
		},


		// 删除app
		delete(appId: number) {
			const idx = this.getAppList().findIndex(item => item.appId === appId)
			if (idx < 0) {
				return
			}
			this.appList = this.getAppList().splice(idx, 1)
			cacheAppList(this.appList)
		},


		// 设置当前app
		changeCurrent(appId: number) {
			this.currentApp = this.getAppList().find(item => item.appId === appId)
			cacheCurrentApp(this.currentApp)
		},


		// 获取当前app
		getCurrent() {
			return this.currentApp || getCachedCurrentApp()
		}
	}
});