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

			<el-table :data="fcmListData" border style="width: 100%">
				<el-table-column type="index" width="50"/>
				<el-table-column show-overflow-tooltip min-width=270 prop="name" label="名称"/>
				<el-table-column show-overflow-tooltip min-width=120 prop="type" label="类型" :formatter="mapTypeEnum"/>
				<el-table-column show-overflow-tooltip min-width=120 prop="scope" label="作用域"
				                 :formatter="mapScopeEnum"/>
				<el-table-column show-overflow-tooltip min-width=270 prop="appName" label="应用"/>
				<el-table-column show-overflow-tooltip min-width=60 prop="dataBindFlag" label="绑定数据"
				                 :formatter="mapDataBindFlag" align="center"/>
				<el-table-column label="操作" align="center" fixed="right" width=136>
					<template #default="scope">
						<el-button type="success" size="small" @click="handleEdit(scope.row)">
							查看
						</el-button>
						<el-button type="danger" size="small" @click="handleDelete(scope.row)">
							删除
						</el-button>
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
import {ref, onMounted} from 'vue'
import {useRoute} from "vue-router";
import {findFcmList} from "@/api/fcm.js";
import {loadEnums} from "@/api/common.js";


const route = useRoute();

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

	findFcmList(appId, keyword, pageInfo).then(response => {
		if (response && response.data && response.data.data) {
			fcmListData.value = response.data.data
			pageInfo.total = response.data.total
		}
	})
}


// 处理页面切换
const handlePageChange = async (val) => {
	console.log(val)
	pageData.value.pageNo = val
	loadFcmListData()
}

// 处理FCM编辑
function handleEdit(row) {
	console.log(row)
}

// 处理FCM删除
function handleDelete(row) {
	console.log(row)
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