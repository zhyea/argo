/**
 * 路由配置
 */
export declare class MenuItem implements Route {

	/**
	 * 路由唯一标识
	 */
	name: string;

	/**
	 * 路由路径
	 */
	path: string;

	/**
	 * 路由完整路径
	 */
	fullPath?: string;

	/**
	 * 路由组件
	 */
	component: any;

	/**
	 * 路由元信息
	 */
	meta?: RouteMeta;

	/**
	 * 子路由
	 */
	children?: MenuItem[];
}


/**
 * 路由元信息
 */
export declare interface RouteMeta {

	/**
	 * 路由标题
	 */
	title: string;

	/**
	 * 是否缓存
	 */
	cache?: boolean;

	/**
	 * 是否可关闭
	 */
	closable?: boolean;
}