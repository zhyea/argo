<template>
	<!--表单信息-->
	<el-form
		:model="fcmForm" ref="fcmFormRef" :rules="appFormRules"
		label-suffix=":" label-width="90px"
		class="fcm-form" status-icon>

		<el-form-item prop="fcmId">
			<el-input type="hidden" v-model="fcmForm.fcmId"/>
		</el-form-item>

		<el-form-item label="名称" prop="name">
			<el-input v-model="fcmForm.name"/>
		</el-form-item>

		<el-form-item label="类型" prop="type">
			<el-select v-model="fcmForm.type">
				<el-option loading=true
				           v-for="e of fcmTypeEnum"
				           :key="e[0]"
				           :label="e[1]"
				           :value="e[0]"
				/>
			</el-select>
		</el-form-item>

		<el-form-item label="作用域" prop="scope">
			<el-radio-group v-model="fcmForm.scope">
			<el-radio v-for="e in fcScopeEnum"
			          :value="e[0]">
				{{ e[1] }}
			</el-radio>
			</el-radio-group>
		</el-form-item>

		<el-form-item label="绑定数据" prop="dataBindFlag">
			<el-input v-model="fcmForm.icon"/>
		</el-form-item>

		<el-form-item label="备注" prop="remark">
			<el-input type="textarea" v-model="fcmForm.remark" :autosize="{ minRows: 4,}"/>
		</el-form-item>

		<el-form-item>
			<el-button type="primary" @click="submitFcmForm">提交</el-button>
		</el-form-item>
	</el-form>
</template>

<script setup>
import {ref, onMounted, onBeforeMount} from "vue";

import {loadEnums} from "@/api/common.js";


// fcm 表单数据
const fcmForm = ref({
	fcmId: '',
	name: '',
	icon: '',
	type: '',
	scope: '1',
	appId: '',
	dataBindFlag: '',
	remark: '',
})


// fcm 表单引用
const fcmFormRef = ref()


// fcm 表单验证规则
const appFormRules = {
	name: [
		{required: true, message: '请输入组件模型名称', trigger: 'blur'},
	],
	type: [
		{required: true, message: '请输入组件模型类型', trigger: 'blur'},
	],
}


// 枚举相关信息
const allEnumMap = ref()

const fcmTypeEnum = ref()

const fcScopeEnum = ref()

// 页面渲染前执行一些必要的操作
onBeforeMount(() => {
	// 加载枚举数据
	loadEnums(allEnumMap, () => {
		fcmTypeEnum.value = allEnumMap.value.get('FcmTypeEnum')
		fcScopeEnum.value = allEnumMap.value.get('FcScopeEnum')
	})
})


function submitFcmForm() {

}

</script>

<style scoped lang="less">

.fcm-form {
	width: 100%;
	max-width: 900px;
	padding: 30px;
}
</style>