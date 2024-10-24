import FcmListPage from './FcmList.vue'
import FcmEditPage from './FcmEdit.vue'


export default [{
	name: 'FcmList',
	path: '/fcm-list',
	component: FcmListPage,
	meta: {
		title: '模型列表',
	},
}, {
	name: 'FcmAdd',
	path: '/fcm-add',
	component: FcmEditPage,
	meta: {
		title: '新增模型',
	},
}]