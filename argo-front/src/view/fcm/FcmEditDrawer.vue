<template>
	<el-drawer :title="`${fcmForm.fcmId ? '编辑' : '新增'}${fcmForm.name}`"
	           v-model="fcmEditDrawer" :with-header=true size="70%">
		<!--表单信息-->
		<el-form
			:model="fcmForm" ref="fcmFormRef" :rules="fcmFormRules"
			label-suffix=":" label-width="90px"
			class="fcm-form" status-icon>

			<el-card header="组件模型基础信息">

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

				<el-form-item label="作用域" prop="scope" v-if="!defaultAppFcmFlag">
					<el-radio-group id="scope" v-model="fcmForm.scope">
						<el-radio v-for="e in fcScopeEnum"
						          :value="e[0]">
							{{ e[1] }}
						</el-radio>
					</el-radio-group>
				</el-form-item>

				<el-form-item label="应用" v-if="2===fcmForm.scope && !defaultAppFcmFlag" prop="appId">
					<el-select id="appId" v-model.lazy="fcmForm.id"
					           placeholder="请选择应用"
					           remote-show-suffix
					           filterable remote :remote-method="fetchApps">
						<el-option v-for="e in appList"
						           :key="e.id"
						           :label="e.appName"
						           :value="e.id"/>
					</el-select>
				</el-form-item>

				<el-form-item label="绑定数据" prop="dataBindFlag">
					<el-switch id="dataBindFlag" v-model="fcmForm.dataBindFlag"
					           inline-prompt size="large"
					           active-text="是" :active-value="1"
					           inactive-text="否" :inactive-value="0"
					/>
				</el-form-item>

				<el-form-item label="图标" prop="icon">
					<el-input id="icon" v-model="fcmForm.icon"/>
				</el-form-item>

				<el-form-item label="备注" prop="remark">
					<el-input id="remark" type="textarea" v-model="fcmForm.remark" :autosize="{ minRows: 4,}"/>
				</el-form-item>
			</el-card>


			<el-card header="组件模型属性信息">

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
						<tr v-for="(e, idx) in fcmPropForm.props"
						    :key="idx">
							<td class="fcm-prop-td-key">
								<el-form-item :prop="`props.${idx}.propKey`" :rules="fcmPropFormRules.propKey">
									<el-input v-model="e.propKey"/>
								</el-form-item>
							</td>
							<td class="fcm-prop-td-type">
								<el-form-item :prop="`props.${idx}.propType`" :rules="fcmPropFormRules.propType">
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
									<el-switch v-model.number="e.required" inline-prompt
									           active-text="是" :active-value='1'
									           inactive-text="否" :inactive-value='0'
									/>
								</el-form-item>
							</td>
							<td class="fcm-prop-td-desc">
								<el-form-item>
									<el-input v-model="e.propDesc"/>
								</el-form-item>
							</td>
							<td class="fcm-prop-td-opt">
								<el-icon v-if="idx === fcmPropForm.props.length - 1"
								         :size="30" @click="addFcmProp">
									<CirclePlus class="fcm-prop-td-opt-icon"/>
								</el-icon>

								<el-icon v-else :size="30" @click="removeFcmProp(idx)">
									<Remove class="fcm-prop-td-opt-icon"/>
								</el-icon>
							</td>
						</tr>
						</tbody>
					</table>
				</el-form>
			</el-card>

			<el-card>
				<el-button type="primary" :disabled="isFcmFormSubmitted" @click="submitFcmForm()">提交</el-button>
			</el-card>
		</el-form>
	</el-drawer>
</template>

<script lang="ts" setup>
import {ref} from "vue";

import {queryApps} from "@/api/app";
import {addFcm, editFcm, getFcm} from "@/api/fcm";
import {submitForm} from "@/view/helper";
import {CirclePlus, Remove} from "@element-plus/icons-vue";
import {useEnumStore} from "@/store/enum";


const enumStore = useEnumStore()

const fcmEditDrawer = ref(false)


// fcm 表单数据
const fcmForm = ref({
	fcmId: null,
	name: '',
	icon: '',
	type: 1,
	scope: 1,
	id: '',
	dataBindFlag: '',
	remark: '',
	props: [],
})


// fcm 属性表单数据
const fcmPropForm = ref({
	props: [{
		propKey: '',
		propType: '',
		propDesc: '',
		required: 0,
	}]
})


// fcm 表单引用
const fcmFormRef = ref()


// fcm属性 表单引用
const fcmPropFormRef = ref()


// fcm 表单验证规则
const fcmFormRules = {
	name: [
		{required: true, message: '请输入组件模型名称', trigger: 'blur'},
		{min: 1, max: 32, message: '模型属性key长度为1-32', trigger: 'change'}
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
				return !(2 === fcmForm.value.scope && !value);
			}, message: '请选择应用', trigger: 'blur'
		}
	],
}


// fcm属性 表单验证规则
const fcmPropFormRules = {
	propKey: [
		{required: true, message: '请输入模型属性key', trigger: 'change'},
		{min: 1, max: 32, message: '模型属性key长度为1-32', trigger: 'change'}
	],
	propType: [
		{required: true, message: '请选择模型属性类型', trigger: 'change'},
	],
	propRequired: [
		{required: true, message: '请选择模型是否必填', trigger: 'change'},
	],
}


// 枚举相关信息
const fcmTypeEnum = computed(()=>{
	return enumStore.getEnumMap('FcmTypeEnum');
})
const fcScopeEnum = computed(()=>{
	return enumStore.getEnumMap('FcScopeEnum');
})
const fcmPropTypeEnum = computed(()=>{
	return enumStore.getEnumMap('FcmPropTypeEnum');
})

// 标记组件模型的作用域是否默认是应用内，用于在应用页新增编辑组件模型
const defaultAppFcmFlag = ref(false)

// 应用信息
const appList = ref()

// 加载组件模型数据
const loadFcmData = async (fcmId) => {
	if (!fcmId) {
		return
	}
	getFcm(fcmId).then(response => {
		if (response && response.data) {
			fcmForm.value = response.data
			fcmPropForm.value.props = response.data.props
			appList.value = [{
				id: response.data.appId,
				appName: response.data.appName,
			}]
		}
	})
}

// 加载应用
function fetchApps(keyword) {
	queryApps(keyword).then(response => {
		if (response && response.data) {
			appList.value = response.data
		} else {
			appList.value = []
		}
	})
}


// 打开组件模型编辑抽屉
const openFcmEditDrawer = (fcmId, appId) => {
	if (fcmFormRef.value) {
		fcmFormRef.value.resetFields()
	}
	if (fcmPropFormRef.value) {
		fcmPropFormRef.value.resetFields()
	}

	fcmEditDrawer.value = true
	fcmForm.value = {
		id: appId,
	}
	isFcmFormSubmitted.value = false

	defaultAppFcmFlag.value = (null != appId)

	// 加载组件模型数据
	if (fcmId) {
		loadFcmData(fcmId)
	}
}


defineExpose({openFcmEditDrawer})


// 新增fcm属性
function addFcmProp() {
	fcmPropForm.value.props.push({})
}

// 删除fcm属性
function removeFcmProp(idx) {
	fcmPropForm.value.props.splice(idx, 1)
}

// 标记是否已经提交表单
const isFcmFormSubmitted = ref(false)


const emit = defineEmits(['afterFcmAdd'])

// 提交fcm编辑信息
const submitFcmForm = async () => {

	const valid = await Promise.all([fcmFormRef.value.validate(), fcmPropFormRef.value.validate()])
		.catch(err => {
			console.log(err)
		})

	if (!valid) {
		return
	}

	const formData = {...fcmForm.value}
	formData.props = fcmPropForm.value.props

	/*if (defaultAppFcmFlag.value) {
		formData.appId = route.params.appId
		formData.appName = 2
	}*/

	const maintainMethod = (formData.fcmId) ? editFcm : addFcm;

	submitForm(fcmFormRef, formData, isFcmFormSubmitted, maintainMethod, () => {
		emit('afterFcmAdd');
		fcmEditDrawer.value = false
	})
}
</script>

<style scoped lang="less">

.fcm-form {
	width: 100%;
	padding: 10px;
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

	.fcm-prop-td-opt-icon {
		cursor: pointer !important;
	}
}
</style>