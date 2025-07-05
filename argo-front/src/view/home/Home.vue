<template>
	<el-container class="home_container" direction="vertical">

		<head-bar :show-breadcrumb="true"/>

		<el-container direction="horizontal">
			<sidebar :collapsed="collapseFlag"
			         :menu-items="sideMenuRef"
			         @menu="changeSideBarState"/>

			<el-container direction="vertical">
				<!--				<tag-view/>-->
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

	<app-select-dialog ref="appSelectorRef"/>
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
import {useRoute} from "vue-router";


const collapseFlag = ref(false)

// 调整侧边折起状态
function changeSideBarState(status: any) {
	collapseFlag.value = status
}


const tagStore = useTagStore()
const cachedTags = computed(() => {
	return tagStore.cachedTags
})


const route = useRoute()
const sideMenuRef = ref()

const appStore = useAppStore()
const appSelectorRef = ref()
onMounted(() => {
	const appId = route.params.appId

	if (appId) {
		sideMenuRef.value = menuItems.app
	} else {
		sideMenuRef.value = menuItems.system
	}

	if (!appStore.getCurrent()) {
		appSelectorRef.value.openAppSelectorDialog()
	}
})


</script>

<style lang="less" scoped>
</style>