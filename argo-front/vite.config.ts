import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'

import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import ElementPlus from 'unplugin-element-plus/vite'
import {ElementPlusResolver} from 'unplugin-vue-components/resolvers'
import Icons from 'unplugin-icons/vite'
import {fileURLToPath} from "mlly";

export default defineConfig({

	envDir: './env',
	envPrefix: ['VITE_', 'ARGO_'],

	define: {
		// custom env vars
		'import.meta.env.CUSTOM': '11111',
	},


	plugins: [
		// 核心插件
		vue(),

		// ElementPlus 相关插件
		AutoImport({
			resolvers: [
				ElementPlusResolver(),
			],
		}),

		// 自动注册ElementPlus组件
		Components({
			resolvers: [
				ElementPlusResolver(),
			],
		}),

		// 按需定制主题配置
		ElementPlus({
			useSource: true,
		}),

		// 图标插件
		Icons(),
	],

	resolve: {
		alias: [
			{find: '@', replacement: fileURLToPath(new URL('./src', import.meta.url))}
		]
	},


	css: {
		preprocessorOptions: {
			scss: {
				api: 'modern-compiler', // or 'modern'
			},
		},
	},

})
