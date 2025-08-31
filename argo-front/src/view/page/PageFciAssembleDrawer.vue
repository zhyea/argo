<template>
	<el-drawer title="组装FCI" v-model="pageFciAssembleDrawer" :with-header=true size="50%">
		<el-container>

			<div class="draggable-demo">
				<el-card class="mb-4">
					<template #header>
						<div class="card-header">
							<span>列表拖拽</span>
							<div class="header-actions">
								<el-button
									type="primary"
									:loading="isSaving"
									@click="handleSave"
								>
									保存排序
								</el-button>
								<el-button
									type="warning"
									@click="resetOrder"
								>
									重置顺序
								</el-button>
							</div>
						</div>
					</template>

					<!-- 拖拽列表组件 -->
					<draggable
						v-model="list"
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
						group="list"
					>
						<template #item="{ element, index }">
							<div class="list-group-item">
								<div class="item-content">
									<el-tag class="mr-2" type="info">{{ index + 1 }}</el-tag>
									<el-tag class="mr-2">{{ element.id }}</el-tag>
									<span>{{ element.name }}</span>
								</div>
								<div class="item-actions">
									<el-tooltip content="拖拽手柄" placement="top">
										<i class="el-icon-rank drag-handle"></i>
									</el-tooltip>
								</div>
							</div>
						</template>
					</draggable>
				</el-card>

				<el-card class="mb-4">
					<template #header>
						<div class="card-header">
							<span>列表拖拽</span>
							<div class="header-actions">
								<el-button
									type="primary"
									:loading="isSaving"
									@click="handleSave"
								>
									保存排序
								</el-button>
								<el-button
									type="warning"
									@click="resetOrder"
								>
									重置顺序
								</el-button>
							</div>
						</div>
					</template>

					<!-- 拖拽列表组件 -->
					<draggable
						v-model="list2"
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
						group="list"
					>
						<template #item="{ element, index }">
							<div class="list-group-item">
								<div class="item-content">
									<el-tag class="mr-2" type="info">{{ index + 1 }}</el-tag>
									<el-tag class="mr-2">{{ element.id }}</el-tag>
									<span>{{ element.name }}</span>
								</div>
								<div class="item-actions">
									<el-tooltip content="拖拽手柄" placement="top">
										<i class="el-icon-rank drag-handle"></i>
									</el-tooltip>
								</div>
							</div>
						</template>
					</draggable>
				</el-card>
			</div>

		</el-container>
	</el-drawer>
</template>

<script lang="ts" setup>
import { ref, reactive } from 'vue'
import { ElMessage } from 'element-plus'
import draggable from 'vuedraggable'

// 状态管理
const isSaving = ref(false)
const isDragging = ref(false)

// 原始数据备份（用于重置）
const originalList = [
	{ id: 1, name: '项目 1' },
	{ id: 2, name: '项目 2' },
	{ id: 3, name: '项目 3' },
	{ id: 4, name: '项目 4' },
	{ id: 5, name: '项目 5' },
]

// 响应式数据
const list = ref([...originalList])


// 原始数据备份（用于重置）
const originalList2 = [
	{ id: 7, name: '项目 7' },
	{ id: 8, name: '项目 8' },
	{ id: 9, name: '项目 9' },
	{ id: 10, name: '项目 10' },
	{ id: 11, name: '项目 11' },
]

// 响应式数据
const list2 = ref([...originalList2])

// 拖拽开始事件
const onDragStart = () => {
	isDragging.value = true
	console.log('开始拖拽')
}

// 拖拽结束事件
const onDragEnd = () => {
	isDragging.value = false
	console.log('拖拽结束，新顺序：', list.value)
}

// 排序变化事件
const onChange = (evt) => {
	console.log('排序发生变化：', evt)
}

// 保存排序
const handleSave = async () => {
	try {
		isSaving.value = true
		// 模拟API请求
		await new Promise(resolve => setTimeout(resolve, 1000))

		// API调用
		// const response = await axios.post('/api/updateSort', {
		//   sortedList: list.value.map((item, index) => ({
		//     ...item,
		//     sort: index + 1
		//   }))
		// })

		ElMessage.success('保存成功！')
	} catch (error) {
		console.error('保存失败：', error)
		ElMessage.error('保存失败，请重试')
	} finally {
		isSaving.value = false
	}
}

// 重置顺序
const resetOrder = () => {
	list.value = [...originalList]
	ElMessage.info('已重置为原始顺序')
}





const pageFciAssembleDrawer = ref(false)
// 打开组件实例抽屉
const openPageFciAssembleDrawer = (appId: number, pageId: number) => {
	pageFciAssembleDrawer.value = true
}
defineExpose({openPageFciAssembleDrawer})
</script>
