import routers, {RouteItem} from '@/router/routeConfig';
import i18n from '@/lang/index.js'
import {ElMessage} from "element-plus";
import {Ref} from "vue";
import config from "@/config";


/**
 * 根据name获取路由
 * @param name
 * @returns {*}
 */
export const routeByName = (name: string): RouteItem => {

	let router: RouteItem;
	const each = (routers: RouteItem[], name: string) => {
		for (const item of routers) {
			if (item.name === name) {
				router = item
			}

			if (router) {
				break
			}

			if (item.children && item.children.length > 0) {
				each(item.children, name)
			}
		}
		return router;
	}

	return each(routers, name)
}


export const routeFormatTag = (route: RouteItem) => {
	return {
		name: route.name,
		fullPath: route.fullPath,
		title: route.meta && route.meta.title ? route.meta.title : '',
		cache: route.meta && route.meta.cache,
		closable: route.meta && !route.meta.notClosable,
	}
}


// export const getCascaderDefaultIds = (node) => {
// 	let ids = []
// 	let tempNode = node
// 	while (tempNode.data.parent_id) {
// 		ids.push(tempNode.data.parent_id)
// 		tempNode = tempNode.parent
// 	}
//
// 	return ids.reverse()
// }
//
//
// export const getNodeParentPath = (id, nodes, path = {}) => {
// 	for (let i = 0; i < nodes.length; i++) {
// 		if (path.status) {
// 			break
// 		}
//
// 		let node = nodes[i]
// 		if (node.parent_id === 0) {
// 			path.ids = []
// 		}
//
// 		if (i === 0 && node.parent_id > 0) {
// 			path[node.parent_id] = [...path.ids]
// 		}
//
// 		if (id === node.id) {
// 			path.status = true
// 			path.ids = path.hasOwnProperty(node.parent_id) ? [...path[node.parent_id]] : []
// 			break
// 		} else {
// 			path.ids.push(node.id)
// 		}
//
// 		if (node.children) {
// 			getNodeParentPath(id, node.children, path)
// 		}
// 	}
// }


export const getTagTitleName = (titleKey: string) => {
	// 校验 titleKey 是否有效
	if (!titleKey) {
		return titleKey;
	}

	const metaKey = `meta.title.${titleKey}`;

	try {
		// 检查是否存在对应的国际化键
		const hasKey = i18n.global.te(metaKey);		// 返回对应的国际化文本或原始 titleKey
		return hasKey ? i18n.global.t(metaKey) : titleKey;
	} catch (error) {
		// 异常处理，防止程序崩溃
		console.error('Error in getTagTitleName:', error);
		return titleKey;
	}
}


/**
 * 提交表单
 * @param formRef 表单引用
 * @param formData 表单数据
 * @param submitFlag 提交标记
 * @param maintainMethod 远程交互的方法
 * @param extraAction 额外的操作
 */
export async function submitForm(formRef: Ref<any>,
                                 formData: any,
                                 submitFlag: Ref<boolean>,
                                 maintainMethod: (data: any) => Promise<any>,
                                 extraAction?: () => void) {
	try {
		const isValid = await formRef.value.validate();
		if (!isValid) return;

		submitFlag.value = true;

		const response = await maintainMethod(formData);
		if (response.data) {
			ElMessage.success({
				message: '保存成功',
				duration: 1500,
			});

			extraAction?.();
		} else {
			submitFlag.value = false;
		}
	} catch (error) {
		submitFlag.value = false;
	}
}


/**
 * 将k/v 对转为Map对象
 * @param key key
 * @param value 值
 * @returns {Map<any, any>} Map对象
 */
export function mapOf<K, V>(key: K, value: V): Map<K, V> {
	const map = new Map<K, V>();
	map.set(key, value);
	return map;
}