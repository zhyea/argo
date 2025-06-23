<template>
	<el-dialog v-model="appSelectorDialogRef"
	           title="选择应用"
	           :close-on-click-modal="false"
	           :close-on-press-escape="false"
	           :show-close="false"
	           :destroy-on-close="true">
		<el-form :model="form" ref="formRef" :rules="formRules">
			<el-form-item label="应用" prop="appId">
				<el-select v-model="form.appId" placeholder="请选择应用">
					<el-option v-for="app in appList"
					           :key="app.id"
					           :label="app.appName"
					           :value="app.id"
					           change="changeSelectedApp"/>
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
import {reactive, ref, computed} from 'vue'
import {useAppStore} from "@/store/app";

const appStore = useAppStore()

const appSelectorDialogRef = ref(false)


const formRef = ref()

const form = reactive({
	appId: null,
})

const formRules = {
	appId: [
		{required: true, message: '请选择应用', trigger: 'blur'},
	],
}


const appList = computed(() => {
	const appList = appStore.getAppList()
	console.log(appList)
	return appList
})


const selectApp = () => {
	formRef.value.validate((valid) => {
		if (valid) {
			appStore.changeCurrent(form.appId)
			appSelectorDialogRef.value = false
		} else {
			return false
		}
	})
}


const openAppSelectorDialog = () => {
	console.log('openAppSelectorDialog')
	appSelectorDialogRef.value = true
}

defineExpose({
	openAppSelectorDialog
})

</script>

<style scoped lang="less">

</style>