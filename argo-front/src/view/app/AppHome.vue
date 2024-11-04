<template>
	<el-container class="home_container">

		<sidebar :collapsed="collapseFlag"
		         :menu-items="appMenu"/>

		<el-container direction="vertical">
			<head-bar :collapsed="collapseFlag" @menu="changeSideBarState"/>
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
import {useRoute} from "vue-router";
import {getApp} from "@/api/app.js";


const route = useRoute()

const collapseFlag = ref(false)

// 调整侧边折起状态
function changeSideBarState(status) {
	collapseFlag.value = status
}


const appMenu = ref([])

// 调整菜单路由
function changeMenuRoutes() {
	const appId = route.query.appId
	const menus = menuItems.app

	menus.forEach(menu => {
		if (menu.uri) {
			menu.uri = menu.uri.replace(':appId', appId)
		}

		if (!menu.children) return

		menu.children.forEach(child => {
			if (child.uri) {
				child.uri = child.uri.replace(':appId', appId)
			}
		})
	})

	appMenu.value = menus
}


onMounted(() => {
	changeMenuRoutes()
})


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