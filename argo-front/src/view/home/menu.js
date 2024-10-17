export default {

	home: [
		{
			name: 'Home',
			icon: 'House',
			uri: '/home',

			children: [{
				name: '应用列表',
				icon: 'Grid',
				uri: '/app-list',
			}, {
				name: '组件模型',
				icon: 'Van',
				uri: '/fcm-list',
			}, {
				name: '系统设置',
				icon: 'Operation',
				uri: '#',
			}],
		},
	],

	app: [
		{
			name: '组件模型',
			icon: 'menu',
			uri: '/fcm-list',
		}, {
			name: '组件实例',
			icon: 'user',
			uri: '/fci-list',
		},{
			name: '用户组',
			icon:'User',
			uri: '#',
		}
	],
}