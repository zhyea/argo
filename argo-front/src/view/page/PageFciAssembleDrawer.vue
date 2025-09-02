<template>
	<el-drawer title="组装FCI" v-model="pageFciAssembleDrawer" :with-header=true size="50%">
		<el-container>
			<el-row :gutter="24" style="width: 100%">
				<el-col :span="8">
					<el-card body-style="background-color: #fdfefe; min-height:160px;">
						<template #header>
							<div class="card-header">可用组件</div>
						</template>

						<!-- 拖拽列表组件 -->
						<draggable
							v-model="candidateFciList"
							item-key="id"
							:animation="300"
							:disabled="isSaving"
							ghost-class="ghost-drag-item"
							@start="onDragStart"
							@end="onDragEnd"
							@change="onChange"
							group="pageFciList"
						>
							<template #item="{ element : ele, index }">
								<el-card shadow="always">{{ ele.fciCode }}({{ ele.name }})</el-card>
							</template>
						</draggable>
					</el-card>
				</el-col>

				<el-col :span="16">
					<el-card body-style="background-color: #f0f3f4; min-height:160px;">
						<template #header>
							<div class="card-header">页面组件</div>
						</template>

						<!-- 拖拽列表组件 -->
						<draggable
							v-model="embeddedFciList"
							item-key="id"
							:animation="300"
							:disabled="isSaving"
							ghost-class="ghost-drag-item"
							@start="onDragStart"
							@end="onDragEnd"
							@change="onChange"
							group="pageFciList"
						>
							<template #item="{ element : ele, index }">
								<el-card shadow="always">{{ ele.fciCode }}({{ ele.name }})</el-card>
							</template>
						</draggable>
						<template #footer style="text-align: right">
							<el-button type="primary" @click="handleSave">保存</el-button>
						</template>
					</el-card>
				</el-col>
			</el-row>
		</el-container>
	</el-drawer>
</template>

<script lang="ts" setup>
import {ref, reactive} from 'vue'
import draggable from 'vuedraggable'
import {mapFciList, relateFciList} from "@/api/page";
import {ElMessage} from "element-plus";

// 状态管理
const isSaving = ref(false)
const isDragging = ref(false)


// 拖拽开始事件
function onDragStart() {
	isDragging.value = true
}

// 拖拽结束事件
function onDragEnd() {
	isDragging.value = false
}

// 排序变化事件
function onChange(evt: any) {
}

// 保存
async function handleSave() {
	const pageId = pageIdRef.value
	const fciIdList = embeddedFciList.value.map(item => item.id)
	mapFciList(pageId, fciIdList).then(res => {
		if (res && res.data) {
			ElMessage.success('保存成功');
		} else {
			ElMessage.error('保存失败');
		}
	}).catch(err => {
		console.log(err)
	})
}

const pageIdRef = ref<number>(0)
const candidateFciList = ref<any[]>([])
const embeddedFciList = ref<any[]>([])

async function loadRelatedFciList(pageId: number) {
	pageIdRef.value = pageId
	const response = await relateFciList(pageId)
	if (response && response.data) {
		const fciData = response.data;
		embeddedFciList.value = fciData.embeddedFciList;
		candidateFciList.value = fciData.availableFciList;
	}
}


const pageFciAssembleDrawer = ref(false)

// 打开组件实例抽屉
async function openPageFciAssembleDrawer(appId: number, pageId: number) {
	await loadRelatedFciList(pageId);
	pageFciAssembleDrawer.value = true
}

defineExpose({openPageFciAssembleDrawer})
</script>
<style scoped lang="less">
.ghost-drag-item {
	background-color: #EAF2F8;
}

.fci-drag-container {
	min-height: 90px;
	background-color: #0e41c9 !important;
}
</style>