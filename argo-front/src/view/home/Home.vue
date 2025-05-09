<template>
	<el-container class="home_container">

		<sidebar :collapsed="collapseFlag"
		         :menu-items="menuItems.home"/>

		<el-container direction="vertical">
			<head-bar :collapsed="collapseFlag" :show-breadcrumb="true" @menu="changeMenuState"/>
			<el-main>
				<router-view :key="$route.fullPath"/>
			</el-main>
		</el-container>

	</el-container>
</template>

<script setup>
import {ref} from 'vue'

import Sidebar from '@/component/layout/SideBar.vue'
import HeadBar from '@/component/layout/HeadBar.vue'

import menuItems from '@/view/home/menu'


let collapseFlag = ref(false)

function changeMenuState(status) {
	collapseFlag.value = status
}
</script>

<style lang="less" scoped>

.home_container {
	height: 100%;
	background-color: #FFFFFF;
}

.el-main {
	padding: 0;
	height: 100%;
}
</style>