<template>
	<el-container class="app-container">

		<div class="app-box"
		     v-for="item in appItems"
		     @click="goToAppPage(item.appId)">

			<div class="app-icon">
				<img :src="item.icon" alt="item.appName" class="app-icon-img"/>
			</div>

			<div class="app-title">
				{{ item.appName }}
			</div>

		</div>

	</el-container>
</template>

<script setup>
import {ref} from 'vue'

import {findAppList} from "@/api/app.js";
import {useRouter} from "vue-router";
import {config} from "@/config/index.js";

defineProps({
	appItems: Array,
})


let appItems = ref([])

// 获取应用信息
findAppList().then(response => {

	if (!response || !response.data) {
		return
	}

	let arr = response.data
	arr.forEach(item => {
		if (!item.icon) {
			item.icon = item.appName.charAt(0).toUpperCase()
		}
	})

	appItems.value = arr
})


const router = useRouter();

// 跳转到应用页面
const goToAppPage = (appId) => {
	router.push({name: config.appHomeRouteName, query: {'appId': appId}, replace: true})
}
</script>

<style scoped lang="less">

.app-container {
	display: flex;
}

.app-box {
	display: grid;
	width: 150px;
	height: 150px;
	justify-content: center;

	.app-icon {
		display: flex;
		margin: 20px 30px 0 30px;
		padding: 0;
		height: 90px;
		width: 90px;
		overflow: hidden;
		border: 3px solid #42b983;
		border-radius: 50%;
		background-color: #FFFFFF;
		align-items: center;
		justify-content: center;
		font-size: 36px;
		font-weight: bold;
		color: #42b983;
	}

	.app-icon-img {
		display: block;
		margin: 0;
		padding: 0;
		width: 80px;
		height: 80px;
	}

	.app-title {
		display: block;
		margin: 0 20px 6px 20px;
		padding: 0;
		width: 110px;
		height: 20px;
		overflow: hidden;
		text-align: center;
		font-family: 'Monospaced', 'serif';
		font-weight: bolder;
		color: #1a1a1a;
	}
}

.app-box:hover {
	background-color: #F0FFF0;
	cursor: pointer;

	.app-title {
		color: #888888;
	}
}
</style>