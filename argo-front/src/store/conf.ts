import config from '@/config'
import {defineStore} from "pinia"

export const useConfStore = defineStore("conf", {

	state: () => ({
		locale: config.locale,
	}),

	actions: {
		setLocale(lang: string) {
			this.locale = lang
		}
	}
});