<template>
	<el-container class="home_container" direction="vertical">

		<head-bar :show-breadcrumb="true" @after-app-changed="replaceSideMenu"/>

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
import {computed, onMounted, ref} from 'vue'

import Sidebar from '@/component/layout/SideBar.vue'
import HeadBar from '@/component/layout/HeadBar.vue'

import menuItems from '@/view/home/menu'
import {useTagStore} from "@/store/tag";
import TagView from "@/component/layout/TagView.vue";
import {useAppStore} from "@/store/app";
import {useRoute} from "vue-router";
import {fixSideMenu} from "@/utils/helper";


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
const sideMenuRef = ref()


onMounted(() => {
	const appId = (appStore.getCurrentAppId() || route.params.appId);
	sideMenuRef.value = findMenu(appId, route.path)
	console.log("------------------------- sideMenuRef22222222", appId, sideMenuRef.value)
})


function replaceSideMenu(currentAppId: number) {
	console.log("++++++++++++++++++++ replaceSideMenu", currentAppId)

	const path = route.path;
	const appId = (currentAppId || appStore.getCurrentAppId() || route.params.appId);
	console.log("++++++++++++++++++++ replaceSideMenu2", appId)

	sideMenuRef.value = findMenu(appId, path)
	console.log("++++++++++++++++++++ sideMenuRef11111111", sideMenuRef.value)
}


function findMenu(currentAppId: number, path: string): any[] {

	if (!currentAppId) return menuItems.system;
	if (!path || path === '' || path === '/' || path === '/home') return menuItems.system;

	const systemMenu = menuItems.system.find((item: any) => {
		const r = item.index === path;
		if (r) {
			return true;
		}
		return item.children && item.children.find((child: any) => child.index === path)
	});

	if (systemMenu) {
		return menuItems.system;
	}
	console.log("======================= findMenu", currentAppId)

	return fixSideMenu(menuItems.app, currentAppId);
}

</script>

<style lang="less" scoped>
</style>