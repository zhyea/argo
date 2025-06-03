<template>
	<el-container class="home_container">

		<sidebar :collapsed="collapseFlag"
		         :menu-items="menuItems.home"
		         @menu="changeSideBarState"/>

		<el-container direction="vertical">
			<head-bar :collapsed="collapseFlag" :show-breadcrumb="true"/>
			<tags-view></tags-view>
			<el-main>
				<router-view :key="$route.fullPath"/>
				<router-view v-slot="{ Component }">
					<el-scrollbar height="100%">
						<keep-alive :include="cacheTags">
							<component :is="Component" />
						</keep-alive>
					</el-scrollbar>
				</router-view>
			</el-main>
		</el-container>

	</el-container>
</template>

<script setup>
import {ref} from 'vue'

import Sidebar from '@/component/layout/SideBar.vue'
import HeadBar from '@/component/layout/HeadBar.vue'

import menuItems from '@/view/home/menu'
import TagsView from "@/component/layout/TagsView.vue";


const collapseFlag = ref(false)

// 调整侧边折起状态
function changeSideBarState(status) {
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