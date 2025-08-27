<template>
	<el-drawer :title="`${fci.name}-属性`"
	           v-model="fciPropsDrawer" :with-header=true size="60%">
		<div class="table-container">
			<div class="table-header">
				<el-form :model="propsSearchForm" label-width="60px" label-suffix=":" :inline="true"
				         @submit.native.prevent>
					<el-form-item label="关键字">
						<el-input v-model="propsSearchForm.keyword" @keyup.enter.native="loadFciPropsData"/>
					</el-form-item>
					<el-form-item>
						<el-button type="primary" @click="loadFciPropsData">查询</el-button>
					</el-form-item>
				</el-form>
			</div>

			<div class="table-body">
				<div class="table-button">
					<el-button type="primary" @click="showPropAddDrawer">新增属性</el-button>
				</div>

				<el-table :data="fciPropsData" border stripe style="width: 100%">
					<el-table-column show-overflow-tooltip min-width=120 prop="propKey" label="属性key"/>
					<el-table-column width=55 prop="dataBindFlag" label="绑定数据"
					                 :formatter="mapDataBindFlag" align="center"/>

					<el-table-column prop="switchFlag" label="是否启用" width="80" align="center">
						<template #default="scope">
							<el-switch id="switchFlag" :model-value="scope.row.switchFlag" inline-prompt
							           @update:modelValue="switchProp(scope.row, val)"
							           active-text="是" :active-value="1"
							           inactive-text="否" :inactive-value="0"/>
						</template>
					</el-table-column>
					<el-table-column show-overflow-tooltip min-width=350 prop="type" label="生效周期" align="center"
					                 :formatter="formatEffectivePeriod"/>
					<el-table-column width=80 prop="statusDesc" label="状态" align="center"/>
					<el-table-column show-overflow-tooltip min-width=166 prop="createTime" label="创建时间"
					                 align="center"/>
					<el-table-column show-overflow-tooltip min-width=166 prop="updateTime" label="修改时间"
					                 align="center"/>
					<el-table-column label="操作" align="center" fixed="right" width=150>
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

		<props-edit-drawer ref="fciPropEditDrawerRef"/>
	</el-drawer>
</template>

<script lang="ts" setup>

import {ref} from "vue";
import {loadEnums} from "@/api/common";
import {findFciProps} from "@/api/fci";
import {useRouter} from "vue-router";
import {formatEffectivePeriod, mapDataBindFlag} from "@/utils/helper";
import PropsEditDrawer from "@/view/fci/FciPropEditDrawer.vue";

const fciPropsDrawer = ref(false)

const fci = ref({
	id: 0,
	name: '',
	fciCode: 0,
	appId: 0,
	fcmId: 0,
	fcmName: '',
	type: 0,
	dataBindFlag: 0,
	dataUrl: '',
	switchFlag: 1,
	effectivePeriodType: 1,
	effectiveStartTime: '',
	effectiveEndTime: '',
	remark: '',
	createTime: '',
	updateTime: '',
})


// 枚举相关信息
const allEnumMap = ref()
const effectivePeriodTypeEnum = ref()


// 打开组件实例抽屉前的准备
const openPrepare = () => {
	fciPropsDrawer.value = true
	// 加载枚举数据
	loadEnums(allEnumMap, () => {
		effectivePeriodTypeEnum.value = allEnumMap.value.get('EffectivePeriodTypeEnum')
	})
}


// 打开组件属性抽屉
const openFciPropsDrawer = (fciRecord: any) => {
	fci.value = fciRecord
	openPrepare()
	loadFciPropsData()
}


defineExpose({
	openFciPropsDrawer,
})

// --------------------------- 添加按钮点击处理
// 组件属性管理抽屉
const fciPropEditDrawerRef = ref()


// 打开属性管理窗口
function showPropAddDrawer() {
	fciPropEditDrawerRef.value.openPropDrawerForAdd(fci.value)
}


// --------------------------- 以下是列表数据

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
const propsSearchForm = ref({
	// 关键字
	keyword: ''
})


// FCM列表数据
const fciPropsData = ref([])

// 加载方法列表数据
function loadFciPropsData() {
	let keyword = propsSearchForm.value.keyword
	let pageInfo = pageData.value


	findFciProps(fci.value.id, keyword, pageInfo).then(response => {
		if (response && response.data && response.data.data) {
			fciPropsData.value = response.data.data
			pageInfo.total = response.data.total
		}
	})
}


// 处理页面切换
const handlePageChange = async (val: any) => {
	// const appId = route.params['appId']
	// router.push({name: route['fciListRouteName'], query: {appId: appId, page: val}})
	// 	.then(() => {
	// 		loadFciListData()
	// 	})
}


function handleEdit(row: any) {
	fciPropEditDrawerRef.value.openPropDrawerForEdit(row.id)
}


function handleDelete(row: any) {
	console.log('handleDelete', row)
}

</script>

<style scoped lang="less">
.fci-form {
	width: 100%;
}
</style>