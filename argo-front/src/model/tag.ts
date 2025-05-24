import {MenuItem} from "@/model/route";


export declare interface TagItem {

	/**
	 * 标签唯一标识
	 */
	name: string;

	/**
	 * 标签标题
	 */
	title: string;

	/**
	 * 标签完整路径
	 */
	fullPath?: string;

	/**
	 * 是否缓存
	 */
	cache?: boolean;

	/**
	 * 是否不可关闭
	 */
	closable?: boolean;

	/**
	 * 标签路由
	 */
	route?: MenuItem;
}