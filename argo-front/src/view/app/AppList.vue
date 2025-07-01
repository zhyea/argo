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
				<el-table-column min-width=60 prop="icon" label="图标">
					<template #default="scope">
						<img :src="scope.row.icon" :alt="scope.row.appName" style="width: 40px; height: 40px;">
					</template>
				</el-table-column>

				<el-table-column show-overflow-tooltip min-width=90 prop="appCode" label="应用Code"
				                 class-name="table-user-code" align="center"/>
				<el-table-column show-overflow-tooltip min-width=150 prop="appName" label="应用名称"/>
				<el-table-column show-overflow-tooltip min-width=166 prop="createTime" label="创建时间"
				                 align="center"/>
				<el-table-column show-overflow-tooltip min-width=166 prop="updateTime" label="修改时间"
				                 align="center"/>
				<el-table-column label="操作" align="center" fixed="right" width=200>
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

</template>

<script setup>
import {ref} from 'vue'

import {findAllApps, findAppList} from "@/api/app";
import {useRouter} from "vue-router";
import PropsDrawer from "@/view/fci/FciPropsDrawer.vue";
import FciDrawer from "@/view/fci/FciEditDrawer.vue";

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


function handleAdd(){
	console.log('add app')
}


function handleEdit(row) {
	console.log('handle edit', row)
}


function handleDelete(row) {
	console.log('handle delete', row)
}




// 处理页面切换
function handlePageChange(val: any){
	const appId = route.params['appId']
	router.push({name: route['fciListRouteName'], query: {appId: appId, page: val}})
		.then(() => {
			loadFciListData()
		})
}


const router = useRouter();

// 跳转到应用页面
const goToAppPage = (appId) => {
	console.log(appId)
	let params = {appId: appId}
	router.push({path: '/app/' + appId})
}
</script>

<style scoped lang="less">

.app-container {
	display: flex;
}

.app-box {
	display: grid;
	width: 150px;
	height: 150px;
	justify-content: center;

	.app-icon {
		display: flex;
		margin: 20px 30px 0 30px;
		padding: 0;
		height: 90px;
		width: 90px;
		overflow: hidden;
		border: 3px solid #42b983;
		border-radius: 50%;
		background-color: #FFFFFF;
		align-items: center;
		justify-content: center;
		font-size: 36px;
		font-weight: bold;
		color: #42b983;
	}

	.app-icon-img {
		display: block;
		margin: 0;
		padding: 0;
		width: 80px;
		height: 80px;
	}

	.app-title {
		display: block;
		margin: 0 20px 6px 20px;
		padding: 0;
		width: 110px;
		height: 20px;
		overflow: hidden;
		text-align: center;
		font-family: 'Monospaced', 'serif';
		font-weight: bolder;
		color: #1a1a1a;
	}
}

.app-box:hover {
	background-color: #F0FFF0;
	cursor: pointer;

	.app-title {
		color: #888888;
	}
}
</style>