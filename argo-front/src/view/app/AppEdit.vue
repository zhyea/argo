<template>
	<!--表单信息-->
	<el-form
		:model="appForm" ref="appFormRef" :rules="appFormRules"
		label-suffix=":" label-width="90px"
		class="fcm-form" status-icon>

		<el-form-item prop="appId">
			<el-input type="hidden" v-model="appForm.id"/>
		</el-form-item>

		<el-form-item label="应用ID" prop="appCode">
			<el-input id="appCode" v-model="appForm.appCode"/>
		</el-form-item>

		<el-form-item label="应用名称" prop="appName">
			<el-input id="appName" v-model="appForm.appName"/>
		</el-form-item>

		<el-form-item label="应用图标" prop="icon">
			<el-input id="icon" v-model="appForm.icon"/>
		</el-form-item>

		<el-form-item label="备注" prop="remark">
			<el-input id="remark" type="textarea" v-model="appForm.remark" :autosize="{ minRows: 4,}"/>
		</el-form-item>

		<el-form-item>
			<el-button type="primary" :disabled="isAppFormSubmitted" @click="submitAppForm">提交</el-button>
		</el-form-item>
	</el-form>
</template>

<script setup>
import {ref} from "vue";
import {addApp, editApp} from "@/api/app.js";
import {submitForm} from "@/utils/common.js";

// app 表单数据
const appForm = ref({
	appId: '',
	appCode: '',
	appName: '',
	icon: '',
	remark: '',
})


// app 表单引用
const appFormRef = ref()


// app 表单验证规则
const appFormRules = {
	appCode: [
		{required: true, message: '请输入应用ID', trigger: 'blur'},
	],
	appName: [
		{required: true, message: '请输入应用名称', trigger: 'blur'},
	],
}


// 标记是否已经提交表单
const isAppFormSubmitted = ref(false)


// 提交app编辑信息
function submitAppForm() {
	const formData = {...appForm.value}
	let maintainMethod = addApp

	if (formData.id) {
		maintainMethod = editApp
	}

	submitForm(appFormRef, formData, isAppFormSubmitted, maintainMethod)
}


</script>

<style scoped lang="less">
.fcm-form {
	width: 100%;
	max-width: 900px;
	padding: 30px;
}
</style>