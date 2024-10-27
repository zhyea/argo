<template>
	<el-container class="fcm-container">
		<!--表单信息-->
		<el-form
			:model="fcmForm" ref="fcmFormRef" :rules="fcmFormRules"
			label-suffix=":" label-width="90px"
			class="fcm-form" status-icon>

			<el-card header="组件模型基础信息" class="fcm-region">

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
					           placeholder="请选择应用"
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
			</el-card>


			<el-card header="组件模型属性信息" class="fcm-region">

				<el-form :model="fcmPropForm" ref="fcmPropFormRef" :rules="fcmPropFormRules">
					<table class="fcm-prop-table">
						<tbody>
						<tr>
							<td class="fcm-prop-td-key">属性key</td>
							<td class="fcm-prop-td-type">类型</td>
							<td class="fcm-prop-td-required">必填</td>
							<td class="fcm-prop-td-desc">描述</td>
							<td class="fcm-prop-td-opt"></td>
						</tr>
						<tr v-for="(e, idx) in fcmPropForm"
						    :key="idx">
							<td class="fcm-prop-td-key">
								<el-form-item prop="propKey">
									<el-input v-model="e.propKey"/>
								</el-form-item>
							</td>
							<td class="fcm-prop-td-type">
								<el-form-item prop="propType">
									<el-select v-model="e.propType"
									           placeholder="请选择属性类型">
										<el-option v-for="e in fcmPropTypeEnum"
										           :key="e[0]"
										           :label="e[1]"
										           :value="e[0]"/>
									</el-select>
								</el-form-item>
							</td>
							<td class="fcm-prop-td-required">
								<el-form-item>
									<el-switch v-model="e.required" inline-prompt
									           active-text="是" active-value=1
									           inactive-text="否" inactive-value=0
									/>
								</el-form-item>
							</td>
							<td class="fcm-prop-td-desc">
								<el-form-item >
									<el-input v-model="e.propDesc"/>
								</el-form-item>
							</td>
							<td class="fcm-prop-td-opt">
								<el-icon v-if="idx === fcmPropForm.length - 1"
								         :size="30" @click="addFcmProp">
									<CirclePlus/>
								</el-icon>

								<el-icon v-else
								         :size="30" @click="removeFcmProp(idx)">
									<Remove/>
								</el-icon>
							</td>
						</tr>
						</tbody>
					</table>
				</el-form>
			</el-card>

			<el-card class="fcm-region">
				<el-button type="primary" :disabled="isFcmFormSubmitted" @click="submitFcmForm">提交</el-button>
			</el-card>
		</el-form>

	</el-container>
</template>

<script setup>
import {ref, onMounted} from "vue";

import {loadEnums} from "@/api/common.js";
import {findAppList} from "@/api/app.js";
import {addFcm, editFcm} from "@/api/fcm.js";
import {submitForm} from "@/utils/common.js";
import {CirclePlus, Remove} from "@element-plus/icons-vue";


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
	props: [],
})


// fcm 属性表单数据
const fcmPropForm = ref([{
	propKey: '',
	propType: '',
	propDesc: '',
	required: 0,
}])


// fcm 表单引用
const fcmFormRef = ref()


// fcm属性 表单引用
const fcmPropFormRef = ref()


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
	],
	appCode: [
		{
			validator: (rule, value, callback) => {
				if (2 === fcmForm.value.scope && !value) {
					return false
				}
				return true;
			}, message: '请选择应用', trigger: 'blur'
		}
	],
}


// fcm属性 表单验证规则
const fcmPropFormRules = {
	propKey: [
		{required: true, message: '请输入模型属性key', trigger: 'change'},
	],
	propType: [
		{required: true, message: '请选择模型属性类型', trigger: 'change'},
	],
	propRequired: [
		{required: true, message: '请选择模型是否必填', trigger: 'change'},
	],
}


// 枚举相关信息
const allEnumMap = ref()

const fcmTypeEnum = ref()

const fcScopeEnum = ref()

const fcmPropTypeEnum = ref()

// 页面渲染前执行一些必要的操作
onMounted(() => {
	// 加载枚举数据
	loadEnums(allEnumMap, () => {
		fcmTypeEnum.value = allEnumMap.value.get('FcmTypeEnum')
		fcScopeEnum.value = allEnumMap.value.get('FcScopeEnum')
		fcmPropTypeEnum.value = allEnumMap.value.get('FcmPropTypeEnum')
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


// 新增fcm属性
function addFcmProp() {
	fcmPropForm.value.push({})
}

// 删除fcm属性
function removeFcmProp(idx) {
	fcmPropForm.value.splice(idx, 1)
}

// 标记是否已经提交表单
const isFcmFormSubmitted = ref(false)


// 提交fcm编辑信息
function submitFcmForm() {

	fcmPropFormRef.value.validate((valid) => {
		if (!valid) {
			return
		}

		const formData = {...fcmForm.value}
		formData.props = fcmPropForm.value

		let maintainMethod = addFcm

		if (formData.id) {
			maintainMethod = editFcm
		}

		submitForm(fcmFormRef, formData, isFcmFormSubmitted, maintainMethod)
	})

}

</script>

<style scoped lang="less">

.fcm-form {
	width: 100%;
	padding: 30px;
}


.fcm-region {
	margin-bottom: 20px;
	box-shadow: none;
}


.fcm-prop-table {
	width: 100%;

	td {
		padding-bottom: 12px;
	}
}

.fcm-prop-td-key {
	width: 30%;
}

.fcm-prop-td-type {
	padding-left: 12px;
	width: 60px;
}

.fcm-prop-td-required {
	width: 20px;
	text-align: center;
	vertical-align: middle;

	.el-switch {
		margin: 0 auto;
	}
}

.fcm-prop-td-desc {
	width: 40%;
}

.fcm-prop-td-opt {
	width: 10px;
	padding-bottom: 12px;
	text-align: center;

	.el-icon {
		padding-bottom: 18px;
	}
}
</style>