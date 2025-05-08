<template>

	<el-container direction="vertical" class="out-wrapper">
		<head-bar :showBreadcrumb="false"/>

		<el-container class="fci_container">
			<el-aside class="left_container">
				<el-row class="item-row">
					<el-col :span="6" class="item-label">组件名称</el-col>
					<el-col :span="18" class="item-value">{{name}}</el-col>
				</el-row>
				<el-row class="item-row">
					<el-col :span="6" class="item-label">组件代码</el-col>
					<el-col :span="18" class="item-value">{{fciCode}}</el-col>
				</el-row>
				<el-row class="item-row">
					<el-col :span="6" class="item-label">模型名称</el-col>
					<el-col :span="18" class="item-value">{{fcmName}}</el-col>
				</el-row>
				<el-row class="item-row">
					<el-col :span="6" class="item-label">组件类型</el-col>
					<el-col :span="18" class="item-value">{{typeName}}</el-col>
				</el-row>
				<el-row class="item-row">
					<el-col :span="6" class="item-label">是否绑定数据</el-col>
					<el-col :span="18" class="item-value">{{dataBindFlag}}</el-col>
				</el-row>
				<el-row class="item-row" v-if="dataBindFlag">
					<el-col :span="6" class="item-label">数据连接</el-col>
					<el-col :span="18" class="item-value">{{dataUrl}}</el-col>
				</el-row>
				<el-row class="item-row">
					<el-col :span="6" class="item-label">生效周期</el-col>
					<el-col :span="18" class="item-value">{{effectivePeriod}}</el-col>
				</el-row>
				<el-row class="item-row">
					<el-col :span="6" class="item-label">备注</el-col>
					<el-col :span="18" class="item-value">{{remark}}</el-col>
				</el-row>
			</el-aside>

			<el-main class="right_container">

			</el-main>
		</el-container>

	</el-container>
</template>

<script setup>
import {ref, onMounted} from 'vue'
import {useRoute, useRouter} from "vue-router";
import {loadEnums} from "@/api/common.js";
import {config} from "@/config/index.js";
import {ElMessage} from "element-plus";
import FciDrawer from "@/view/fci/FciEditDrawer.vue";
import {delFci, findFciList} from "@/api/fci.js";
import Sidebar from "@/component/layout/SideBar.vue";
import HeadBar from "@/component/layout/HeadBar.vue";


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


</script>

<style scoped lang="less">
.out-wrapper {
	margin: 0;
	padding: 0;
	height: 100%;
}

.fci_container {
	height: 100%;
	padding: 0;
}

.left_container {
	width: 420px;
	height: 100%;
	background-color: #f5f000;
}

.right_container {
	height: 100%;
	background-color: #00f000;
}

</style>