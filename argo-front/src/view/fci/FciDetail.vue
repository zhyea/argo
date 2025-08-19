<template>

	<el-container direction="vertical" class="out-wrapper">
		<head-bar :showBreadcrumb="false"/>

		<el-container class="fci_container">
			<el-aside class="left_container">
				<el-row class="item-row">
					<el-col :span="7" class="item-label">组件名称</el-col>
					<el-col :span="17" class="item-value">{{ fciData.name }}</el-col>
				</el-row>
				<el-row class="item-row">
					<el-col :span="7" class="item-label">组件代码</el-col>
					<el-col :span="17" class="item-value">{{ fciData.fciCode }}</el-col>
				</el-row>
				<el-row class="item-row">
					<el-col :span="7" class="item-label">模型名称</el-col>
					<el-col :span="17" class="item-value">{{ fciData.fcmName }}</el-col>
				</el-row>
				<el-row class="item-row">
					<el-col :span="7" class="item-label">组件类型</el-col>
					<el-col :span="17" class="item-value">{{ fciData.typeName }}</el-col>
				</el-row>
				<el-row class="item-row">
					<el-col :span="7" class="item-label">生效周期类型</el-col>
					<el-col :span="17" class="item-value">{{ fciData.effectivePeriodTypeName }}
					</el-col>
				</el-row>
				<el-row class="item-row" v-if="fciData.effectivePeriodType === 2">
					<el-col :span="7" class="item-label">生效开始时间</el-col>
					<el-col :span="17" class="item-value">{{ fciData.effectiveStartTime }}
					</el-col>
				</el-row>
				<el-row class="item-row" v-if="fciData.effectivePeriodType === 2">
					<el-col :span="7" class="item-label">生效结束时间</el-col>
					<el-col :span="17" class="item-value">{{ fciData.effectiveEndTime }}
					</el-col>
				</el-row>
				<el-row class="item-row">
					<el-col :span="7" class="item-label">备注</el-col>
					<el-col :span="17" class="item-value">{{ fciData.remark }}</el-col>
				</el-row>
			</el-aside>

			<el-main class="right_container">

			</el-main>
		</el-container>

	</el-container>
</template>

<script setup>
import {ref, onMounted} from 'vue'
import {useRoute} from "vue-router";
import {loadEnumMap} from "@/api/common";
import { getFci} from "@/api/fci";
import HeadBar from "@/component/layout/HeadBar.vue";


const route = useRoute();

const fciData = ref({
	id: 0,
	fcmId: 0,
	appId: 0,
	name: '',
	fciCode: '',
	fcmName: '',
	typeName: '',
	switchFlag: 1,
	effectivePeriodType: 1,
	effectivePeriodTypeName: '',
	effectiveStartTime: '',
	effectiveEndTime: '',
	remark: '',
})

// 加载组件实例数据
function loadFciData() {
	const fciId = route.params.fciId;

	Promise.all([getFci(fciId), loadEnumMap()])
		.then(result => {
			const [resp, enumMap] = result;

			const effectivePeriodTypeEnum = enumMap.get('EffectivePeriodTypeEnum');
			if (!resp || !resp.data) {
				return;
			}

			const r = resp.data;
			r.effectivePeriodTypeName = effectivePeriodTypeEnum && effectivePeriodTypeEnum.get(r.effectivePeriodType);
			r.effectivePeriod = r.effectiveStartTime + ' ~ ' + r.effectiveEndTime;

			fciData.value = r;
		})
}


onMounted(() => {
	// 加载页面数据
	loadFciData();
})

</script>

<style scoped lang="less">
.out-wrapper {
	margin: 0;
	padding: 0;
	height: 100%;
}

.fci_container {
	height: 100%;
	padding: 0;
}

.left_container {
	width: 360px;
	height: 100%;
	background-color: #f5f000;
}

.right_container {
	height: 100%;
	background-color: #00f000;
}

</style>