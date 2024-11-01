<template>
	<el-container class="home_container">

		<sidebar :collapsed="collapseFlag"
		         :menu-items="menuItems.app"/>

		<el-container direction="vertical">
			<head-bar :collapsed="collapseFlag" @menu="changeMenuState"/>
			<el-main>
				<router-view :key="$route.fullPath" />
			</el-main>
		</el-container>

	</el-container>

</template>

<script setup>
import {ref} from 'vue'

import Sidebar from '@/component/layout/SideBar.vue'
import HeadBar from '@/component/layout/HeadBar.vue'

import menuItems from '@/view/home/menu'
import {useRoute} from "vue-router";
import {getApp} from "@/api/app.js";


const route = useRoute()

const collapseFlag = ref(false)

function changeMenuState(status) {
	collapseFlag.value = status
}


function loadAppData() {
	let appId = route.query.appId

	getApp(appId).then(response => {

	})
}


</script>

<style scoped lang="less">
.home_container {
	height: 100%;
	background-color: #FFFFFF;
}

.el-main {
	padding: 0;
	height: 100%;
}
</style>