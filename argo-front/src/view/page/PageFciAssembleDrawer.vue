<template>
	<el-drawer title="组装FCI" v-model="pageFciAssembleDrawer" :with-header=true size="50%">
		<el-container>

			<draggable class="drag_img" item-key="url">
				<!--				<template #item="{ element }">
									<div class="img_item">
										<img :src="element.url" alt="" style="width: 100%;height: 100%;">
										&lt;!&ndash; 图片的遮罩 预览 和 删除 &ndash;&gt;
										<div class="shade">
											<div class="img_operate">
												&lt;!&ndash;								<el-icon>&ndash;&gt;
												&lt;!&ndash;									<ZoomIn @click="handlePictureCardPreview(element)" style="cursor:pointer;"/>&ndash;&gt;
												&lt;!&ndash;								</el-icon>&ndash;&gt;
												&lt;!&ndash;								<el-icon style="margin: 0 10px;">&ndash;&gt;
												&lt;!&ndash;									<DeleteFilled @click="handleRemove(element)"&ndash;&gt;
												&lt;!&ndash;									              style="cursor: pointer;"/>&ndash;&gt;
												&lt;!&ndash;								</el-icon>&ndash;&gt;
											</div>
										</div>
									</div>
								</template>-->
			</draggable>

		</el-container>
	</el-drawer>
</template>

<script lang="ts" setup>

import {ref} from "vue";
import draggable from "vuedraggable";

const pageFciAssembleDrawer = ref(false)


// 打开组件实例抽屉
const openPageFciAssembleDrawer = (appId: number, pageId: number) => {
	pageFciAssembleDrawer.value = true
}


defineExpose({openPageFciAssembleDrawer})


</script>

<style scoped lang="less">
.page-form {
	width: 100%;
}
</style>