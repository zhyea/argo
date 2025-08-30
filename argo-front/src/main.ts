import {createApp} from 'vue'
import {createPinia} from 'pinia'

import * as icons from '@element-plus/icons-vue'
import i18n from '@/lang/index'


import App from './App.vue'
import ROUTER from '@/router'

import './assets/style.css'


const app = createApp(App)
const pinia = createPinia()


app.use(ROUTER).use(pinia).use(i18n)


for (const [iconName, iconComponent] of Object.entries(icons as Record<string, any>)) {
	app.component(iconName, iconComponent)
}

app.mount('#app')
