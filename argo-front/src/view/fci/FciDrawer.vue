<template>
	<el-drawer title="编辑组件实例"
	           v-model="fciItemDrawer" :with-header=true size="40%">
		<el-container>
			<!--表单信息-->
			<el-form status-icon
			         label-position="right"
			         label-width="90px"
			         label-suffix=":"
			         :model="fciForm" ref="fciFormRef" :rules="fciFormRules" class="fci-form">

				<el-form-item prop="fcmId">
					<el-input type="hidden" v-model="fciForm.fcmId"/>
				</el-form-item>

			</el-form>
		</el-container>
	</el-drawer>
</template>

<script setup>

import {ref} from "vue";

const fciItemDrawer = ref(false)

const fciForm = ref({
	id: '',
	remark: '',
})

const fciFormRef = ref()

const fciFormRules = {
	ruleTitle: [
		{required: true, message: '请输入规则名称', trigger: 'blur'},
	],
	response: [
		{required: true, message: '请输入返回值', trigger: 'blur'},
	],
};


const isFciFormSubmitted = ref(false)


// 打开组件实例抽屉
const openFciDrawer = (fcmId, fciId) => {
	fciItemDrawer.value = true

	if (fciFormRef.value) {
		fciFormRef.value.resetFields();
	}

	if (fciId) {
		//loadMethodRule(ruleId)
	} else {
		fciForm.value = {
			fcmId: fcmId,
		}
	}

	isFciFormSubmitted.value = false
}


defineExpose({openFciDrawer})


</script>

<style scoped lang="less">
.fci-form {
	width: 100%;
}
</style>