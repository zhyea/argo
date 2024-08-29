import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import {resolve} from "path"

import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import {ElementPlusResolver} from 'unplugin-vue-components/resolvers'

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

	],

	resolve: {
		alias: {
			'@': resolve(__dirname, './src'),
		}
	}
})
