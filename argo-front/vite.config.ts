import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import path from 'path'

import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import ElementPlus from 'unplugin-element-plus/vite'
import {ElementPlusResolver} from 'unplugin-vue-components/resolvers'
import Icons from 'unplugin-icons/vite'

export default defineConfig({

	envDir: './env',
	envPrefix: ['VITE_', 'ARGO_'],

	define: {
		'import.meta.env.CUSTOM': '11111',
	},

	plugins: [

		vue(),

		// 自动导入ElementPlus组件
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

		// 图标
		Icons(),
	],

	resolve: {
		alias: [
			{find: '@', replacement: path.resolve(__dirname, 'src')}
		]
	},
})
