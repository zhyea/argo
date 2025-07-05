export default {

	system: [
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
			}]
		}, {
			name: '通用模型',
			icon: 'cpu',
			index: '/fcm-main',

			children: [{
				name: '组件模型列表',
				index: '/fcm-list',
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
			name: '应用首页',
			icon: 'grid',
			index: '/app/:appId',
		},
		{
			name: '组件模型',
			icon: 'cpu',
			index: '/app-fcm-list',

			children: [{
				name: '组件模型列表',
				index: '/app/:appId/app-fcm-list',
			},]
		}, {
			name: '组件实例',
			icon: 'set-up',
			index: '/fci-main',

			children: [{
				name: '组件实例列表',
				index: '/app/:appId/fci-list',
			},]
		}, {
			name: '应用页面',
			icon: 'office-building',
			index: '/app/:appId/page-main',

			children: [{
				name: '应用页面列表',
				index: '/app/:appId/page-list',
			},]
		}, {
			name: '用户组',
			icon: 'User',
			index: '/app/:appId/user-group',
		}
	],
}