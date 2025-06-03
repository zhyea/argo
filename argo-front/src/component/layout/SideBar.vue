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
				<el-scrollbar height="100%">
					<el-menu class="el-menu-vertical"
					         :collapse="collapsed"
					         :router="true"
					         :collapse-transition="false"
					         background-color="#263238"
					         text-color="#afb5bd"
					         active-text-color="#ffffff">
						<menu-item v-for="item in menuItems" :item="item" :key="item.id"/>
					</el-menu>
				</el-scrollbar>
			</div>


			<div class="collapse-box" @click="sidebarSwitch">
				<div class="normal" v-if="!collapsed">
					<el-icon>
						<fold/>
					</el-icon>
					折叠菜单
				</div>
				<div class="mini" v-else>
					<el-icon>
						<expand/>
					</el-icon>
				</div>
			</div>

		</div>
	</el-aside>
</template>

<script setup>

import MenuItem from '@/component/layout/MenuItem.vue'
import ArgoLogo from "@/component/icons/argo-logo.vue";
import {Expand, Fold} from "@element-plus/icons-vue";

let props = defineProps({
	collapsed: Boolean,
	menuItems: Array,
})
const emit = defineEmits(['menu'])


/**
 * 侧边栏开关
 */
function sidebarSwitch() {
	emit('menu', !props.collapsed)
}

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

.collapse-box {
	display: flex;
	height: 56px;
	line-height: 56px;
	align-items: center;
	color: #F0FFF0;
	width: 100%;
	font-size: 14px;

	.normal {
		padding-left: 20px;
	}

	.mini {
		width: 100%;
		text-align: center;
		padding-right: 6px;
	}

	.el-icon {
		width: 24px;
		height: 18px;
		font-size: 18px;
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

.collapse-box {
	margin-top: auto;
	cursor: pointer;
}

.collapse-box:hover {
	cursor: pointer;
}
</style>