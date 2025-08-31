import {defineStore} from "pinia"
import {findAllApps} from "@/api/app";
import {
	cacheAppList, cacheCurrentApp, clearCachedAppInfo, getCachedAppList, getCachedCurrentApp
} from "@/utils/cache";
import {setLastVisitedApp} from "@/api/preference";
import {fixSideMenu} from "@/utils/helper";
import menuItems from '@/view/home/menu'

export const useAppStore = defineStore("app", {

	state: () => ({
		appList: Array<any>(),
		currentApp: null,
	}),

	actions: {
		// 获取全部的app
		async fetchAllApps(): Promise<Array<any>> {
			const response = await findAllApps()
			if (response && response.data) {
				this.appList = response.data
				cacheAppList(this.appList)
			}
			return this.appList
		},


		// 刷新app列表
		async refresh(): Promise<Array<any>> {
			return await this.fetchAllApps()
		},


		// 获取app列表
		getAppList(): Array<any> {
			if (this.appList && this.appList.length > 0) {
				return this.appList;
			}
			return getCachedAppList();
		},


		// 删除app
		delete(appId: number) {
			const appList = this.getAppList();
			const idx = appList.findIndex(item => item.id === appId);
			if (idx < 0) {
				return;
			}
			this.appList = appList.splice(idx, 1);
			cacheAppList(this.appList);
		},


		// 设置当前app
		async changeCurrent(appId: number) {
			await setLastVisitedApp(appId);

			const appList = this.getAppList();
			if (!appList || appList.length === 0) {
				console.error("appList is empty");
			}
			this.currentApp = appList.find(item => item.id === appId);
			cacheCurrentApp(this.currentApp);
		},


		// 获取当前app
		getCurrent() {
			return this.currentApp || getCachedCurrentApp();
		},


		// 获取当前appId
		getCurrentAppId() {
			const app = this.getCurrent();
			return app ? app.id : null;
		},


		// 清空app列表
		clear() {
			this.appList = [];
			this.currentApp = null;
			clearCachedAppInfo();
		}
	}
});