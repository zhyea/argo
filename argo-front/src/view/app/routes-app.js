import AppEditPage from './AppEdit.vue'
import FcmListPage from '../fcm/FcmList.vue'
import FcmEditPage from '../fcm/FcmEditDrawer.vue'

export default [{
	name: 'AppEdit',
	path: '/app/:appId/edit',
	component: AppEditPage,
	meta: {
		title: '新增应用',
	},
}, {
	name: 'AppFcmList',
	path: '/app/:appId/app-fcm-list',
	component: FcmListPage,
	meta: {
		title: '模型列表',
	},
}, {
	name: 'AppFcmAdd',
	path: '/app/:appId/app-fcm-add',
	component: FcmEditPage,
	meta: {
		title: '新增组件模型',
	},
}, {
	name: 'AppFcmEdit',
	path: '/app/:appId/app-fcm-edit/:fcmId',
	component: FcmEditPage,
	meta: {
		title: '编辑组件模型',
	},
},]