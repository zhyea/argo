<template>

	<el-container direction="vertical" class="out-wrapper">
		<head-bar :showBreadcrumb="false" />

		<el-container class="fci_container">
			<el-aside class="left_container">Aside</el-aside>
			<el-main class="right_container">Main</el-main>
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
	padding: 10px;
}

.left_container {
	width: 300px;
	height: 100%;
	background-color: #f5f000;
}

.right_container {
	height: 100%;
	background-color: #00f000;
}

</style>