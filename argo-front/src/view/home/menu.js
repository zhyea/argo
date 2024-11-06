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
				index: '/app/:appId/app-fcm-list',
			}, {
				name: '新增组件模型',
				index: '/app/:appId/app-fcm-add',
			}]
		}, {
			name: '组件实例',
			icon: 'set-up',
			index: '/fci-main',

			children: [{
				name: '组件实例列表',
				index: '/app/:appId/fci-list',
			}, {
				name: '新增组件实例',
				index: '/app/:appId/fci-add',
			}]
		}, {
			name: '应用页',
			icon: 'office-building',
			index: '/app/:appId/page-main',

			children: [{
				name: '应用页面列表',
				index: '/app/:appId/page-list',
			}, {
				name: '新增页面实例',
				index: '/app/:appId/page-add',
			}]
		}, {
			name: '用户组',
			icon: 'User',
			index: '/app/:appId/user-group',
		}
	],
}