export default [{
	name: 'AppList',
	path: '/app-list',
	component: () => import('./AppList.vue'),
	meta: {
		title: '应用列表',
	},
}, {
	name: 'AppAdd',
	path: '/app-add',
	component: () => import('./AppEdit.vue'),
	meta: {
		title: '新增应用',
	},
}]