import zh from './zh_cn'
import config from '@/config'
import {createI18n} from 'vue-i18n'

// 多语言配置
const messages = {

	'zh-cn': {
		...zh,
	}
}


// 创建i18n实例
const i18n = createI18n({
	legacy: false,
	locale: config.locale,
	fallbackLocale: 'en',
	messages,
	globalInjection: true,
})


export default i18n