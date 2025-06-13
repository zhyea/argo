<template>
	<el-drawer :title="`${propForm.id ? '编辑' : '新增'}组件实例 - ${propForm.name}`"
	           v-model="fciPropEditDrawer" :with-header=true size="40%">
		<el-container>
			<!--表单信息-->
			<el-form status-icon
			         label-position="right"
			         label-width="100px"
			         label-suffix=":"
			         :model="propForm" ref="fciPropFormRef" :rules="fciFormRules" class="fci-form">

				<el-card class="fcm-region">
					<el-form-item prop="fciId">
						<el-input type="hidden" v-model="propForm.fciId"/>
					</el-form-item>

					<el-form-item label="Key" prop="propKey">
						<el-input id="propKey" v-model="propForm.propKey"/>
					</el-form-item>

					<el-form-item label="数据绑定" prop="dataBindFlag">
						<el-switch id="dataBindFlag" v-model="propForm.dataBindFlag"
						           inline-prompt size="large"
						           active-text="是" :active-value="1"
						           inactive-text="否" :inactive-value="0"/>
					</el-form-item>

					<el-form-item label="值" v-if="0===propForm.dataBindFlag" prop="propValue">
						<el-input id="propValue" v-model="propForm.propValue"/>
					</el-form-item>

					<el-form-item label="值选择器" v-if="1===propForm.dataBindFlag" prop="propValueSelector">
						<el-input id="propValue" v-model="propForm.propValue"/>
					</el-form-item>

					<el-form-item label="数据链接" v-if="1===propForm.dataBindFlag" prop="dataUrl">
						<el-input id="name" v-model="propForm.dataUrl"/>
					</el-form-item>

					<el-form-item label="是否启用" prop="switchFlag">
						<el-switch id="dataBindFlag" v-model="propForm.dataBindFlag"
						           inline-prompt size="large"
						           active-text="是" :active-value="1"
						           inactive-text="否" :inactive-value="0"/>
					</el-form-item>

					<el-form-item label="生效周期" prop="effectivePeriodType">
						<el-radio-group id="effectivePeriodType" v-model="propForm.effectivePeriodType">
							<el-radio v-for="e in effectivePeriodTypeEnum"
							          :value="e[0]">
								{{ e[1] }}
							</el-radio>
						</el-radio-group>
					</el-form-item>

					<el-form-item label="生效时间" prop="effectiveTimeRange"
					              v-if="propForm.effectivePeriodType === 2">
						<el-date-picker
							v-model="propForm.effectiveTimeRange"
							type="datetimerange"
							range-separator="到"
							start-placeholder="请选择生效开始时间"
							end-placeholder="请选择生效结束时间"
							value-format="YYYY-MM-DD HH:mm:ss"/>
					</el-form-item>

					<el-form-item label="备注" prop="remark">
						<el-input id="remark" type="textarea" v-model="propForm.remark" :autosize="{ minRows: 4,}"/>
					</el-form-item>
				</el-card>

				<el-card class="fcm-region">
					<el-button type="primary" :disabled="isFciPropFormSubmitted" @click="submitFciPropForm">提交</el-button>
				</el-card>
			</el-form>
		</el-container>
	</el-drawer>
</template>

<script setup>

import {ref} from "vue";
import {submitForm} from "@/view/helper";
import {addFci, editFci, getFci} from "@/api/fci";
import {useEnumStore} from "@/store/enum";

const enumStore = useEnumStore()

const fciPropEditDrawer = ref(false)

const propForm = ref({
	id: 0,
	fciId: 0,
	propKey: '',
	propValue: '',
	dataBindFlag: 0,
	dataUrl: '',
	propValueSelector: '',
	switchFlag: 1,
	effectivePeriodType: 1,
	effectiveStartTime: '',
	effectiveEndTime: '',
	status: 0,
	remark: '',
})

const fciPropFormRef = ref()

const fciFormRules = {
	name: [
		{required: true, message: '请输入组件名称', trigger: 'blur'},
	],
	response: [
		{required: true, message: '请输入返回值', trigger: 'blur'},
	],
};


// 加载组件实例数据
const loadFciPropData = async (propId) => {
	if (!propId) {
		return
	}

	getFci(propId).then(response => {
		if (response && response.data) {
			const fciData = response.data;
			propForm.value = fciData;
			propForm.value.effectiveTimeRange = [fciData.effectiveStartTime, fciData.effectiveEndTime];
		}
	})
}


const isFciPropFormSubmitted = ref(false)


// 枚举相关信息
const effectivePeriodTypeEnum = ref()


// 打开组件实例抽屉前的准备
const openPrepare = () => {
	fciPropEditDrawer.value = true

	if (fciPropFormRef.value) {
		fciPropFormRef.value.resetFields();
	}

	isFciPropFormSubmitted.value = false

	// 加载枚举数据
	effectivePeriodTypeEnum.value = enumStore.getEnumMap('EffectivePeriodTypeEnum')
}


// 打开组件实例抽屉-用于编辑
const openDrawerForEdit = (fciId, appId) => {
	openPrepare()
	loadFciPropData(fciId)
}


// 打开组件实例抽屉-用于新增
const openDrawerForAdd = (fcmRow) => {
	openPrepare()
	propForm.value.dataBindFlag = fcmRow.dataBindFlag
}


const emit = defineEmits(['afterFciEdit'])


// 提交组件实例表单
const submitFciPropForm = async () => {
	if (!fciPropFormRef.value.validate()) {
		return;
	}

	const formData = {...propForm.value}

	const maintainMethod = formData.id ? editFci : addFci;

	submitForm(fciPropFormRef, formData, isFciPropFormSubmitted, maintainMethod, () => {
		emit('afterFciEdit');
		fciPropEditDrawer.value = false
	})
}


defineExpose({
	openFciDrawerForEdit: openDrawerForEdit,
	openFciDrawerForAdd: openDrawerForAdd,
})

</script>

<style scoped lang="less">
.fci-form {
	width: 100%;
}
</style>