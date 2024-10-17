import {fileURLToPath, URL} from 'node:url'

import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'

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

		// ElementPlus按需引入
		ElementPlus(),
		// 图标
		Icons(),
	],

	resolve: {
		alias: {
			// 配置路径别名
			'@': fileURLToPath(new URL('./src', import.meta.url))
		}
	}
})
