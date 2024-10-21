export default {

	home: [
		{
			name: 'Dashboard',
			icon: 'platform',
			index: 'platform-main',
			uri: "/home"
		}, {
			name: '应用',
			icon: 'grid',
			index: 'app-main',

			children: [{
				name: '应用列表',
				index: 'app-list',
				uri: '/app-list',
			}, {
				name: '新增应用',
				index: 'app-add',
				uri: '/app-add',
			}]
		}, {
			name: '组件模型',
			icon: 'cpu',
			index: 'fcm-main',

			children: [{
				name: '模型列表',
				index: 'fcm-list',
				uri: '/fcm-list',
			}, {
				name: '新增模型',
				index: 'fcm-add',
				uri: '/fcm-add',
			}]
		}, {
			name: '用户',
			icon: 'user',
			index: 'user-main',

			children: [{
				name: '用户列表',
				index: 'user-list',
				uri: '/user-list',
			}, {
				name: '新增用户',
				index: 'user-add',
				uri: '/user-add',
			}, {
				name: '个人信息维护',
				index: 'self-maintain',
				uri: '/self-maintain',
			}]
		}, {
			name: '系统设置',
			icon: 'Setting',
			index: 'setting-main',
			uri: '#',
		}
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
		}, {
			name: '用户组',
			icon: 'User',
			uri: '#',
		}
	],
}