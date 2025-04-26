<template>
	<div class="table-container">

		<div class="table-header">
			<el-form :model="keywordForm" label-width="60px" label-suffix=":" :inline="true"
			         @submit.native.prevent>
				<el-form-item label="关键字">
					<el-input v-model="keywordForm.keyword" @keyup.enter.native="loadFcmListData"/>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="loadFcmListData">查询</el-button>
				</el-form-item>
			</el-form>
		</div>

		<div class="table-body">
			<div class="table-button">
				<el-button type="primary" @click="handleAdd">新增模型</el-button>
			</div>

			<el-table :data="fcmListData" border style="width: 100%">
				<el-table-column show-overflow-tooltip min-width=160 prop="name" label="名称"/>
				<el-table-column show-overflow-tooltip min-width=90 prop="type" label="类型"
				                 :formatter="mapTypeEnum"/>
				<el-table-column show-overflow-tooltip min-width=70 prop="scope" label="作用域"
				                 :formatter="mapScopeEnum" />
				<el-table-column show-overflow-tooltip min-width=160 prop="appName" label="应用"/>
				<el-table-column show-overflow-tooltip min-width=60 prop="dataBindFlag" label="绑定数据"
				                 :formatter="mapDataBindFlag" align="center"/>
				<el-table-column show-overflow-tooltip min-width=166 prop="createTime" label="创建时间"
				                 align="center"/>
				<el-table-column show-overflow-tooltip min-width=166 prop="updateTime" label="修改时间"
				                 align="center"/>
				<el-table-column label="操作" align="center" fixed="right" width=240>
					<template #default="scope">
						<el-button type="warning" size="small" @click="handleAddFci(scope.row)">创建实例</el-button>
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

	<fci-drawer ref="fciAddDrawerRef"/>
	<fcm-drawer @after-fcm-add="loadFcmListData" ref="fcmEditDrawerRef"/>
</template>

<script setup>
import {ref, onMounted} from 'vue'
import {useRoute, useRouter} from "vue-router";
import {delFcm, findFcmList} from "@/api/fcm.js";
import {loadEnums} from "@/api/common.js";
import {config} from "@/config/index.js";
import {ElMessage} from "element-plus";
import FciDrawer from "@/view/fci/FciEditDrawer.vue";
import FcmDrawer from "@/view/fcm/FcmEditDrawer.vue";


const route = useRoute();
const router = useRouter();

// 分页数据
const pageData = ref({
	// 当前页
	pageNo: 1,
	// 页面长度
	pageSize: 10,
	// 记录总数
	total: 0,
})


// 搜索数据
const keywordForm = ref({
	// 关键字
	keyword: ''
})


// FCM列表数据
const fcmListData = ref([])

// 加载方法列表数据
function loadFcmListData() {
	let keyword = keywordForm.value.keyword
	let appId = route.params.appId
	let pageInfo = pageData.value

	if (route.query.page) {
		pageInfo.pageNo = parseInt(route.query.page)
	}

	findFcmList(appId, keyword, pageInfo).then(response => {
		if (response && response.data && response.data.data) {
			fcmListData.value = response.data.data
			pageInfo.total = response.data.total
		}
	})
}


// 处理页面切换
const handlePageChange = async (val) => {
	let appId = route.params.appId
	router.push({name: config.fcmListRouteName, query: {appId: appId, page: val}})
		.then(() => {
			loadFcmListData()
		})

}

// 处理FCM删除
function handleDelete(row) {
	delFcm(row.fcmId).then(response => {
		if (response && response.data) {
			ElMessage.success({
				message: '删除成功',
				duration: 1500,
			})
			loadFcmListData()
		}
	})
}


// 组件实例新增抽屉
const fciAddDrawerRef = ref()
// 组件模型编辑抽屉
const fcmEditDrawerRef = ref()

// 打开新增组件实例抽屉
function handleAddFci(row) {
	fciAddDrawerRef.value.openFciAddDrawer(row)
}

// 新增FCM
function handleAdd() {
	fcmEditDrawerRef.value.openFcmEditDrawer()
}

// 处理FCM编辑
function handleEdit(row) {
	fcmEditDrawerRef.value.openFcmEditDrawer(row.fcmId, row.appId)
}

// 枚举数据
const allEnumsMap = ref()

// 页面渲染前执行一些必要的操作
onMounted(() => {
	// 加载枚举数据
	loadEnums(allEnumsMap)
	// 加载页面数据
	loadFcmListData()
})


function mapTypeEnum(row, column, cellValue, index) {
	return mapEnum('FcmTypeEnum', row.type)
}


function mapScopeEnum(row, column, cellValue, index) {
	return mapEnum('FcScopeEnum', row.scope)
}


function mapDataBindFlag(row, column, cellValue, index) {
	return mapEnum('YesOrNo', row.dataBindFlag)
}


function mapEnum(enumType, enumCode) {
	let enumMap = allEnumsMap.value.get(enumType)
	let result
	if (enumMap) {
		result = enumMap.get(enumCode)
	}
	return result ? result : ''
}
</script>

<style scoped lang="less">

.table-container {
	margin: 0;
	padding: 12px;
	background-color: #2b4b6b;
}

.table-header, .table-body, .table-footer {
	display: block;
	margin: 0;
	padding: 12px;
	background-color: #FFFFFF;
	border: #535bf2 1px solid;
}

.table-header, .table-body {
	margin-bottom: 9px;
}

.table-header {
	padding-top: 24px;
}

.table-button {
	padding-bottom: 9px;
}
</style>