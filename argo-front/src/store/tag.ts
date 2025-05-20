import {defineStore} from "pinia"
import config, {route} from "@/config";
import {routeByName, routeFormatTag} from "@/utils/helper";
import router from "@/router";


interface Tag {

	name: string;

	cache: any;

	fullPath: string;
}


export const useTagStore = defineStore("tag", {

	state: () => ({
		tagList: Array<Tag>(),
	}),

	getters: {
		tags: state => state.tagList,
		cacheTags: state => {
			if (!state.tagList) {
				return [];
			}

			return state.tagList.filter(item => item.cache).map(item => item.name);
		}
	},

	actions: {

		openTagView(tag: Tag) {
			let isset = this.tagList.some(function (item) {
				return item.fullPath === tag.fullPath
			})

			let dashboardName = route.dashboardName
			if (tag.name !== dashboardName && (this.tagList.length === 0 || this.tagList[0].name !== dashboardName)) {
				let dashboardTag = routeFormatTag(routeByName(dashboardName))
				dashboardTag.fullPath = route.dashboardFullPath

				this.tagList.splice(0, 0, dashboardTag)
			}

			if (!isset) {
				this.tagList.push(tag)
			}
		},


		closeTagView(key: string) {
			for (let item of this.tagList) {
				if (key !== item.fullPath) {
					continue;
				}
				let index = this.tagList.indexOf(item)
				this.tagList.splice(index, 1)

				if (router.currentRoute.value.fullPath === item.fullPath) {
					return router.push({path: this.tagList[index - 1].fullPath})
				}
			}
		},

		closeTagHandle(tagList: Array<number>) {
			tagList.reverse().forEach(key => {
				this.tagList.splice(key, 1)
			})
		}
	}
})