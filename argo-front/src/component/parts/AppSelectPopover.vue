<template>
	<el-popover placement="bottom" :width="270" :visible="visible" trigger="click">
		<template #reference>
			<span @click="visible = true">
				{{ currentApp['appName'] }}
				<el-icon class="app-arrow"><ArrowDown/></el-icon>
			</span>
		</template>
		<el-table :data="appData" @row-click="selectApp" :show-header="false">
			<el-table-column property="appName" label="date"/>
		</el-table>
	</el-popover>
</template>

<script setup lang="ts">

import {computed, ref} from 'vue'
import {useAppStore} from "@/store/app";
import {ArrowDown} from "@element-plus/icons-vue";

const appStore = useAppStore()

const currentApp = computed(() => {
	return appStore.getCurrent();
})

const appData = computed(() => {
	const appList = appStore.getAppList()
	console.log(appList)
	return appList
})


const visible = ref(false)

const selectApp = (row: any) => {
	appStore.changeCurrent(row.id)
	visible.value = false
	console.log(visible)
}

</script>

<style scoped lang="less">
.app-arrow {
	font-size: 12px;
	display: inline-block;
	animation: rotateArrow 2s infinite linear;
}

@keyframes rotateArrow {
	0% {
		transform: rotate(0deg);
	}
	100% {
		transform: rotate(360deg);
	}
}
</style>