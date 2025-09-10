<template>
	<el-drawer :title="`${propForm.id ? '编辑' : '新增'}实例属性${propForm.id? '-'+ propForm.propKey : '' }`"
	           v-model="fciPropEditDrawer" :with-header=true size="40%">
		<el-container>
			<!--表单信息-->
			<el-form status-icon
			         label-position="right"
			         label-width="100px"
			         label-suffix=":"
			         :model="propForm" ref="fciPropFormRef" :rules="fciPropRules" class="fci-form">

				<el-card class="fcm-region">
					<el-form-item prop="fciId">
						<el-input type="hidden" v-model="propForm.fciId"/>
					</el-form-item>

					<el-form-item label="属性Key" prop="propKey">
						<el-select placeholder="请选择属性" v-model="propForm.propKey"
						           :disabled="fixedPropDisabledFlag">
							<el-option v-for="e in fcmProps"
							           :key="e.propKey"
							           :label="e.propKey"
							           :value="e.propKey">
							</el-option>
						</el-select>
					</el-form-item>

					<el-form-item label="数据绑定" prop="dataBindFlag">
						<el-radio-group id="dataBindFlag" v-model="propForm.dataBindFlag">
							<el-radio :value="0"> 不绑定 </el-radio>
							<el-radio :value="1"> 绑定 </el-radio>
							<el-radio :value="2"> 继承实例 </el-radio>
						</el-radio-group>
					</el-form-item>

					<el-form-item label="属性Value" v-if="0===propForm.dataBindFlag" prop="propValue">
						<el-input id="propValue" v-model="propForm.propValue"
						          :disabled="expiredStatusFlag || effectiveStatusFlag"
						/>
					</el-form-item>

					<el-form-item label="数据链接" v-if="1===propForm.dataBindFlag" prop="dataUrl">
						<el-input id="name" v-model="propForm.dataUrl"
						          :disabled="expiredStatusFlag || effectiveStatusFlag"
						/>
					</el-form-item>

					<el-form-item label="请求method" v-if="1===propForm.dataBindFlag" prop="effectivePeriodType">
						<el-radio-group id="dataRequestMethod" v-model="propForm.dataRequestMethod"
						                :disabled="expiredStatusFlag || effectiveStatusFlag">
							<el-radio v-for="e in dataRequestMethodEnum"
							          :value="e[0]">
								{{ e[1] }}
							</el-radio>
						</el-radio-group>
					</el-form-item>

					<el-form-item label="请求参数" v-if="1===propForm.dataBindFlag" prop="dataRequestParams">
						<el-input id="name" type="textarea" v-model="propForm.dataRequestParams"
						          :disabled="expiredStatusFlag || effectiveStatusFlag"
						/>
					</el-form-item>

					<el-form-item label="请求headers" v-if="1===propForm.dataBindFlag" prop="dataRequestHeaders">
						<el-input id="name" v-model="propForm.dataRequestHeaders"
						          :disabled="expiredStatusFlag || effectiveStatusFlag"
						/>
					</el-form-item>

					<el-form-item label="值选择器" v-if="1===propForm.dataBindFlag || 2===propForm.dataBindFlag" prop="propValueSelector">
						<el-input id="propValue" v-model="propForm.propValueSelector"
						          :readonly="expiredStatusFlag || effectiveStatusFlag"
						/>
					</el-form-item>

					<el-form-item label="生效周期" prop="effectivePeriodType">
						<el-radio-group id="effectivePeriodType" v-model="propForm.effectivePeriodType"
						                :disabled="expiredStatusFlag || effectiveStatusFlag">
							<el-radio v-for="e in effectivePeriodTypeEnum"
							          :value="e[0]">
								{{ e[1] }}
							</el-radio>
						</el-radio-group>
					</el-form-item>

					<el-form-item label="生效时间" prop="effectiveTimeRange"
					              v-if="propForm.effectivePeriodType === 2">
						<el-date-picker
							v-model="propForm.effectiveStartTime"
							type="datetime"
							placeholder="请选择生效开始时间"
							value-format="YYYY-MM-DD HH:mm:ss"
							:readonly="expiredStatusFlag || effectiveStatusFlag"
							:disabled-date="timeRangeOpt.disabledStartTime"
						/>
						　到　
						<el-date-picker
							v-model="propForm.effectiveEndTime"
							type="datetime"
							placeholder="请选择生效结束时间"
							value-format="YYYY-MM-DD HH:mm:ss"
							:readonly="expiredStatusFlag"
							:disabled-date="timeRangeOpt.disabledEndTime"
						/>
					</el-form-item>

					<el-form-item label="备注" prop="remark">
						<el-input id="remark" type="textarea" v-model="propForm.remark" :autosize="{ minRows: 4,}"
						          :disabled="expiredStatusFlag"
						/>
					</el-form-item>
				</el-card>

				<el-card class="fcm-region">
					<el-button type="primary" :disabled="isPropFormSubmitted" @click="submitFciPropForm">提交
					</el-button>
				</el-card>
			</el-form>
		</el-container>
	</el-drawer>
</template>

<script lang="ts" setup>

import {ref, computed} from "vue";
import {submitForm} from "@/utils/helper";
import {addFciProp, editFciProp, getFciProp} from "@/api/fci";
import {useEnumStore} from "@/store/enum";
import {getFcm} from "@/api/fcm";
import {Ref} from "@vue/reactivity";

const enumStore = useEnumStore()

const fciPropEditDrawer = ref(false)

const propForm = ref({
	id: 0,
	fciId: 0,
	propKey: '',
	propValue: '',
	dataBindFlag: 0,
	dataUrl: '',
	dataRequestMethod: 0,
	dataRequestParams: '',
	dataRequestHeaders: '',
	propValueSelector: '',
	switchFlag: 1,
	effectivePeriodType: 1,
	effectiveStartTime: '',
	effectiveEndTime: '',
	status: 0,
	remark: '',
})

const fciPropFormRef = ref()

const fciPropRules = {
	propKey: [
		{required: true, message: '请输入组件名称', trigger: 'blur'},
	],
	propValue: [
		{required: true, message: '属性值不可为空', trigger: 'blur'},
	],
	dataUrl: [
		{required: true, message: '数据连接不可为空', trigger: 'blur'},
	],
	dataRequestMethod: [
		{required: true, message: '数据请求method不可为空', trigger: 'blur'},
	],
	propValueSelector: [
		{required: true, message: '属性值选择器不可为空', trigger: 'blur'},
	],
	effectivePeriodType: [
		{required: true, message: '请输入返回值', trigger: 'blur'},
	],
	effectiveStartTime: [
		{required: true, message: '生效开始时间不可为空', trigger: 'blur'},
	],
	effectiveEndTime: [
		{required: true, message: '生效结束时间不可为空', trigger: 'blur'},
	],
};


const timeRangeOpt = {
	disabledStartTime: (time: Date) => {
		const minTime = new Date().setHours(0, 0, 0, 0)
		if (propForm.value.effectiveEndTime) {
			const maxTime = new Date(propForm.value.effectiveEndTime).getTime()
			return !(time.getTime() <= maxTime && time.getTime() >= minTime)
		}
		return !(time.getTime() >= minTime)
	},

	disabledEndTime: (time: Date) => {
		let todayEarliest = new Date().setHours(0, 0, 0, 0)
		if (propForm.value.effectiveStartTime) {
			const t = new Date(propForm.value.effectiveStartTime).getTime()
			return !(time.getTime() >= Math.max(todayEarliest, t))
		} else {
			return !(time.getTime() > todayEarliest)
		}
	}
}


const fixedPropDisabledFlag: Ref<boolean> = ref(false)
const expiredStatusFlag: Ref<boolean> = ref(false)
const effectiveStatusFlag: Ref<boolean> = ref(false)
const pendingStatusFlag: Ref<boolean> = ref(false)


// 加载组件实例数据
async function loadFciPropData(propId: number) {
	if (!propId) {
		return
	}

	const response = await getFciProp(propId);
	if (response && response.data) {
		const propData = response.data;
		propForm.value = propData;

		expiredStatusFlag.value = propData.status === 1
		effectiveStatusFlag.value = propData.status === 2
		pendingStatusFlag.value = propData.status === 3
	}
}


const isPropFormSubmitted = ref(false)


// 枚举相关信息
const effectivePeriodTypeEnum = computed(() => {
	return enumStore.getEnumMap('EffectivePeriodTypeEnum');
})

const dataRequestMethodEnum = computed(()=>{
	return enumStore.getEnumMap('HttpQueryMethodEnum');
})


// 打开组件实例抽屉前的准备
function openPrepare() {
	fciPropEditDrawer.value = true

	if (fciPropFormRef.value) {
		fciPropFormRef.value.resetFields();
		fciPropFormRef.value = {};
	}

	isPropFormSubmitted.value = false;
	fixedPropDisabledFlag.value = false;

	expiredStatusFlag.value = false;
	effectiveStatusFlag.value = false;
	pendingStatusFlag.value = false;
}


// 打开组件实例抽屉-用于编辑
async function openDrawerForEdit(fciId: number) {
	openPrepare();
	await loadFciPropData(fciId);

	if (propForm.value.propKey) {
		fixedPropDisabledFlag.value = true
	}
}


// 打开组件实例抽屉-用于新增
function openDrawerForAdd(fci: any) {
	openPrepare()
	loadFcmProps(fci.fcmId)
	propForm.value.fciId = fci.id
}


const emit = defineEmits(['afterPropEdit'])


// 提交组件实例表单
function submitFciPropForm() {
	if (!fciPropFormRef.value.validate()) {
		return;
	}

	const formData = {...propForm.value}
	const maintainMethod = formData.id ? editFciProp : addFciProp;

	submitForm(fciPropFormRef, formData, isPropFormSubmitted, maintainMethod, () => {
		emit('afterPropEdit');
		fciPropEditDrawer.value = false
	})
}


const fcmProps: Ref<any[]> = ref([])

function loadFcmProps(fcmId: number) {
	getFcm(fcmId).then(response => {
		if (response && response.data) {
			fcmProps.value = response.data.props;
		}
	})
}


defineExpose({
	openPropDrawerForEdit: openDrawerForEdit,
	openPropDrawerForAdd: openDrawerForAdd,
})

</script>

<style scoped lang="less">
.fci-form {
	width: 100%;
}
</style>