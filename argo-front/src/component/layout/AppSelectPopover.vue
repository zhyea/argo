

<template>
	<el-popover placement="bottom" :width="400" trigger="click">
		<template #reference>
			<el-button style="margin-right: 16px">Click to activate</el-button>
		</template>
		<el-table :data="appData">
			<el-table-column width="150" property="date" label="date" />
		</el-table>
	</el-popover>
</template>

<script setup lang="ts">

import {ref, computed, onMounted} from 'vue'

import {useAppStore} from "@/store/app";

const appStore = useAppStore()

const appData= computed(() => {
	return appStore.getAppList()
})

</script>

<style scoped lang="less">

</style>