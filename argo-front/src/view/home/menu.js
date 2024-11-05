export default {

	home: [
		{
			name: 'Dashboard',
			icon: 'platform',
			index: '/home',
		}, {
			name: '应用',
			icon: 'grid',
			index: '/app-main',

			children: [{
				name: '应用列表',
				index: '/app-list',
			}, {
				name: '新增应用',
				index: '/app-add',
			}]
		}, {
			name: '组件模型',
			icon: 'cpu',
			index: '/fcm-main',

			children: [{
				name: '组件模型列表',
				index: '/fcm-list',
			}, {
				name: '新增组件模型',
				index: '/fcm-add',
			}]
		}, {
			name: '用户',
			icon: 'user',
			index: '/user-main',

			children: [{
				name: '用户列表',
				index: '/user-list',
			}, {
				name: '新增用户',
				index: '/user-add',
			}, {
				name: '个人信息维护',
				index: '/self-info',
			}]
		}, {
			name: '系统设置',
			icon: 'Setting',
			index: '/setting-main',
			uri: '#',
		}
	],

	app: [
		{
			name: '组件模型',
			icon: 'cpu',
			index: '/app-fcm-list',

			children: [{
				name: '组件模型列表',
				index: '/app-fcm-list/:appId',
			}, {
				name: '新增组件模型',
				index: '/app-fcm-add/:appId',
			}]
		}, {
			name: '组件实例',
			icon: 'set-up',
			index: '/fci-main',

			children: [{
				name: '组件实例列表',
				index: '/fci-list/:appId',
			}, {
				name: '新增组件实例',
				index: '/fci-add/:appId',
			}]
		}, {
			name: '应用页',
			icon: 'office-building',
			index: '/app-page-main',

			children: [{
				name: '应用页面列表',
				index: '/app-page-list/:appId',
			}, {
				name: '新增页面实例',
				index: '/app-page-add/:appId',
			}]
		}, {
			name: '用户组',
			icon: 'User',
			index: '/user-group',
		}
	],
}