<template>
	<!--表单信息-->
	<el-form
		:model="appForm" ref="appFormRef" :rules="appFormRules"
		label-suffix=":" label-width="90px"
		class="app-form" status-icon>

		<el-form-item prop="appId">
			<el-input type="hidden" v-model="appForm.id"/>
		</el-form-item>

		<el-form-item label="应用ID" prop="appCode">
			<el-input v-model="appForm.appCode"/>
		</el-form-item>

		<el-form-item label="应用名称" prop="appName">
			<el-input v-model="appForm.appName"/>
		</el-form-item>

		<el-form-item label="应用图标" prop="icon">
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
import {ref} from "vue";
import {addApp, editApp} from "@/api/app.js";
import {ElMessage} from "element-plus";

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

	maintainApp(maintainMethod, formData)
}


// 维护应用信息
const maintainApp = (maintainMethod, formData) => {
	appFormRef.value.validate((valid) => {
		if (!valid) return

		isAppFormSubmitted.value = true

		maintainMethod(formData, maintainMethod).then(response => {
			if (response.data) {
				ElMessage.success({
					message: '保存成功',
					duration: 1500,
				})
			}
		}).catch(() => {
			isAppFormSubmitted.value = false
		})
	})
}

</script>

<style scoped lang="less">
.app-form {
	width: 100%;
	max-width: 900px;
	padding: 30px;
}
</style>