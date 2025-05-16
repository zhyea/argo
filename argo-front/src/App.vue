<!-- 配置信息 -->

<template>
	<router-view/>
</template>


<script setup>
import {onMounted, watch} from 'vue'
import {useBreadcrumbStore} from "@/store/breadcrumb.js";
import {useRoute} from "vue-router";
import {useI18n} from "vue-i18n";
import {getLocale} from "@/utils/localforage/index.ts";

const route = useRoute()
const breadcrumbStore = useBreadcrumbStore()

onMounted(() => {
		breadcrumbStore.set(route.matched)
	})


const i18n = useI18n();
getLocale().then(lang => {
	i18n.locale.value = lang
})

watch(route, () => {
	breadcrumbStore.set(route.matched)
})

</script>

<style scoped>

html, body {
	width: 100%;
	height: 100%;
}

body {
	margin: 0;
}

#app {
	height: 100%;
	width: 100%;
}
</style>
