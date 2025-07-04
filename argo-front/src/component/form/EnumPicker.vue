<template>

	<el-radio-group :id="id" v-if="formType==='radio'" v-model="enumValue"
	                @change="handleChange">
		<el-radio v-for="e in enumRef"
		          :value="e[0]">
			{{ e[1] }}
		</el-radio>
	</el-radio-group>

	<el-select :id="id" v-if="formType==='select'" v-model="enumValue" :placeholder="placeholder"
	           @change="handleChange">
		<el-option v-for="e in enumRef"
		           :key="e[0]"
		           :label="e[1]"
		           :value="e[0]"/>
	</el-select>

</template>

<script setup lang="ts">
import {computed, ref, watch} from "vue";
import {useEnumStore} from "@/store/enum";

const props = defineProps({
	id: String,
	type: String,
	enum: String,
	modelValue: {
		type: Number,
		default: 1
	},
	placeholder: String,
})

const enumValue = ref(props.modelValue)
const formType = ref(props.type)


const enumStore = useEnumStore();

const enumRef = computed(() => {
	return enumStore.getEnumMap(props.enum!);
})


// 监听 prop 的变化
watch(() => props.modelValue, (newVal) => {
	enumValue.value = newVal;
});

// 定义事件处理函数，用于更新父组件中的值
const emit = defineEmits(['update:modelValue']);
const handleChange = (value: any) => {
	emit('update:modelValue', value);
};

</script>

<style scoped lang="less">

</style>