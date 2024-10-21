<template>

	<!-- parent -->
	<el-sub-menu v-if="prop.item.children"
	             :index="prop.item.index">

		<template #title>
			<el-icon v-if="prop.item.icon">
				<component :is="prop.item.icon"/>
			</el-icon>
			<span>{{ prop.item.name }}</span>
		</template>

		<template v-for="child in prop.item.children">
			<menu-item v-if="child.children" :item="child" :key="child"/>
			<el-menu-item v-else :index="child.index" :key="child.uri">
				<el-icon v-if="child.icon">
					<component :is="child.icon"/>
				</el-icon>
				{{ child.name }}
			</el-menu-item>
		</template>

	</el-sub-menu>

	<!-- children -->
	<el-menu-item v-else
	              :index="prop.item.index">
		<el-icon v-if="prop.item.icon">
			<component :is="prop.item.icon"/>
		</el-icon>
		<span>{{ prop.item.name }}</span>
	</el-menu-item>
</template>

<script setup>

const prop = defineProps({
	item: {
		type: Object,
		required: true
	}
})




</script>

<style scoped lang="less">

</style>