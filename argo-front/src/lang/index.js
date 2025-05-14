import { createI18n } from 'vue-i18n'
import zh from './zh_cn'
import config from '@/config'

const messages = {

  'zh-cn': {
    ...zh,
  }
}


export default createI18n({
  locale: config.locale,
  fallbackLocale: 'en',
  legacy: false,
  messages
})
