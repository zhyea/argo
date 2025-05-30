<template>

	<el-container v-if="validAppFlag" class="home_container">
		<sidebar :collapsed="collapseFlag" :menu-items="appMenu"/>

		<el-container direction="vertical">
			<head-bar :show-breadcrumb="true" @menu="changeSideBarState"/>
			<el-main>
				<router-view :key="$route.fullPath"/>
			</el-main>
		</el-container>
	</el-container>

	<el-container v-else v-loading="!validAppFlag"
	              element-loading-text="应用数据加载中 ······"
	              class="home_container">
		<el-main/>
	</el-container>

</template>

<script setup>
import {ref, onMounted, onActivated} from 'vue'

import Sidebar from '@/component/layout/SideBar.vue'
import HeadBar from '@/component/layout/HeadBar.vue'

import menuItems from '@/view/home/menu'
import {useRoute} from "vue-router";
import {getApp} from "@/api/app";


const route = useRoute()

const collapseFlag = ref(false)

// 调整侧边折起状态
function changeSideBarState(status) {
	collapseFlag.value = status
}


const appMenu = ref([])

// 调整菜单路由
function changeMenuRoutes() {
	const appId = route.params.appId
	const menus = menuItems.app

	if (!appId) return

	menus.forEach(menu => {
		if (menu.index) {
			menu.index = menu.index.replace(':appId', appId)
		}

		if (!menu.children) return

		menu.children.forEach(child => {
			if (child.index) {
				child.index = child.index.replace(':appId', appId)
			}
		})
	})

	appMenu.value = menus
}


// 是否需要loading蒙版
const validAppFlag = ref(false)


onMounted(() => {
	// 调整路径
	changeMenuRoutes()
	// 检查appIds是否有效
	loadAppData()
})


onActivated(() => {
	// 调整路径
	changeMenuRoutes()
})

// 加载应用数据判断应用ID是否有效
function loadAppData() {
	let appId = route.params.appId

	getApp(appId).then(response => {
		validAppFlag.value = response && response.data
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