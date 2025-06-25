<template>
	<el-header>
		<el-menu mode="horizontal" :ellipsis="false"
		         background-color="#263238" text-color="#FFF" active-text-color="#FFD04B">
			<el-menu-item index="0">
				ARGO 内容管理
			</el-menu-item>


			<el-sub-menu index="2">
				<template #title>
					<el-icon>
						<avatar/>
					</el-icon>
				</template>
				<el-menu-item index="2-1" @click="openChangePasswordPage">
					<el-icon>
						<Key/>
					</el-icon>
					<span>修改密码</span>
				</el-menu-item>
				<el-menu-item index="2-2" @click="logout">
					<el-icon>
						<SwitchButton/>
					</el-icon>
					退出登录
				</el-menu-item>
			</el-sub-menu>


			<!--			&lt;!&ndash; avatar &ndash;&gt;-->
			<!--			<el-col :span="8" class="header-avatar">-->
			<!--				<el-dropdown>-->
			<!--					<span>-->
			<!--						<el-icon><avatar/></el-icon>-->
			<!--						<el-icon class="el-icon&#45;&#45;right">-->
			<!--							<arrow-down/>-->
			<!--						</el-icon>-->
			<!--					</span>-->

			<!--					<template #dropdown>-->
			<!--						<el-dropdown-menu>-->
			<!--							<el-dropdown-item @click="openChangePasswordPage">修改密码</el-dropdown-item>-->
			<!--							<el-dropdown-item @click="logout">退出登录</el-dropdown-item>-->
			<!--						</el-dropdown-menu>-->
			<!--					</template>-->
			<!--				</el-dropdown>-->
			<!--			</el-col>-->
		</el-menu>
	</el-header>

</template>


<script setup>

import {computed} from 'vue'
import {useRouter} from 'vue-router'
import {useAuthStore} from "@/store/auth";
import {useBreadcrumbStore} from "@/store/breadcrumb";
import {ElMessageBox, ElMessage} from "element-plus";
import {ArrowDown, Avatar} from "@element-plus/icons-vue";
import {useAppStore} from "@/store/app";


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


const appList = computed(() => {
	return appStore.getAppList();
})


const currentAppId = computed(() => {
	if (appStore.currentApp) {
		return appStore.currentApp.id;
	}
	return 0;
});


function changeSelectedApp(appId) {
	console.log("changeSelectedApp", appId)
	appStore.changeCurrent(appId)
	currentAppId.value = appStore.currentApp.id
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

.el-menu--horizontal > .el-menu-item:nth-child(1) {
	margin-right: auto;
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