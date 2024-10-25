<template>
	<!--表单信息-->
	<el-form
		:model="appForm" ref="appFormRef" :rules="appFormRules"
		label-suffix=":" label-width="90px"
		class="fcm-form" status-icon>

		<el-form-item prop="fcmId">
			<el-input type="hidden" v-model="appForm.id"/>
		</el-form-item>

		<el-form-item label="名称" prop="name">
			<el-input v-model="appForm.name"/>
		</el-form-item>

		<el-form-item label="类型" prop="type">
			<el-input v-model="appForm.appName"/>
		</el-form-item>

		<el-form-item label="作用域" prop="scope">
			<el-input v-model="appForm.icon"/>
		</el-form-item>

		<el-form-item label="绑定数据" prop="dataBindFlag">
			<el-input v-model="appForm.icon"/>
		</el-form-item>

		<el-form-item label="备注" prop="remark">
			<el-input type="textarea" v-model="appForm.remark" :autosize="{ minRows: 4,}"/>
		</el-form-item>

		<el-form-item>
			<el-button type="primary" @click="submitAppForm">提交</el-button>
		</el-form-item>
	</el-form>
</template>

<script setup>
import {ref, onMounted} from "vue";

import {loadEnums} from "@/api/common.js";


// app 表单数据
const fcmForm = ref({
	fcmId: '',
	name: '',
	icon: '',
	type: '',
	scope: '',
	appId: '',
	dataBindFlag: '',
	remark: '',
})


const enumMap = ref()

// 页面渲染前执行一些必要的操作
onMounted(() => {
	// 加载枚举数据
	loadEnums(enumMap)
})
</script>

<style scoped lang="less">

.fcm-form {
	width: 100%;
	max-width: 900px;
	padding: 30px;
}
</style>