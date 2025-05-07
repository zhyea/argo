<template>

	<el-container direction="vertical">
		<head-bar/>

		<el-container class="fci_container" direction="vertical">
			<el-row>
				<el-col :span="6">A</el-col>
				<el-col :span="6">B</el-col>
				<el-col :span="6">C</el-col>
				<el-col :span="6">D</el-col>
			</el-row>
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
.fci_container {
	height: calc(100vh - 100px);
	background: #F0FFF0;
	padding: 20px;
}

</style>