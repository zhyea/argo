import AppEditPage from './AppEdit.vue'
import FcmListPage from '../fcm/FcmList.vue'
import FcmEditPage from '../fcm/FcmEdit.vue'

export default [{
	name: 'AppEdit',
	path: '/app-edit/:appId',
	component: AppEditPage,
	meta: {
		title: '新增应用',
	},
}, {
	name: 'AppFcmList',
	path: '/app-fcm-list/:appId',
	component: FcmListPage,
	meta: {
		title: '模型列表',
	},
}, {
	name: 'AppFcmAdd',
	path: '/app-fcm-add/:appId',
	component: FcmEditPage,
	meta: {
		title: '新增组件模型',
	},
}, {
	name: 'AppFcmEdit',
	path: '/app-fcm-edit/:appId/:fcmId',
	component: FcmEditPage,
	meta: {
		title: '编辑组件模型',
	},
},]