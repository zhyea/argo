<template>
	<div class="table-container">

		<div class="table-header">
			<el-form :model="keywordForm" label-width="60px" label-suffix=":" :inline="true"
			         @submit.native.prevent
			>
				<el-form-item label="关键字">
					<el-input v-model="keywordForm.keyword" @keyup.enter.native="queryMethods"/>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="queryMethods">查询</el-button>
				</el-form-item>
			</el-form>
		</div>

		<div class="table-body">
			<div class="table-button">
				<el-button type="primary">暂时留着</el-button>
			</div>

			<el-table :data="methodListData" border style="width: 100%">
				<el-table-column type="index" width="50"/>
				<el-table-column show-overflow-tooltip min-width=270 prop="typeName" label="类名"/>
				<el-table-column show-overflow-tooltip min-width=120 prop="methodName" label="方法名"/>
				<el-table-column show-overflow-tooltip min-width=180 prop="methodAlias" label="方法描述"/>
				<el-table-column show-overflow-tooltip min-width=270 prop="responseType" label="返回类型"/>
				<el-table-column show-overflow-tooltip width=180 align="center" prop="lastRequestTime"
				                 label="上次调用时间"/>
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

	<method-item @after-method-modify="loadMethodListData" ref="methodDrawerRef"/>
</template>

<script setup>
import {ref} from 'vue'

// 搜索数据
const keywordForm = ref({
	// 关键字
	keyword: ''
})

</script>

<style scoped lang="less">

</style>