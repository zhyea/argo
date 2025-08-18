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
				<el-button type="primary" @click="handleAddFcm">新增模型</el-button>
			</div>

			<el-table :data="fcmListData" border style="width: 100%">
				<el-table-column show-overflow-tooltip min-width=160 prop="name" label="名称"/>
				<el-table-column show-overflow-tooltip min-width=90 prop="type" label="类型"
				                 :formatter="mapTypeEnum"/>
				<el-table-column show-overflow-tooltip min-width=70 prop="scope" label="作用域"
				                 :formatter="mapScopeEnum"/>
				<el-table-column show-overflow-tooltip min-width=160 prop="appName" label="应用"/>
				<el-table-column show-overflow-tooltip min-width=166 prop="createTime" label="创建时间"
				                 align="center"/>
				<el-table-column show-overflow-tooltip min-width=166 prop="updateTime" label="修改时间"
				                 align="center"/>
				<el-table-column label="操作" align="center" fixed="right" width=220>
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

	<fci-drawer ref="fciEditDrawerRef"/>
	<fcm-drawer @after-fcm-add="loadFcmListData" ref="fcmEditDrawerRef"/>
</template>

<script lang="ts" setup>
import {ref, onMounted} from 'vue'
import {useRoute, useRouter} from "vue-router";
import {delFcm, findFcmList} from "@/api/fcm";
import {ElMessage} from "element-plus";
import FciDrawer from "@/view/fci/FciEditDrawer.vue";
import FcmDrawer from "@/view/fcm/FcmEditDrawer.vue";
import {ROUTE_NAMES} from "@/config";
import {useEnumStore} from "@/store/enum";


const route = useRoute();
const router = useRouter();
const enumStore = useEnumStore();


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
	let appId = parseInt(route.params.appId as string)
	let pageInfo = pageData.value

	if (route.query.page) {
		pageInfo.pageNo = parseInt(route.query.page as string)
	}

	findFcmList(appId, keyword, pageInfo).then(response => {
		if (response && response.data && response.data.data) {
			fcmListData.value = response.data.data
			pageInfo.total = response.data.total
		}
	})
}


// 处理页面切换
function handlePageChange(val: any) {
	const appId = route.params.appId
	router.push({name: ROUTE_NAMES.fcmListRouteName, query: {appId: appId, page: val}})
		.then(() => {
			loadFcmListData()
		})

}

// 处理FCM删除
function handleDelete(row: any) {
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


// 组件实例编辑抽屉
const fciEditDrawerRef = ref()
// 组件模型编辑抽屉
const fcmEditDrawerRef = ref()

// 打开新增组件实例抽屉
function handleAddFci(row: any) {
	fciEditDrawerRef.value.openFciDrawerForAdd(row)
}

// 新增FCM
function handleAddFcm() {
	const appId = route.params.appId
	fcmEditDrawerRef.value.openFcmEditDrawer(0, appId)
}

// 处理FCM编辑
function handleEdit(row: any) {
	fcmEditDrawerRef.value.openFcmEditDrawer(row.fcmId, row.appId)
}

// 页面渲染前执行一些必要的操作
onMounted(() => {
	// 加载枚举数据
	enumStore.refresh();
	// 加载页面数据
	loadFcmListData();
})


function mapTypeEnum(row: any) {
	return enumStore.getEnumDesc('FcmTypeEnum', row.type)
}


function mapScopeEnum(row: any) {
	return enumStore.getEnumDesc('FcScopeEnum', row.scope)
}


</script>

<style scoped lang="less">


</style>