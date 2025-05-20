import AppPageList from './AppPageList.vue'


export default [{
	name: 'AppPageList',
	path: '/app/:appId/page-list',
	component: AppPageList,
	meta: {
		title: '应用页面',
	},
}]