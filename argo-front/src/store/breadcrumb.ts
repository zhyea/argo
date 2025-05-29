import {defineStore} from "pinia";
import {MenuItem} from "@/model/route";

export const useBreadcrumbStore = defineStore('breadcrumb', {

	state: () => ({
		local: '',
		breadcrumb: Array<MenuItem>(),
	}),


	actions: {

		// 设置面包屑
		set(route: MenuItem[]) {

			const title: Array<string> = this.breadcrumb.map((item: MenuItem) => {
				return item.meta!.title
			})
			route.filter((item: MenuItem) => {
					return title.indexOf(item.meta!.title) < 0
				}
			).forEach((item: MenuItem) => {
				title.push(item.meta!.title)
				this.breadcrumb.push(item)
			})

		}
	}

});