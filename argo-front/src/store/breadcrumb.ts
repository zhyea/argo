import {defineStore} from "pinia";

export const useBreadcrumbStore = defineStore('breadcrumb', {

	state: () => ({
		local: '',
		breadcrumb: Array<string>(),
	}),


	actions: {

		set(breadcrumb: any[]) {

			const title: Array<string> = []

			breadcrumb.filter((item: any) => {
					return (title.indexOf(item.meta.title) < 0)
				}
			).forEach((item: any) => {
				title.push(item.meta.title)
				this.breadcrumb.push(item.meta.title)
			})
		}
	}

});