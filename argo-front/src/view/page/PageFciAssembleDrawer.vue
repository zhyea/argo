<template>
	<el-drawer title="组装FCI" v-model="pageFciAssembleDrawer" :with-header=true size="50%">
		<el-container>
			<el-row :gutter="24" style="width: 100%">
				<el-col :span="8">
					<el-card>
						<template #header>
							<div class="card-header">组件实例</div>
						</template>

						<!-- 拖拽列表组件 -->
						<draggable
							v-model="candidateFciList"
							item-key="id"
							:animation="300"
							:disabled="isSaving"
							class="list-group"
							ghost-class="ghost-class"
							chosen-class="chosen-class"
							drag-class="drag-class"
							@start="onDragStart"
							@end="onDragEnd"
							@change="onChange"
							group="pageFciList"
						>
							<template #item="{ element, index }">
								<el-card shadow="always">{{ element.name }}</el-card>
							</template>
						</draggable>
					</el-card>
				</el-col>

				<el-col :span="16">

					<el-card class="mb-4">
						<template #header>
							<div class="card-header">页面信息</div>
						</template>

						<!-- 拖拽列表组件 -->
						<draggable
							v-model="embeddedFciList"
							item-key="id"
							:animation="300"
							:disabled="isSaving"
							class="list-group"
							ghost-class="ghost-class"
							chosen-class="chosen-class"
							drag-class="drag-class"
							@start="onDragStart"
							@end="onDragEnd"
							@change="onChange"
							group="pageFciList"
						>
							<template #item="{ element, index }">
								<el-card shadow="always">{{ element.name }}</el-card>
							</template>
						</draggable>
						<template #footer>
							<el-button type="primary" :loading="isSaving" @click="handleSave"> 保存排序</el-button>
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
import {relateFciList} from "@/api/page";

// 状态管理
const isSaving = ref(false)
const isDragging = ref(false)


// 拖拽开始事件
function onDragStart() {
	isDragging.value = true
	console.log('开始拖拽')
}

// 拖拽结束事件
function onDragEnd() {
	isDragging.value = false
	console.log('拖拽结束，新顺序：', embeddedFciList.value)
}

// 排序变化事件
function onChange(evt: any) {
	console.log('排序发生变化：', evt)
}

// 保存排序
const handleSave = async () => {

}

const candidateFciList = ref([])
const embeddedFciList = ref([])

async function loadRelatedFciList(pageId: number) {
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
