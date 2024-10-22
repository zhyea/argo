<template>
	<!--表单信息-->
	<el-form
	         :model="appForm" ref="appFormRef" :rules="appFormRules"
	         label-suffix=":" label-width="90px"
	         class="app-form" status-icon >

		<el-form-item label="应用ID" prop="appId">
			<el-input v-model="appForm.appId"/>
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
			<el-button type="primary" @click="onSubmit">提交</el-button>
		</el-form-item>
	</el-form>
</template>

<script setup>
import {ref} from "vue";

// app 表单数据
const appForm = ref({
	appCode: '',
	appName: '',
	icon: '',
	remark: '',
})


// app 表单引用
const appFormRef = ref({})


// app 表单验证规则
const appFormRules = {
	appId: [
		{required: true, message: '请输入应用ID', trigger: 'blur'},
	],
	appName: [
		{required: true, message: '请输入应用名称', trigger: 'blur'},
	],
}


// 提交app编辑信息
function onSubmit() {

}

</script>

<style scoped lang="less">
.app-form {
	width: 100%;
	max-width: 900px;
	padding: 30px;
}
</style>