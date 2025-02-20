<template>
	<el-drawer :title="`${fciForm.id ? '编辑' : '新增'}组件实例`"
	           v-model="fciItemDrawer" :with-header=true size="50%">
		<el-container>
			<!--表单信息-->
			<el-form status-icon
			         label-position="right"
			         label-width="100px"
			         label-suffix=":"
			         :model="fciForm" ref="fciFormRef" :rules="fciFormRules" class="fci-form">


				<el-card header="组件实例信息" class="fcm-region">

					<el-form-item prop="fcmId">
						<el-input type="hidden" v-model="fciForm.fcmId"/>
					</el-form-item>

					<el-form-item label="名称" prop="name">
						<el-input id="name" v-model="fciForm.name"/>
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

					<el-form-item label="生效时间" prop="effectivePeriodType" size="small"
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

<script setup>

import {ref} from "vue";
import {loadEnums} from "@/api/common.js";

const fciItemDrawer = ref(false)

const fciForm = ref({
	id: 0,
	fcmId: 0,
	name: '',
	dataUrl: '',
	switchFlag: 1,
	effectivePeriodType: 1,
	effectiveTimeRange: null,
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


const isFciFormSubmitted = ref(false)


// 枚举相关信息
const allEnumMap = ref()
const effectivePeriodTypeEnum = ref()

// 打开组件实例抽屉
const openFciDrawer = (fcmId, fciId) => {
	fciItemDrawer.value = true

	if (fciFormRef.value) {
		fciFormRef.value.resetFields();
	}

	if (fciId) {
		//loadMethodRule(ruleId)
	} else {
		fciForm.value.fcmId = fcmId
		/*fciForm.value = {
			fcmId: fcmId,
		}*/
	}

	isFciFormSubmitted.value = false

	// 加载枚举数据
	loadEnums(allEnumMap, () => {
		effectivePeriodTypeEnum.value = allEnumMap.value.get('EffectivePeriodTypeEnum')
	})
}


const submitFciForm = async () => {}


defineExpose({openFciDrawer})


</script>

<style scoped lang="less">
.fci-form {
	width: 100%;
}
</style>