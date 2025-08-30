<template>
	<el-popover placement="bottom" :width="240" ref="popover" trigger="hover"
	            @show="showPopover" @hide="hidePopover">
		<template #reference>
			<span>
				{{ currentAppName }}
				<el-icon :class="{'rotate-180':isOpen, 'rotate-360':!isOpen, 'app-arrow': true}">
					<ArrowDown/>
				</el-icon>
			</span>
		</template>

		<el-table :data="appData" @row-click="selectApp" :show-header="false">
			<el-table-column property="appName" label="date"/>
		</el-table>
	</el-popover>
</template>

<script lang="ts" setup>

import {computed, ref} from 'vue'
import {useAppStore} from "@/store/app";
import {ArrowDown} from "@element-plus/icons-vue";
import {useTagStore} from "@/store/tag";
import {useRouter} from "vue-router";
import {goToApp} from "@/utils/helper";

const appStore = useAppStore();
const tagStore = useTagStore();
const router = useRouter();


const isOpen = ref(false)

function showPopover() {
	appStore.refresh().catch((e) => {
		console.error(e)
	});
	isOpen.value = true
}

function hidePopover() {
	isOpen.value = false
}

const currentAppName = computed(() => {
	const app = appStore.getCurrent();
	return app ? app.appName : ''
})

const appData = computed(() => {
	return appStore.getAppList()
})

const popover = ref()

function selectApp(row: any) {
	appStore.changeCurrent(row.id);
	popover.value.hide();
	goToAppPage(row.id).catch((e) => {
		console.error(e)
	});
}


async function goToAppPage(appId: number) {
	await goToApp(appId, router)
}

</script>

<style scoped lang="less">
.app-arrow {
	font-size: 12px;
	display: inline-block;
}

.rotate-180 {
	transform: rotate(-180deg); /* 旋转180度 */
	transition: transform 0.5s; /* 平滑过渡效果 */
}

.rotate-360 {
	transform: rotate(-360deg); /* 旋转360度 */
	transition: transform 0.5s; /* 平滑过渡效果 */
}
</style>