import {createI18n} from 'vue-i18n'
import zh from './zh_cn'
import config from '@/config'

// 多语言配置
const messages = {

	'zh-cn': {
		...zh,
	}

}

// 创建i18n实例
export default createI18n({
	locale: config.locale,
	fallbackLocale: 'en',
	messages
})
