import {defineStore} from "pinia"
import {loadEnumMap} from "@/api/common";
import {cacheEnums, getCachedEnums} from "@/utils/cache";


export const useEnumStore = defineStore("enum", {

	state: () => ({
		enums: new Map<string, Map<number, string>>(),
	}),


	actions: {

		// 打开标签
		async refresh() {
			if (this.enums.size === 0) {
				this.enums = await loadEnumMap()
				cacheEnums(this.enums)
			} else {
				loadEnumMap().then((data) => {
					this.enums = data;
					cacheEnums(this.enums)
				})
			}
		},


		// 获取枚举集合
		getEnumMap(enumName: string): Map<number, string> {
			let enums = this.enums;
			if (!this.enums) {
				enums = this.enums || getCachedEnums()
			}
			if (!enums || enums.size === 0) {
				this.refresh().then(() => {});
			}
			return enums.get(enumName) || new Map<number, string>();
		},


		// 根据枚举名称和枚举值获取枚举描述
		getEnumDesc(enumName: string, enumCode: number): string {
			return this.getEnumMap(enumName).get(enumCode) || "";
		},

	}
});