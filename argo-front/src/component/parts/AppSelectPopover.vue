<template>
	<el-popover placement="bottom" :width="400" trigger="click">
		<template #reference>
			{{ prop.title }}
		</template>
		<el-table :data="appData" :row-click="selectApp">
			<el-table-column width="150" property="appName" label="date"/>
		</el-table>
	</el-popover>
</template>

<script setup lang="ts">

import {computed} from 'vue'
import {useAppStore} from "@/store/app";

const prop = defineProps({
	title: '',
})

const appStore = useAppStore()

const appData = computed(() => {
	const appList = appStore.getAppList()
	console.log(appList)
	return appList
})


function selectApp(row: any) {
	appStore.changeCurrent(row.appId)
}

</script>

<style scoped lang="less">

</style>