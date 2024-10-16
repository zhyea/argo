export default {

	home: [
		{
			name: 'Home',
			icon: 'Platform',
			uri: '/home',

			children: [{
				name: '应用列表',
				icon: 'menu',
				uri: '/app-list',
			}, {
				name: '组件模型',
				icon: 'user',
				uri: '/fcm-list',
			}, {
				name: '系统设置',
				icon: 'setting',
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
			icon:'setting',
			uri: '#',
		}
	],
}