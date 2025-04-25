<template>
	<div class="table-container">

		<div class="table-header">
			<el-form :model="keywordForm" label-width="60px" label-suffix=":" :inline="true"
			         @submit.native.prevent>
				<el-form-item label="关键字">
					<el-input v-model="keywordForm.keyword" @keyup.enter.native="loadAppPageListData"/>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="loadAppPageListData">查询</el-button>
				</el-form-item>
			</el-form>
		</div>

		<div class="table-body">
			<div class="table-button">
				<el-button type="primary" @click="handleAdd">新增页面</el-button>
			</div>

			<el-table :data="appPageListData" border style="width: 100%">
				<el-table-column show-overflow-tooltip min-width=120 prop="pageId" label="ID"/>
				<el-table-column show-overflow-tooltip min-width=120 prop="pageCode" label="页面代码" align="center"/>
				<el-table-column show-overflow-tooltip min-width=120 prop="pageName" label="页面名称"/>
				<el-table-column show-overflow-tooltip min-width=166 prop="createTime" label="创建时间"/>
				<el-table-column show-overflow-tooltip min-width=166 prop="updateTime" label="更新时间"/>
				<el-table-column label="操作" align="center" fixed="right" width=240>
					<template #default="scope">
						<el-button type="success" size="small" @click="handleEdit(scope.row)">编辑</el-button>
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

	<page-drawer ref="appPageAddDrawerRef" @after-page-add="loadAppPageListData"/>
</template>

<script setup>
import PageDrawer from "@/view/page/AppPageEditDrawer.vue";
import {ref, onMounted} from "vue";
import {useRoute, useRouter} from "vue-router";
import {deletePage, findPages} from "@/api/page.js";
import {ElMessage} from "element-plus";


const route = useRoute();
const router = useRouter();

// 应用页面新增抽屉
const appPageAddDrawerRef = ref()

// 搜索数据
const keywordForm = ref({
	// 关键字
	keyword: ''
})

// 分页数据
const pageData = ref({
	// 当前页
	pageNo: 1,
	// 页面长度
	pageSize: 10,
	// 记录总数
	total: 0,
})

// 应用页面列表数据
const appPageListData = ref([])

// 加载方法列表数据
function loadAppPageListData() {
	let keyword = keywordForm.value.keyword
	let appId = route.params.appId
	let pageInfo = pageData.value

	if (route.query.page) {
		pageInfo.pageNo = parseInt(route.query.page)
	}

	findPages(appId, keyword, pageInfo).then(response => {
		if (response && response.data && response.data.data) {
			appPageListData.value = response.data.data
			pageInfo.total = response.data.total
		}
	})
}


// 新增应用页面
function handleAdd() {
	let appId = route.params.appId
	appPageAddDrawerRef.value.openAppPageDrawer(appId)
}

// 编辑应用页面
function handleEdit(row) {
	appPageAddDrawerRef.value.openAppPageDrawer(row.appId, row.pageId)
}

// 处理页面删除
function handleDelete(row) {
	deletePage(row.pageId).then(response => {
		if (response && response.data) {
			ElMessage.success({
				message: '删除成功',
				duration: 1500,
			})
			loadAppPageListData()
		}
	})
}

// 处理页面切换
const handlePageChange = async (val) => {
	let appId = route.params.appId
	/*router.push({name: config.fcmListRouteName, query: {appId: appId, page: val}})
		.then(() => {
			loadFcmListData()
		})*/
}


onMounted(() => {
	loadAppPageListData()
})

</script>

<style scoped lang="less">

</style>