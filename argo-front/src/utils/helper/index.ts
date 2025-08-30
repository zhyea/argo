/**
 * 通用工具
 */
import {Ref} from "vue";
import {ElMessage} from "element-plus";
import type {FormInstance} from "element-plus";
import {useEnumStore} from "@/store/enum";
import {MenuItem} from "@/model/route";
import routers from "@/view/home/routes";
import {TagItem} from "@/model/tag";
import i18n from "@/lang";
import {useTagStore} from "@/store/tag";
import {Router} from "vue-router";
import {cloneDeep} from "lodash-es";


/**
 * 提交表单
 * @param formRef 表单引用
 * @param formData 表单数据
 * @param submitFlag 提交状态标志
 * @param maintainMethod 远程交互的方法
 * @param extraAction 额外的操作
 */
export function submitForm<T extends Record<string, any>>(
	formRef: { value: FormInstance },
	formData: T,
	submitFlag: Ref<boolean>,
	maintainMethod: (data: T) => Promise<{ data: any }>,
	extraAction?: () => void
): void {
	formRef.value.validate().then((valid) => {
		if (!valid) {
			console.log("表单验证失败");
			return;
		}

		submitFlag.value = true;

		maintainMethod(formData).then((response) => {
			if (response.data) {
				ElMessage.success({
					message: "保存成功",
					duration: 1500,
				});

				if (extraAction) {
					extraAction();
				}
			} else {
				console.log("submit failed");
				submitFlag.value = false;
			}
		}).catch(error => {
			console.log("submit failed", error);
			submitFlag.value = false;
		})
	}).catch((error) => {
		console.log("表单验证失败", error);
	});
}


/**
 * 根据name获取路由
 * @param name
 * @returns {*}
 */
export const routeByName = (name: string): MenuItem => {

	let router: MenuItem;
	const find = (routers: MenuItem[], name: string) => {
		for (const item of routers) {
			if (item.name === name) {
				router = item
			}

			if (router) {
				break
			}

			if (item.children && item.children.length > 0) {
				find(item.children, name)
			}
		}
		return router;
	}

	return find(routers, name)
}


export const routeFormatTag = (route: MenuItem): TagItem => {

	if (!route) {
		throw new Error('Route parameter is required');
	}

	const {meta} = route;

	return {
		name: route.name,
		fullPath: route.fullPath,
		title: meta?.title ?? '',
		cache: meta?.cache ?? false,
		closable: meta?.closable ?? true,
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

/**
 * 获取标签标题
 * @param titleKey 标题键
 * @returns 标题
 */
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
 * 格式化有效期
 * @param row
 */
export function formatEffectivePeriod(row: any) {
	let result = '~'
	if (row.effectiveStartTime && row.effectiveStartTime !== '1970-01-01 08:00:00'
		&& row.effectiveEndTime && row.effectiveEndTime !== '1970-01-01 08:00:00') {
		result = row.effectiveStartTime + ' ~ ' + row.effectiveEndTime;
	} else if (row.effectiveStartTime && row.effectiveStartTime !== '1970-01-01 08:00:00') {
		result = row.effectiveStartTime + ' ~ ';
	}
	return result
}


/**
 * 数据绑定标志
 * @param row
 */
export function mapDataBindFlag(row: any) {
	return useEnumStore().getEnumDesc('YesOrNo', row.dataBindFlag)
}


/**
 * 获取枚举描述
 * @param enumName 枚举类名称
 * @param enumCode 枚举值
 */
export function getEnumDesc(enumName: string, enumCode: number) {
	return useEnumStore().getEnumDesc(enumName, enumCode)
}


/**
 * 调整菜单路由路径
 * @param menus 菜单信息
 * @param appId 项目ID
 */
export function fixMenuRoutes(menus: any, appId: number): any {
	menus = cloneDeep(menus)

	if (!appId || !menus || menus.length === 0) return;

	menus.forEach((menu: any) => {
		if (menu.index) {
			menu.index = menu.index.replace(':appId', appId);
		}

		if (!menu.children) return;

		menu.children.forEach((child: any) => {
			if (child.index) {
				child.index = child.index.replace(':appId', appId);
			}
		})
	})

	return menus;
}


/**
 * 跳转到项目
 *
 * @param appId 项目ID
 * @param router 路径
 */
export async function goToApp(appId: number, router: Router) {
	const tagStore = useTagStore();
	tagStore.clearTags();
	await router.push({path: '/app/' + appId});
	//router.go(0);
}