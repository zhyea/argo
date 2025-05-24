import {createApp} from 'vue'
import {createPinia} from 'pinia'
import i18n from '@/lang/index'

import * as icons from '@element-plus/icons-vue'


import App from './App.vue'
import router from '@/router'

import './assets/style.css'


const app = createApp(App)
const pinia = createPinia()

app.use(router).use(pinia).use(i18n)


for (const [iconName, iconComponent] of Object.entries(icons as Record<string, any>)) {
	app.component(iconName, iconComponent)
}

app.mount('#app')
