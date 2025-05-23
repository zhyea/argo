/**
 * 通用工具
 */
import {ref, Ref} from "vue";
import {ElMessage} from "element-plus";
import type {FormInstance} from "element-plus";

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
				submitFlag.value = false;
			}
		}).catch((error) => {
			submitFlag.value = false;
			console.error("提交表单时发生错误:", error);
			ElMessage.error({
				message: "保存失败，请稍后重试",
				duration: 1500,
			});
		});
	}).catch((error) => {
		console.log("表单验证失败", error);
	});
}


/**
 * 将k/v 对转为Map对象
 * @param key key
 * @param value 值
 * @returns {Map<any, any>} Map对象
 */
export function mapOf(key: any, value: any) {
	const map = new Map()
	map.set(key, value)
	return map
}