<template>
	<el-dialog v-model="appSelectorDialogRef"
	           title="选择应用"
	           :close-on-click-modal="false"
	           :close-on-press-escape="false"
	           :show-close="false"
	           :destroy-on-close="true">
		<el-form :model="form" ref="formRef" :rules="formRules">
			<el-form-item label="应用" prop="appId">
				<el-select v-model="form.appId" id="appId" placeholder="请选择应用">
					<el-option v-for="app in appList"
					           :key="app.id"
					           :label="app.appName"
					           :value="app.id"/>
				</el-select>
			</el-form-item>
		</el-form>
		<template #footer>
	      <span class="dialog-footer">
	        <el-button type="primary" @click="selectApp">确定</el-button>
	      </span>
		</template>
	</el-dialog>
</template>

<script lang="ts" setup>
import {computed, ref} from 'vue'
import {useAppStore} from "@/store/app";
import {setLastVisitedApp} from "@/api/preference";

const appStore = useAppStore()

const appSelectorDialogRef = ref(false)


const formRef = ref()

const form = ref({
	appId: null,
})

const formRules = {
	appId: [
		{required: true, message: '请选择应用', trigger: 'blur'},
	],
}


const appList = computed(() => {
	return appStore.getAppList()
})


const selectApp = () => {
	formRef.value.validate((valid: any) => {
		if (!valid) {
			return false;
		}
		console.log(form.value)
		if (form.value.appId) {
			appStore.changeCurrent(form.value.appId)
			setLastVisitedApp(form.value.appId)
			appSelectorDialogRef.value = false
		}
	})
}


const openAppSelectorDialog = () => {
	appSelectorDialogRef.value = true;
	const appList = appStore.getAppList();
	if (!appList || appList.length === 0) {
		appStore.fetchAllApps().catch(err => {
			console.log(err)
		})
	}
}

defineExpose({
	openAppSelectorDialog
})

</script>

<style scoped lang="less">

</style>