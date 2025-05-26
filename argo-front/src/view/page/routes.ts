export default [{
	name: 'AppPageList',
	path: '/app/:appId/page-list',
	component: () => import('./AppPageList.vue'),
	meta: {
		title: '应用页面',
	},
}]