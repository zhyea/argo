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
				name: '组件模型列表',
				index: 'fcm-list',
				uri: '/fcm-list',
			}, {
				name: '新增组件模型',
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
				index: 'self-info',
				uri: '/self-info',
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
			icon: 'cpu',
			index: 'app-fcm-list',

			children: [{
				name: '组件模型列表',
				index: 'app-fcm-list',
				uri: '/fcm-list',
			}, {
				name: '新增组件模型',
				index: 'fcm-add',
				uri: '/fcm-add',
			}]
		}, {
			name: '组件实例',
			icon: 'set-up',
			index: 'fci-main',

			children: [{
				name: '组件实例列表',
				index: 'fci-list',
				uri: '/fci-list',
			}, {
				name: '新增组件实例',
				index: 'fci-add',
				uri: '/fci-add',
			}]
		}, {
			name: '应用页',
			icon: 'office-building',
			index: 'app-page-main',

			children: [{
				name: '应用页面列表',
				index: 'app-page-list',
				uri: '/app-page-list',
			}, {
				name: '新增页面实例',
				index: 'app-page-add',
				uri: '/app-page-add',
			}]
		}, {
			name: '用户组',
			icon: 'User',
			uri: '#',
			index: 'user-group',
		}
	],
}