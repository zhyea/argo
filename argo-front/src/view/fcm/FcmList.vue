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
				<el-table-column show-overflow-tooltip min-width=120 prop="type" label="类型"/>
				<el-table-column show-overflow-tooltip min-width=120 prop="scope" label="作用域"/>
				<el-table-column show-overflow-tooltip min-width=270 prop="appName" label="应用"/>
				<el-table-column show-overflow-tooltip min-width=120 prop="dataBindFlag" label="绑定数据"/>
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
			<el-pagination :pager-count="5"
			               :page-size="pageData.pageSize"
			               :current-page="pageData.pageNo"
			               :total="pageData.total"
			               @size-change="handlePageChange"
			               @current-change="handlePageChange"
			               layout="prev, pager, next"/>
		</div>
	</div>
</template>

<script setup>
import {ref} from 'vue'
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
		}
	})
}


// 处理页面切换
const handlePageChange = async (val) => {
	console.log(val)
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
const enumMap = ref({})

// 页面渲染前执行一些必要的操作
onMounted(() => {
	// 加载枚举数据
	loadEnums(enumMap)
})

</script>

<style scoped lang="less">

</style>