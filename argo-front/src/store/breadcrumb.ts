import {defineStore} from "pinia";

export const useBreadcrumbStore = defineStore('breadcrumb', {

	state: () => ({
		local: '',
		breadcrumb: [],
	}),


	actions: {

		set(breadcrumb: any[]) {

			const title: string[] = []

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