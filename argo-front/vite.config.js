import {fileURLToPath, URL} from 'node:url'

import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'


export default defineConfig({

	envDir: './env',
	envPrefix: ['VITE_', 'ARGO_'],

	define: {
		'import.meta.env.CUSTOM': '11111',
	},

	plugins: [
		vue(),
	],

	resolve: {
		alias: {
			'@': fileURLToPath(new URL('./src', import.meta.url))
		}
	}
})
