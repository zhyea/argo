<template>
	<el-drawer :title="`${appForm.id && appForm.id > 0 ? '编辑 ' + appForm.appName : '新增应用'}`"
	           v-model="appEditDrawer" :with-header=true size="40%">
		<el-container v-loading="loadingRef">
			<!--表单信息-->
			<el-form :model="appForm" ref="appFormRef" :rules="appFormRules"
			         label-suffix=":" label-width="90px"
			         status-icon class="full-region">

				<el-card>

					<el-form-item prop="id">
						<el-input type="hidden" v-model="appForm.id"/>
					</el-form-item>

					<el-form-item label="应用编码" prop="appCode">
						<el-input id="appCode" readonly v-model="appForm.appCode"/>
					</el-form-item>

					<el-form-item label="应用名称" prop="appName">
						<el-input id="appName" v-model="appForm.appName"/>
					</el-form-item>

					<el-form-item label="应用类型" prop="appType">
						<enum-picker id="appType" type="select" enum="AppTypeEnum" v-model="appForm.appType"/>
					</el-form-item>

					<el-form-item label="应用图标" prop="icon">
						<el-input id="icon" v-model="appForm.icon"/>
					</el-form-item>

					<el-form-item label="备注" prop="remark">
						<el-input id="remark" type="textarea" v-model="appForm.remark" :autosize="{ minRows: 4,}"/>
					</el-form-item>
				</el-card>


				<el-card>
					<el-button type="primary" :disabled="isAppFormSubmitted" @click="submitAppForm">提交</el-button>
				</el-card>
			</el-form>
		</el-container>
	</el-drawer>
</template>

<script lang="ts" setup>
import {ref} from "vue";
import {addApp, editApp, generateAppCode, getApp} from "@/api/app";
import {submitForm} from "@/utils/helper";
import EnumPicker from "@/component/form/EnumPicker.vue";

// app 表单数据
const appForm = ref({
	id: 0,
	appCode: '',
	appName: '',
	appType: 1,
	icon: '',
	remark: '',
})

// app 表单引用
const appFormRef = ref()
const loadingRef = ref(true)

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
function openPrepare(appId: number) {
	appEditDrawer.value = true

	if (appFormRef.value) {
		appFormRef.value.resetFields();
	}

	if (!appId) {
		generateAppCode().then(response => {
			appForm.value.appCode = response.data;
			loadingRef.value = false
		})
	}

	isAppFormSubmitted.value = false
}


// 加载组件实例数据
function loadAppData(appId: number) {
	if (!appId || appId <= 0) return;
	getApp(appId).then(response => {
		if (response && response.data) {
			appForm.value = response.data;
		}
		loadingRef.value = false
	})
}

// 打开组件实例抽屉-用于编辑
function openAppEditDrawer(appId: number) {
	openPrepare(appId)
	loadAppData(appId)
}


const emit = defineEmits(['afterEdit'])


defineExpose({
	openAppEditDrawer,
})

</script>

<style scoped lang="less">

</style>