<template>
	<el-aside :width="collapsed ? '72px' : '250px'">
		<div class="sidebar-box">
			<div class="logo-box">
				<div class="normal" v-if="!collapsed">
					ARGO 内容管理
				</div>
				<div class="mini" v-else>
					<ArgoLogo/>
				</div>
			</div>

			<div class="nav-box">
				<el-menu class="el-menu-vertical"
				         :collapse="collapsed"
				         :router="true"
				         :collapse-transition="false"
				         background-color="#263238"
				         text-color="#afb5bd"
				         active-text-color="#ffffff">
					<menu-item v-for="item in menuItems" :item="item" :key="item.id"/>


					<el-menu-item class="el-menu-collapse">
						<el-icon>
							<ArgoLogo/>
						</el-icon>
						<span>折叠</span>
					</el-menu-item>
				</el-menu>
			</div>
		</div>
	</el-aside>
</template>

<script setup>

import MenuItem from '@/component/layout/MenuItem.vue'
import ArgoLogo from "@/component/icons/argo-logo.vue";

defineProps({
	collapsed: Boolean,
	menuItems: Array,
})
</script>


<style scoped lang="less">

.sidebar-box {
	display: flex;
	flex-direction: column;
	height: 100%;
	background: #263238;
	border-right: 1px solid #e6e6e6;
}

.logo-box {
	height: 60px;
	display: flex;
	line-height: 60px;
	color: #42b983;
	width: 100%;

	.normal {
		padding-left: 20px;
	}

	.mini {
		width: 100%;
		text-align: center;
		padding-right: 6px;
	}
}

.nav-box {
	flex: auto;
	overflow-y: auto;
}

.el-menu-vertical {
	height: 100%;
	border-right: 0;
}

.el-menu-collapse {
	bottom: 0;
	height: 100%;
	border-right: 0;

}
</style>