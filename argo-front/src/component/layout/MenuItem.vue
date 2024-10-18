<template>

	<!-- parent -->
	<el-sub-menu v-if="props.item.children"
	             :index="props.item.uri">

		<template #title>
			<el-icon v-if="props.item.icon">
				<component :is="props.item.icon"/>
			</el-icon>
			<span>{{ props.item.name }}</span>
		</template>

		<template v-for="child in props.item.children">
			<menu-item v-if="child.children" :item="child" :key="child"/>
			<el-menu-item v-else :index="child.uri" :key="child.uri">
				<el-icon v-if="child.icon">
					<component :is="child.icon"/>
				</el-icon>
				{{ child.name }}
			</el-menu-item>
		</template>

	</el-sub-menu>

	<!-- children -->
	<el-menu-item v-else
	              :index="props.item.uri">
		<el-icon v-if="props.item.icon">
			<component :is="props.item.icon"/>
		</el-icon>
		<span>{{ props.item.name }}</span>
	</el-menu-item>
</template>

<script setup>

const props = defineProps({
	item: {
		type: Object,
		required: true
	}
})




</script>

<style scoped lang="less">

</style>