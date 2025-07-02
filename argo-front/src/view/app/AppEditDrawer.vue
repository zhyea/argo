<template>
	<el-drawer :title="`${appForm.id ? '编辑' : '新增'} 应用信息 - ${appForm.appName}`"
	           v-model="appEditDrawer" :with-header=true size="40%">
		<el-container>
			<!--表单信息-->

			<el-card class="fcm-region">
				<el-form
					:model="appForm" ref="appFormRef" :rules="appFormRules"
					label-suffix=":" label-width="90px"
					status-icon>

					<el-form-item prop="appId">
						<el-input type="hidden" v-model="appForm.id"/>
					</el-form-item>

					<el-form-item label="应用Code" prop="appCode">
						<el-input id="appCode" v-model="appForm.appCode"/>
					</el-form-item>

					<el-form-item label="应用名称" prop="appName">
						<el-input id="appName" v-model="appForm.appName"/>
					</el-form-item>

					<el-form-item label="应用图标" prop="icon">
						<el-input id="icon" v-model="appForm.icon"/>
					</el-form-item>

					<el-form-item label="备注" prop="remark">
						<el-input id="remark" type="textarea" v-model="appForm.remark" :autosize="{ minRows: 4,}"/>
					</el-form-item>
				</el-form>
			</el-card>


			<el-card class="fcm-region">
				<el-button type="primary" :disabled="isAppFormSubmitted" @click="submitAppForm">提交</el-button>
			</el-card>
		</el-container>
	</el-drawer>
</template>

<script lang="ts" setup>
import {ref} from "vue";
import {addApp, editApp, getApp} from "@/api/app";
import {submitForm} from "@/view/helper";

// app 表单数据
const appForm = ref({
	id: '',
	appCode: '',
	appName: '',
	icon: '',
	remark: '',
})

// app 表单引用
const appFormRef = ref()

// app 表单验证规则
const appFormRules = {
	appCode: [
		{required: true, message: '请输入应用ID', trigger: 'blur'},
	],
	appName: [
		{required: true, message: '请输入应用名称', trigger: 'blur'},
	],
}

// 标记是否已经提交表单
const isAppFormSubmitted = ref(false)


// 提交app编辑信息
function submitAppForm() {
	if (!appFormRef.value.validate()) {
		return;
	}
	const formData = {...appForm.value};
	const maintainMethod = formData.id ? editApp : addApp;

	submitForm(appFormRef, formData, isAppFormSubmitted, maintainMethod, () => {
		emit('afterEdit');
		appEditDrawer.value = false;
	});
}


const appEditDrawer = ref(false)

// 打开组件实例抽屉前的准备
function openPrepare() {
	appEditDrawer.value = true

	if (appFormRef.value) {
		appFormRef.value.resetFields();
	}

	isAppFormSubmitted.value = false
}


// 加载组件实例数据
function loadAppData(appId) {
	if (!appId) return;
	getApp(appId).then(response => {
		if (response && response.data) {
			appForm.value = response.data;
		}
	})
}

// 打开组件实例抽屉-用于编辑
function openAppEditDrawer(appId) {
	openPrepare()
	loadAppData(appId)
}


const emit = defineEmits(['afterEdit'])


defineExpose({
	openAppEditDrawer,
})

</script>

<style scoped lang="less">

</style>