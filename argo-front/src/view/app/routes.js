import AppListPage from './AppList.vue'
import AppEditPage from './AppEdit.vue'


export default [{
	name: 'AppList',
	path: '/app-list',
	component: AppListPage
}, {
	name: 'AppAdd',
	path: '/app-add',
	component: AppEditPage
}]