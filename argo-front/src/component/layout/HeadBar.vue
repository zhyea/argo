<template>
	<el-header>
		<el-row>

			<!-- logo, selector, menu or breadcrumb  -->
			<el-col :span="16">
				<div class="logo-box">
					<div class="normal">
						ARGO 内容管理
					</div>
				</div>
				<!--
				<el-breadcrumb separator="/">
					<el-breadcrumb-item v-for="item in breadcrumb"
						:to="item.path"
						:key="item.path">
							{{ item.meta.title }}
					</el-breadcrumb-item>
				</el-breadcrumb>
				-->
			</el-col>

			<!-- avatar -->
			<el-col :span="8" class="header-avatar">
				<el-dropdown>
					<span>
						<el-icon><avatar/></el-icon>
						<el-icon class="el-icon--right">
							<arrow-down/>
						</el-icon>
					</span>

					<template #dropdown>
						<el-dropdown-menu>
							<el-dropdown-item @click="openChangePasswordPage">修改密码</el-dropdown-item>
							<el-dropdown-item @click="logout">退出登录</el-dropdown-item>
						</el-dropdown-menu>
					</template>
				</el-dropdown>
			</el-col>
		</el-row>
	</el-header>

</template>


<script setup>

import {computed} from 'vue'
import {useRouter} from 'vue-router'
import {useAuthStore} from "@/store/auth";
import {useBreadcrumbStore} from "@/store/breadcrumb";
import {ElMessageBox, ElMessage} from "element-plus";
import {ArrowDown, Avatar, Expand, Fold} from "@element-plus/icons-vue";


defineProps({
	showBreadcrumb: Boolean,
})


const router = useRouter()
const authStore = useAuthStore()
const breadcrumbStore = useBreadcrumbStore()

const breadcrumb = computed(() => {
	return breadcrumbStore.breadcrumb
});


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
	border-bottom: 1px solid #374349;
	background: #263238;
	color: #AFB5BD;
	height: 60px;
	line-height: 60px;
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