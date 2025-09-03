<template>
	<div class="table-container">

		<div class="table-header">
			<el-form :model="keywordForm" label-width="60px" label-suffix=":" :inline="true"
			         @submit.native.prevent>
				<el-form-item label="关键字">
					<el-input v-model="keywordForm.keyword" @keyup.enter.native="loadAppList"/>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="loadAppList">查询</el-button>
				</el-form-item>
			</el-form>
		</div>

		<div class="table-body">
			<div class="table-button">
				<el-button type="primary" @click="handleAdd">新增应用</el-button>
			</div>

			<el-table :data="appList" border stripe style="width: 100%">
				<el-table-column width="80px" prop="icon" label="图标" align="center">
					<template #default="scope">
						<img :src="scope.row.icon" :alt="scope.row.appName" style="width: 40px; height: 40px;">
					</template>
				</el-table-column>

				<el-table-column show-overflow-tooltip min-width=90 prop="appCode" label="应用Code"
				                 class-name="mono-code" align="center"/>
				<el-table-column show-overflow-tooltip min-width=150 prop="appName" label="应用名称"/>
				<el-table-column show-overflow-tooltip min-width=50 prop="appType" label="类型"
				                 :formatter="mapAppTypeEnum" align="center"/>
				<el-table-column show-overflow-tooltip min-width=150 prop="remark" label="备注"/>
				<el-table-column show-overflow-tooltip min-width=180 prop="createTime" label="创建时间"
				                 align="center"/>
				<el-table-column show-overflow-tooltip min-width=180 prop="updateTime" label="修改时间"
				                 align="center"/>
				<el-table-column label="操作" align="center" fixed="right" width=200>
					<template #default="scope">
						<el-button type="success" size="small" @click="handleEdit(scope.row)">编辑</el-button>
						<el-button type="success" size="small" @click="goToAppPage(scope.row.id)">跳转</el-button>
						<el-button type="danger" size="small" @click="handleDelete(scope.row)">删除</el-button>
					</template>
				</el-table-column>
			</el-table>
		</div>

		<div class="table-footer">
			<el-pagination background
			               :page-size="pageData.pageSize"
			               :current-page="pageData.pageNo"
			               :total="pageData.total"
			               @current-change="handlePageChange"
			               layout="total, prev, pager, next, ->, jumper"/>
		</div>
	</div>

	<app-drawer ref="appEditDrawerRef" @after-edit="loadAppList"/>
</template>

<script lang="ts" setup>
import {ref, onMounted} from 'vue'

import {delApp, findAppList} from "@/api/app";
import {useRouter} from "vue-router";
import {useRoute} from "vue-router";
import AppDrawer from "@/view/app/AppEditDrawer.vue";
import {ElMessage, ElMessageBox} from "element-plus";
import {getEnumDesc, goToApp} from "@/utils/helper";
import {useTagStore} from "@/store/tag";

const tagStore = useTagStore();

// 搜索数据
const keywordForm = ref({
	// 关键字
	keyword: ''
})

// 分页配置
const pageData = ref({
	// 当前页
	pageNo: 1,
	// 页面长度
	pageSize: 10,
	// 记录总数
	total: 0,
})

// 列表数据
const appList = ref([])

// 加载应用列表
function loadAppList() {
	const keyword = keywordForm.value.keyword;
	const pageNo = pageData.value.pageNo;
	const pageSize = pageData.value.pageSize;

	findAppList(keyword, pageNo, pageSize).then(res => {
		if (res && res.data && res.data.data) {
			appList.value = res.data.data
			pageData.value.total = res.data.total
		}
	})
}

onMounted(() => {
	loadAppList()
})


// 应用编辑抽屉
const appEditDrawerRef = ref()

// 打开新增应用抽屉
function handleAdd() {
	appEditDrawerRef.value.openAppEditDrawer(0)
}

// 打开应用编辑抽屉
function handleEdit(row: any) {
	appEditDrawerRef.value.openAppEditDrawer(row.id)
}


// 处理应用删除
function handleDelete(row: any) {
	ElMessageBox.prompt('请输入应用名称以确认删除', '删除确认', {
		confirmButtonText: '确认',
		cancelButtonText: '取消',
		inputErrorMessage: '应用名称错误',
		inputValidator: (value) => {
			return row.appName === value;
		}
	}).then(() => {
		delApp(row.id).then(() => {
			ElMessage.success({
				message: '删除成功',
				duration: 1500,
			});
			loadAppList()
		})
	}).catch(() => {
		ElMessage.info({
			message: '取消删除',
		})
	})
}

const route = useRoute()
const router = useRouter();

// 处理页面切换
function handlePageChange(val: any) {
	const appId = route.params['appId']
	router.push({name: 'fciListRouteName', query: {appId: appId, page: val}})
		.then(() => {
			loadAppList()
		})
}


// 跳转到应用页面
async function goToAppPage(appId: number) {
	await goToApp(appId, router)
}


// 映射应用类型枚举
function mapAppTypeEnum(row: any) {
	return getEnumDesc('AppTypeEnum', row.appType)
}
</script>


<style scoped lang="less">

</style>