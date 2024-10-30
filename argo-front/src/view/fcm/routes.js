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
		title: '新增组件模型',
	},
}, {
	name: 'FcmEdit',
	path: '/fcm-edit',
	component: FcmEditPage,
	meta: {
		title: '编辑组件模型',
	},
}]