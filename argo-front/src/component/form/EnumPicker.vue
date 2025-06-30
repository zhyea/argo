<template>

	<el-radio-group :id="id" v-if="props.type==='radio'" v-model="model" :placeholder="placeholder">
		<el-radio v-for="e in enumRef"
		          :value="e[0]">
			{{ e[1] }}
		</el-radio>
	</el-radio-group>


	<el-select :id="id" v-if="props.type==='select'" v-model="model" :placeholder="placeholder">
		<el-option v-for="e in enumRef"
		           :key="e[0]"
		           :label="e[1]"
		           :value="e[0]"/>
	</el-select>

</template>

<script setup lang="ts">
import {computed} from "vue";
import {useEnumStore} from "@/store/enum";

const props = defineProps({
	id: String,
	type: String,
	enum: String,
	model: String,
	placeholder: String,
})


const enumStore = useEnumStore();

const enumRef = computed(() => {
	return enumStore.getEnumMap(props.enum!);
})
</script>

<style scoped lang="less">

</style>