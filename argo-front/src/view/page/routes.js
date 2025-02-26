import AppListPage from './AppList.vue'
import AppEditPage from './AppEdit.vue'


export default [{
	name: 'AppPageList',
	path: '/app-list',
	component: AppListPage,
	meta: {
		title: '应用列表',
	},
}, {
	name: 'AppAdd',
	path: '/app-add',
	component: AppEditPage,
	meta: {
		title: '新增应用',
	},
}]