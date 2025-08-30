<template>
	<el-container class="home_container" direction="vertical">

		<head-bar :show-breadcrumb="true"/>

		<el-container direction="horizontal">
			<sidebar :collapsed="collapseFlag" :menu-items="sideMenuRef" :active-index="route.path"
			         @menu="changeSideBarState"/>

			<el-container direction="vertical">
				<tag-view/>
				<el-main>
					<router-view :key="$route.fullPath" v-slot="{ Component }">
						<el-scrollbar height="100%">
							<keep-alive :include="cachedTags">
								<component :is="Component"/>
							</keep-alive>
						</el-scrollbar>
					</router-view>
				</el-main>
			</el-container>
		</el-container>
	</el-container>
</template>

<script lang="ts" setup>
import {ref, computed, onMounted} from 'vue'

import Sidebar from '@/component/layout/SideBar.vue'
import HeadBar from '@/component/layout/HeadBar.vue'
import AppSelectDialog from "@/component/parts/AppSelectDialog.vue";

import menuItems from '@/view/home/menu'
import {useTagStore} from "@/store/tag";
import TagView from "@/component/layout/TagView.vue";
import {useAppStore} from "@/store/app";
import {useRoute, useRouter} from "vue-router";
import {fixMenuRoutes} from "@/utils/helper";


const collapseFlag = ref(false)

// 调整侧边折起状态
function changeSideBarState(status: any) {
	collapseFlag.value = status
}


const tagStore = useTagStore()
const cachedTags = computed(() => {
	return tagStore.cachedTags
})

const appStore = useAppStore()

const route = useRoute()
const sideMenuRef = computed(() => {
	const sideMenu = appStore.getCurrentAppSideMenu();
	console.log('sideMenu', sideMenu);
	if (sideMenu) {
		return sideMenu;
	}
	const path = route.path;
	const appId = (appStore.getCurrentAppId() || route.params.appId);
	return findMenu(appId, path);
})


function findMenu(currentAppId: number, path: string): any[] {

	console.log('menu', menuItems.app)

	if (!currentAppId) return menuItems.system;
	if (!path || path === '' || path === '/' || path === '/home') return menuItems.system;

	const systemMenu = menuItems.system.find(item => {
		const r = item.index === path;
		if (r) {
			return true;
		}
		return item.children && item.children.find(child => child.index === path)
	})

	if (systemMenu) {
		return menuItems.system;
	}

	return fixMenuRoutes(menuItems.app, currentAppId)
}


</script>

<style lang="less" scoped>
</style>