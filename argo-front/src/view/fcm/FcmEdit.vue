<template>
	<!--表单信息-->
	<el-form
		:model="fcmForm" ref="fcmFormRef" :rules="fcmFormRules"
		label-suffix=":" label-width="90px"
		class="fcm-form" status-icon>

		<el-form-item prop="fcmId">
			<el-input type="hidden" v-model="fcmForm.fcmId"/>
		</el-form-item>

		<el-form-item label="名称" prop="name">
			<el-input id="name" v-model="fcmForm.name"/>
		</el-form-item>

		<el-form-item label="类型" prop="type">
			<el-radio-group id="type" v-model="fcmForm.type">
				<el-radio v-for="e in fcmTypeEnum"
				          :value="e[0]">
					{{ e[1] }}
				</el-radio>
			</el-radio-group>
		</el-form-item>

		<el-form-item label="作用域" prop="scope">
			<el-radio-group id="scope" v-model="fcmForm.scope">
				<el-radio v-for="e in fcScopeEnum"
				          :value="e[0]">
					{{ e[1] }}
				</el-radio>
			</el-radio-group>
		</el-form-item>

		<el-form-item label="应用" v-if="2===fcmForm.scope" prop="appCode">
			<el-select id="appCode" v-model="fcmForm.appCode"
			           filterable remote :remote-method="fetchApps">
				<el-option v-for="e in appList"
				           :key="e.appCode"
				           :label="e.appName"
				           :value="e.appCode"/>
			</el-select>
		</el-form-item>

		<el-form-item label="绑定数据" prop="dataBindFlag">
			<el-switch id="dataBindFlag" v-model="fcmForm.dataBindFlag"
			           inline-prompt size="large"
			           active-text="是" active-value="1"
			           inactive-text="否" inactive-value="0"
			/>
		</el-form-item>

		<el-form-item label="图标" prop="icon">
			<el-input id="icon" v-model="fcmForm.icon"/>
		</el-form-item>

		<el-form-item label="备注" prop="remark">
			<el-input id="remark" type="textarea" v-model="fcmForm.remark" :autosize="{ minRows: 4,}"/>
		</el-form-item>

		<el-form-item>
			<el-button type="primary" :disabled="isFcmFormSubmitted" @click="submitFcmForm">提交</el-button>
		</el-form-item>
	</el-form>
</template>

<script setup>
import {ref, onMounted} from "vue";

import {loadEnums} from "@/api/common.js";
import {findAppList} from "@/api/app.js";
import {addFcm, editFcm} from "@/api/fcm.js";
import {submitForm} from "@/utils/common.js";


// fcm 表单数据
const fcmForm = ref({
	fcmId: '',
	name: '',
	icon: '',
	type: 1,
	scope: 1,
	appCode: '',
	dataBindFlag: '',
	remark: '',
})


// fcm 表单引用
const fcmFormRef = ref()


// fcm 表单验证规则
const fcmFormRules = {
	name: [
		{required: true, message: '请输入组件模型名称', trigger: 'blur'},
	],
	type: [
		{required: true, message: '请选择组件模型类型', trigger: 'blur'},
	],
	scope: [
		{required: true, message: '请选择组件模型作用域', trigger: 'blur'},
	],
	dataBindFlag: [
		{required: true, message: '绑定数据标记必填', trigger: 'blur'}
	]
}


// 枚举相关信息
const allEnumMap = ref()

const fcmTypeEnum = ref()

const fcScopeEnum = ref()

// 页面渲染前执行一些必要的操作
onMounted(() => {
	// 加载枚举数据
	loadEnums(allEnumMap, () => {
		fcmTypeEnum.value = allEnumMap.value.get('FcmTypeEnum')
		fcScopeEnum.value = allEnumMap.value.get('FcScopeEnum')
	})
})


// 获取应用
const appList = ref()

const fetchApps = async () => {
	findAppList().then(response => {
		if (response && response.data) {
			appList.value = response.data
		} else {
			appList.value = []
		}
	})
}


// 标记是否已经提交表单
const isFcmFormSubmitted = ref(false)


// 提交fcm编辑信息
function submitFcmForm() {
	const formData = {...fcmForm.value}
	let maintainMethod = addFcm

	if (formData.id) {
		maintainMethod = editFcm
	}

	submitForm(fcmFormRef, formData, isFcmFormSubmitted, maintainMethod)
}

</script>

<style scoped lang="less">

.fcm-form {
	width: 100%;
	max-width: 900px;
	padding: 30px;
}
</style>