<template>
	<el-drawer :title="`${fci.name}属性`"
	           v-model="fciPropsDrawer" :with-header=true size="60%">
		<el-container>
		</el-container>
	</el-drawer>
</template>

<script setup>

import {ref} from "vue";
import {loadEnums} from "@/api/common";

const fciPropsDrawer = ref(false)

const fci = ref({
	id: 0,
	fcmId: 0,
	appId: 0,
	name: '',
	dataBindFlag: 0,
	dataUrl: '',
	switchFlag: 1,
	effectivePeriodType: 1,
	effectiveTimeRange: null,

	remark: '',
})


// 枚举相关信息
const allEnumMap = ref()
const effectivePeriodTypeEnum = ref()


// 打开组件实例抽屉前的准备
const openPrepare = () => {

	// 加载枚举数据
	loadEnums(allEnumMap, () => {
		effectivePeriodTypeEnum.value = allEnumMap.value.get('EffectivePeriodTypeEnum')
	})
}


// 打开组件实例抽屉-用于编辑
const openFciPropsDrawer = (fciId, appId) => {
	openPrepare()
}


defineExpose({
	openFciPropsDrawer,
})

</script>

<style scoped lang="less">
.fci-form {
	width: 100%;
}
</style>