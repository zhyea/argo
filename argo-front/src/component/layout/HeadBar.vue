<template>
	<el-header>
		<el-menu mode="horizontal" :ellipsis="false"
		         background-color="#263238" text-color="#FFF" active-text-color="#FFD04B">
			<el-menu-item class="logo" index="Home" @click="goTo">
				ARGO 内容管理
			</el-menu-item>

			<el-menu-item class="logo" index="1">
				<app-selector/>
			</el-menu-item>

			<el-sub-menu index="2">
				<template #title>
					<el-icon>
						<setting/>
					</el-icon>
					系统管理
				</template>
				<el-menu-item index="AppList" @click="goTo">
					<el-icon>
						<grid/>
					</el-icon>
					<span>应用管理</span>
				</el-menu-item>
				<el-menu-item index="1-2" @click="logout">
					<el-icon>
						<user/>
					</el-icon>
					用户管理
				</el-menu-item>
			</el-sub-menu>

			<el-sub-menu index="3">
				<template #title>
					<el-icon>
						<avatar/>
					</el-icon>
				</template>
				<el-menu-item index="3-1" @click="openChangePasswordPage">
					<el-icon>
						<Key/>
					</el-icon>
					<span>修改密码</span>
				</el-menu-item>
				<el-menu-item index="3-2" @click="logout">
					<el-icon>
						<SwitchButton/>
					</el-icon>
					退出登录
				</el-menu-item>
			</el-sub-menu>


		</el-menu>
	</el-header>

</template>


<script setup>

import {computed} from 'vue'
import {useRouter} from 'vue-router'
import {useAuthStore} from "@/store/auth";
import {useBreadcrumbStore} from "@/store/breadcrumb";
import {ElMessageBox, ElMessage} from "element-plus";
import {Avatar, Grid, Key, Setting, SwitchButton, User} from "@element-plus/icons-vue";
import {useAppStore} from "@/store/app";
import AppSelector from "@/component/parts/AppSelectPopover.vue";


defineProps({
	showBreadcrumb: Boolean,
})


const router = useRouter()
const authStore = useAuthStore()
const breadcrumbStore = useBreadcrumbStore()
const appStore = useAppStore()

const breadcrumb = computed(() => {
	return breadcrumbStore.breadcrumb;
});


const currentAppId = computed(() => {
	if (appStore.currentApp) {
		return appStore.currentApp.id;
	}
	return 0;
});


async function goTo(menu) {
	await router.push({
		name: menu.index
	})
}


/**
 * 打开更改密码页
 */
function openChangePasswordPage() {
	router.push({
		name: 'ChangePasswordPage'
	})
}


/**
 * 退出登录
 */
function logout() {
	ElMessageBox.confirm(
		'是否确认退出登录？', '提示',
		{
			confirmButtonText: '确认',
			cancelButtonText: '取消',
			type: 'warning',
		}
	).then(() => {
		appStore.clear()
		authStore.useLogout()
			.then(() => {
				router.push({
					name: 'Login'
				})
			})
	}).catch(() => {
		//用户点击了取消
		ElMessage.error({
			message: '请求退出异常',
		})
	})
}

</script>


<style lang="less" scoped>
.el-header {
	border-bottom: 1px solid #263238;
	background: #263238;
	color: #AFB5BD;
	height: 60px;
	line-height: 60px;
}


.el-menu--horizontal {
	border-bottom: 1px solid #263238;
}

.el-menu--horizontal > .el-menu-item:nth-child(2) {
	margin-right: auto;
}

.el-menu--horizontal .logo:hover {
	background-color: #263238;
}

.el-menu--horizontal .logo {
	color: #AFB5BD !important;
	border-bottom-color: #263238 !important;
	background: #263238 !important;
}

.el-menu--horizontal .el-sub-menu {
	margin-right: 30px !important;
}

.el-dropdown {
	line-height: 60px;
}

.el-button {
	border: none;
}


.open-menu {
	cursor: pointer;
}

.header-avatar {
	text-align: right;
	color: #AFB5BD;
}

.el-breadcrumb {
	line-height: 60px;
}

a {
	text-decoration: none;
}
</style>