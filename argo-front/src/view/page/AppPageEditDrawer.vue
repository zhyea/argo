<template>
	<el-drawer :title="`${appPageForm.pageId ? '编辑' : '新增'}应用页面`"
	           v-model="appPageItemDrawer" :with-header=true size="40%">
		<el-container>
			<!--表单信息-->
			<el-form status-icon
			         label-position="right"
			         label-width="100px"
			         label-suffix=":"
			         :model="appPageForm" ref="appPageFormRef" :rules="appPageFormRules" class="page-form">

				<el-card header="应用页面信息" class="page-region">

					<el-form-item prop="appId">
						<el-input type="hidden" v-model="appPageForm.appId"/>
					</el-form-item>

					<el-form-item label="页面代码" prop="pageCode">
						<el-input id="pageCode" readonly v-model="appPageForm.pageCode"/>
					</el-form-item>

					<el-form-item label="页面名称" prop="pageName">
						<el-input id="pageName" v-model="appPageForm.pageName"/>
					</el-form-item>

					<el-form-item label="备注" prop="remark">
						<el-input id="remark" type="textarea" v-model="appPageForm.remark" :autosize="{ minRows: 4,}"/>
					</el-form-item>
				</el-card>

				<el-card class="fcm-region">
					<el-button type="primary" :disabled="isAppPageFormSubmitted" @click="submitAppPageForm">提交
					</el-button>
				</el-card>

			</el-form>
		</el-container>
	</el-drawer>
</template>

<script setup>

import {ref} from "vue";
import {addPage, editPage, generatePageCode, getPage} from "@/api/page";
import {submitForm} from "@/utils/common";

const appPageItemDrawer = ref(false)

const appPageForm = ref({
	pageId: 0,
	appId: 0,
	pageCode: '',
	pageName: '',
	remark: '',
})

const appPageFormRef = ref()

const appPageFormRules = {
	pageCode: [
		{required: true, message: '请输入页面代码', trigger: 'blur'},
	],
	pageName: [
		{required: true, message: '请输入页面名称', trigger: 'blur'},
	],
};

const isAppPageFormSubmitted = ref(false)


// 打开组件实例抽屉
const openAppPageDrawer = (appId, pageId) => {
	appPageItemDrawer.value = true

	if (appPageFormRef.value) {
		appPageFormRef.value.resetFields();
	}

	if (pageId) {
		loadPageInfo(appId, pageId);
	} else {
		appPageForm.value.appId = appId;
		generatePageCode().then(res => {
			appPageForm.value.pageCode = res.data;
		});
	}

	isAppPageFormSubmitted.value = false
}

// 加载页面信息
const loadPageInfo = (appId, pageId) => {
	getPage(pageId).then(res => {
		if (res.data.appId !== appId) {
			throw new Error('页面信息错误')
		}
		appPageForm.value = res.data;
	})
}


const emit = defineEmits(['afterPageAdd'])

const submitAppPageForm = async () => {
	if (!appPageFormRef.value.validate()) {
		return;
	}

	const formData = {...appPageForm.value};

	const maintainMethod = formData.pageId ? editPage : addPage;

	submitForm(appPageFormRef, formData, isAppPageFormSubmitted, maintainMethod, () => {
		emit('afterPageAdd');
		appPageItemDrawer.value = false
	})
}


defineExpose({openAppPageDrawer})


</script>

<style scoped lang="less">
.page-form {
	width: 100%;
}
</style>