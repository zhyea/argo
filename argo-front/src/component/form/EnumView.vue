<template>

	<el-radio-group id="effectivePeriodType" v-if="props.type==='radio'" v-model="fciForm.effectivePeriodType">
		<el-radio v-for="e in enumRef"
		          :value="e[0]">
			{{ e[1] }}
		</el-radio>
	</el-radio-group>


</template>

<script setup lang="ts">
import {computed} from "vue";
import {useEnumStore} from "@/store/enum";

const props = defineProps({
	id: String,
	type: String,
	enum: String,
})


const enumStore = useEnumStore();

const enumRef = computed(() => {
	return enumStore.getEnumMap(props.enum!);
})
</script>

<style scoped lang="less">

</style>