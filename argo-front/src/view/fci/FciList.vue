<template>
	<div class="table-container">

		<div class="table-header">
			<el-form :model="keywordForm" label-width="60px" label-suffix=":" :inline="true"
			         @submit.native.prevent>
				<el-form-item label="关键字">
					<el-input v-model="keywordForm.keyword" @keyup.enter.native="loadFciListData"/>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="loadFciListData">查询</el-button>
				</el-form-item>
			</el-form>
		</div>

		<div class="table-body">
			<el-table :data="fciListData" border stripe style="width: 100%">
				<el-table-column show-overflow-tooltip min-width=120 prop="fcmName" label="模型名称"/>
				<el-table-column show-overflow-tooltip min-width=90 prop="fciCode" label="组件Code"
				                 class-name="table-user-code" align="center"/>
				<el-table-column show-overflow-tooltip min-width=150 prop="name" label="组件名称"/>
				<el-table-column min-width=60 prop="type" label="类型" :formatter="mapTypeEnum"/>
				<el-table-column width=55 prop="dataBindFlag" label="绑定数据"
				                 :formatter="mapDataBindFlag" align="center"/>
				<el-table-column show-overflow-tooltip min-width=220 prop="type" label="生效周期" align="center"
				                 :formatter="formatEffectivePeriod"/>
				<el-table-column show-overflow-tooltip min-width=166 prop="createTime" label="创建时间"
				                 align="center"/>
				<el-table-column show-overflow-tooltip min-width=166 prop="updateTime" label="修改时间"
				                 align="center"/>
				<el-table-column label="操作" align="center" fixed="right" width=200>
					<template #default="scope">
						<el-button type="success" size="small" @click="handleEdit(scope.row)">编辑</el-button>
						<el-button type="warning" size="small" @click="showProps(scope.row)">详情</el-button>
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

	<fci-drawer ref="fciEditDrawerRef" @after-fci-edit="loadFciListData"/>
	<props-drawer ref="fciPropsDrawerRef"/>
</template>

<script setup>
import {ref, onMounted} from 'vue'
import {useRoute, useRouter} from "vue-router";
import {ElMessage} from "element-plus";
import FciDrawer from "@/view/fci/FciEditDrawer.vue";
import PropsDrawer from "@/view/fci/FciPropsDrawer.vue";
import {delFci, findFciList} from "@/api/fci";
import {formatEffectivePeriod, mapDataBindFlag} from "@/view/helper";
import {useEnumStore} from "@/store/enum";


const route = useRoute();
const router = useRouter();

const enumStore = useEnumStore()

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


// FCI列表数据
const fciListData = ref([])

// 加载方法列表数据
function loadFciListData() {
	let keyword = keywordForm.value.keyword
	let appId = route.params.appId
	let pageInfo = pageData.value

	if (route.query.page) {
		pageInfo.pageNo = parseInt(route.query.page)
	}

	findFciList(appId, keyword, pageInfo).then(response => {
		if (response && response.data && response.data.data) {
			fciListData.value = response.data.data
			pageInfo.total = response.data.total
		}
	})
}


// 处理页面切换
const handlePageChange = async (val) => {
	let appId = route.params.appId
	router.push({name: route.fciListRouteName, query: {appId: appId, page: val}})
		.then(() => {
			loadFciListData()
		})
}


// 组件实例编辑抽屉
const fciEditDrawerRef = ref()


// 处理FCI编辑
function handleEdit(row) {
	fciEditDrawerRef.value.openFciDrawerForEdit(row.id, row.appId)
}


// 处理FCM删除
function handleDelete(row) {
	delFci(row.id).then(response => {
		if (response && response.data) {
			ElMessage.success({
				message: '删除成功',
				duration: 1500,
			})
			loadFciListData()
		}
	})
}


// 组件属性管理抽屉
const fciPropsDrawerRef = ref()


// 打开属性管理窗口
function showProps(row) {
	fciPropsDrawerRef.value.openFciPropsDrawer(row)
}


// 页面渲染前执行一些必要的操作
onMounted(() => {
	// 加载枚举数据
	enumStore.refresh()
	// 加载页面数据
	loadFciListData()
})


function mapTypeEnum(row, column, cellValue, index) {
	return enumStore.getEnumDesc('FcmTypeEnum', row.type)
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