<template>
	<el-drawer :title="`${fciForm.id ? '编辑' : '新增'}组件实例 - ${fciForm.name}`"
	           v-model="fciItemDrawer" :with-header=true size="40%">
		<el-container>
			<!--表单信息-->
			<el-form status-icon
			         label-position="right"
			         label-width="100px"
			         label-suffix=":"
			         :model="fciForm" ref="fciFormRef" :rules="fciFormRules" class="fci-form">

				<el-card class="fcm-region">
					<el-form-item prop="fcmId">
						<el-input type="hidden" v-model="fciForm.fcmId"/>
						<el-input type="hidden" v-model="fciForm.appId"/>
					</el-form-item>

					<el-form-item label="名称" prop="name">
						<el-input id="name" v-model="fciForm.name"/>
					</el-form-item>

					<el-form-item label="使用范围" prop="usageScope">
						<el-radio-group id="usageScope" v-model="fciForm.usageScope">
							<el-radio v-for="e in usageScopeEnum"
							          :value="e[0]">
								{{ e[1] }}
							</el-radio>
						</el-radio-group>
					</el-form-item>

					<el-form-item label="是否启用" prop="switchFlag">
						<el-switch id="switchFlag" v-model="fciForm.switchFlag"
						           inline-prompt size="large"
						           active-text="是" :active-value="1"
						           inactive-text="否" :inactive-value="0"/>
					</el-form-item>

					<el-form-item label="生效周期" prop="effectivePeriodType">
						<el-radio-group id="effectivePeriodType" v-model="fciForm.effectivePeriodType">
							<el-radio v-for="e in effectivePeriodTypeEnum"
							          :value="e[0]">
								{{ e[1] }}
							</el-radio>
						</el-radio-group>
					</el-form-item>

					<el-form-item label="生效时间" prop="effectivePeriodType"
					              v-if="fciForm.effectivePeriodType === 2">
						<el-date-picker
							v-model="fciForm.effectiveTimeRange"
							type="datetimerange"
							range-separator="到"
							start-placeholder="请选择生效开始时间"
							end-placeholder="请选择生效结束时间"
							value-format="YYYY-MM-DD HH:mm:ss"/>
					</el-form-item>

					<el-form-item label="备注" prop="remark">
						<el-input id="remark" type="textarea" v-model="fciForm.remark" :autosize="{ minRows: 4,}"/>
					</el-form-item>
				</el-card>


				<el-card class="fcm-region">
					<el-button type="primary" :disabled="isFciFormSubmitted" @click="submitFciForm">提交</el-button>
				</el-card>
			</el-form>
		</el-container>
	</el-drawer>
</template>

<script lang="ts" setup>

import {ref, computed} from "vue";
import {submitForm} from "@/utils/helper";
import {addFci, editFci, getFci} from "@/api/fci";
import {useEnumStore} from "@/store/enum";

const enumStore = useEnumStore()

const fciItemDrawer = ref(false)

const fciForm = ref({
	id: 0,
	fcmId: 0,
	appId: 0,
	name: '',
	usageScope: 2,
	switchFlag: 1,
	effectivePeriodType: 1,
	effectiveTimeRange: ['', ''],

	remark: '',
})

const fciFormRef = ref()

const fciFormRules = {
	name: [
		{required: true, message: '请输入组件名称', trigger: 'blur'},
	],
	response: [
		{required: true, message: '请输入返回值', trigger: 'blur'},
	],
};

// 加载组件实例数据
const loadFciData = async (fciId: number) => {
	if (!fciId) {
		return
	}

	getFci(fciId).then(response => {
		if (response && response.data) {
			const fciData = response.data;
			fciForm.value = fciData;
			fciForm.value.effectiveTimeRange = [fciData.effectiveStartTime, fciData.effectiveEndTime];
		}
	})
}


const isFciFormSubmitted = ref(false)


// 枚举相关信息
const effectivePeriodTypeEnum = computed(() => {
	return enumStore.getEnumMap('EffectivePeriodTypeEnum');
})
const usageScopeEnum = computed(() => {
	return enumStore.getEnumMap('FciUsageScopeEnum');
})


// 打开组件实例抽屉前的准备
const openPrepare = () => {
	fciItemDrawer.value = true

	if (fciFormRef.value) {
		fciFormRef.value.resetFields();
	}

	isFciFormSubmitted.value = false
}


// 打开组件实例抽屉-用于编辑
const openDrawerForEdit = (fciId: number, appId: number) => {
	openPrepare()
	loadFciData(fciId)
}


// 打开组件实例抽屉-用于新增
const openDrawerForAdd = (fcmRow: any) => {
	openPrepare()
	fciForm.value.fcmId = fcmRow.fcmId
	fciForm.value.appId = fcmRow.appId
}


const emit = defineEmits(['afterFciEdit'])


// 提交组件实例表单
const submitFciForm = async () => {
	if (!fciFormRef.value.validate()) {
		return;
	}

	const formData = {...fciForm.value}

	const maintainMethod = formData.id ? editFci : addFci;

	submitForm(fciFormRef, formData, isFciFormSubmitted, maintainMethod, () => {
		emit('afterFciEdit');
		fciItemDrawer.value = false
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