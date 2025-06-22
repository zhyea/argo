import {defineStore} from "pinia";
import {MenuItem} from "@/model/route";

export const useBreadcrumbStore = defineStore('breadcrumb', {

	state: () => ({
		breadcrumb: Array<MenuItem>(),
	}),


	actions: {

		// 设置面包屑
		set(route: MenuItem[]) {
			const title: Array<string> = Array<string>();

			this.breadcrumb = route.filter((item: MenuItem) => {

					if (title.includes(item.meta!.title)) {
						return false;
					}

					title.push(item.meta!.title)

					return true;
				}
			)
		}
	}

});