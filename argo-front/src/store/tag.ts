import {defineStore} from "pinia"
import {ROUTE_NAMES} from "@/config";
import {routeByName, routeFormatTag} from "@/utils/helper";
import ROUTER from "@/router";
import {TagItem} from "@/model/tag";


export const useTagStore = defineStore("tag", {

	state: () => ({
		tagList: Array<TagItem>(),
	}),


	getters: {
		tags: state => {
			return state.tagList
		},
		cachedTags: state => {
			return state.tagList?.filter((item: TagItem) => item.cache)
				.map(item => item.name) || [];
		},
	},


	actions: {
		// 打开标签
		openTagView(tag: TagItem) {
			console.log(`openTagView: ${JSON.stringify(tag)}`);

			/*const dashboardName = ROUTE_NAMES.homeRouteName;

			// 如果不是仪表盘标签且 tagList 为空或者第一个标签不是仪表盘，则插入仪表盘标签
			if (tag.name !== dashboardName &&
				(this.tagList.length === 0 || this.tagList[0].name !== dashboardName)) {

				const dashboardRoute = routeByName(dashboardName);
				console.log('dashboard', dashboardRoute);

				const dashboardTag = routeFormatTag(dashboardRoute);
				dashboardTag.fullPath = ROUTE_NAMES.dashboardFullPath;
				this.tagList.splice(0, 0, dashboardTag);
			}*/

			// 如果标签尚未打开，则添加新标签
			if (!this.tagList.some(item => item.fullPath === tag.fullPath)) {
				this.tagList.push(tag);
			}
		},


		// 关闭标签
		closeTagView(key: string) {
			const index = this.tagList.findIndex(item => item.fullPath === key);
			if (index === -1) return;

			const [closedTag] = this.tagList.splice(index, 1);

			// 如果关闭的是当前路由页面，则跳转到前一个标签页或默认页面
			if (ROUTER.currentRoute.value.fullPath === closedTag.fullPath) {
				const previousTag = this.tagList[index - 1];
				if (previousTag) {
					ROUTER.push({path: previousTag.fullPath});
				}
			}
		},


		// 关闭标签处理
		closeTagHandle(tagIndices: number[]) {
			// 从后往前删除，避免索引错乱
			[...tagIndices].sort((a, b) => b - a).forEach(index => {
				this.tagList.splice(index, 1);
			});
		},


		clearTags() {
			console.log('-----------------------------------------clearTags-----');
			this.tagList = [];
		},
	}
});