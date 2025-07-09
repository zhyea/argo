import config from '@/config'
import {defineStore} from "pinia"

export const useMenuStore = defineStore("menu", {

	state: () => ({
		menu: config.locale,
	}),

	actions: {
		setLocale(lang: string) {
			this.menu = lang
		},


		check(path: string) {

		},
	}
});