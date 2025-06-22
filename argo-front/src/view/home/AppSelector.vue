<template>
	<el-dialog v-model="appSelectorDialogRef"
	           title="选择应用"
	           colose-on-click-modal="false"
	           close-on-press-escape="false"
	           show-close="false"
	           destroy-on-close="true">
		<el-form :model="form" ref="formRef">
			<el-form-item label="应用">
				<el-select v-model="form.app" placeholder="请选择应用">
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

<script setup>
import {reactive, ref, computed} from 'vue'
import {useAppStore} from "@/store/app";

const appStore = useAppStore()

const appSelectorDialogRef = ref(false)


const formRef = ref()

const form = reactive({
	app: 0,
})


const appList = computed(async () => {
	console.log(appStore.appList)
	return await appStore.getAppList()
})


const selectApp = () => {
	appStore.changeCurrent(form.app)
	appSelectorDialogRef.value = false
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