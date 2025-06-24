<!-- 配置信息 -->

<template>
	<el-config-provider :locale="locale">
		<router-view/>
	</el-config-provider>
</template>

<script lang="ts" setup>
import {ref, onMounted, watch, computed} from 'vue';
import {useBreadcrumbStore} from '@/store/breadcrumb';
import {useRoute} from 'vue-router';
import {useI18n} from 'vue-i18n';
import {getLocale} from '@/utils/cache';
import {useConfStore} from '@/store/conf';
import {ROUTE_NAMES} from '@/config';
import {useTagStore} from '@/store/tag';
import zh from 'element-plus/es/locale/lang/zh-cn';
import en from 'element-plus/es/locale/lang/en';
import {routeFormatTag} from '@/utils/helper';
import {useAppStore} from "@/store/app";

const route = useRoute();
const breadcrumbStore = useBreadcrumbStore();
const confStore = useConfStore();
const tagStore = useTagStore();

onMounted(async () => {
	breadcrumbStore.set(route.matched);
})


const i18n = useI18n();
getLocale().then(lang => {
	if (!lang) {
		return;
	}
	confStore.setLocale(lang)
	i18n.locale.value = lang
})


watch(route, () => {

	if (route.name === ROUTE_NAMES.loginRouteName) {
		return;
	}

	breadcrumbStore.set(route.matched)

	const tag = routeFormatTag(route)
	tagStore.openTagView(tag)
})


const locale = ref(computed(() => {
	const locale = confStore.locale === 'en' ? en : zh
	console.log(`the locale of app: ${locale}`);
	return locale
}))

</script>

<style lang="less" scoped>

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
