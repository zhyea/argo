<!-- 配置信息 -->

<template>
	<router-view/>
</template>

<script setup>
import {ref, onMounted, watch, computed} from 'vue'
import {useBreadcrumbStore} from "@/store/breadcrumb";
import {useRoute} from "vue-router";
import {useI18n} from "vue-i18n";
import {getLocale} from "@/utils/localforage";
import {useAppStore} from "@/store/app";
import {ROUTE_NAMES} from "@/config/index";
import {useTagStore} from "@/store/tag";
import zh from 'element-plus/es/locale/lang/zh-cn'
import en from 'element-plus/es/locale/lang/en'
import {routeFormatTag} from "@/utils/helper/index";

const route = useRoute();
const breadcrumbStore = useBreadcrumbStore();
const appStore = useAppStore();
const tagStore = useTagStore();

onMounted(() => {
	breadcrumbStore.set(route.matched);
})


const i18n = useI18n();
getLocale().then(lang => {
	if (!lang) {
		return;
	}
	appStore.setLocale(lang)
	i18n.locale.value = lang
})

watch(route, () => {
	breadcrumbStore.set(route.matched)
	if (route.name === ROUTE_NAMES.homeRouteName
		|| route.name === ROUTE_NAMES.loginRouteName) {
		return;
	}

	const tag = routeFormatTag(route)
	tagStore.openTagView(tag)
})


const locale = ref(computed(() => {
	return appStore.locale === getLocale() ? zh : en
}))


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
