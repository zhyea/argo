import {createApp} from 'vue'
import {createPinia} from 'pinia'

import * as icons from '@element-plus/icons-vue'


import App from './App.vue'
import router from './router'

import './assets/style.css'


const app = createApp(App)

app.use(router).use(createPinia())

for (const iconName in icons) {
	if (Reflect.has(icons, iconName)) {
		app.component(iconName, icons[iconName])
	}
}

app.mount('#app')
