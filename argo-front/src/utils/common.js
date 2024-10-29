/**
 * 通用工具
 */
import {ElMessage} from "element-plus";


/**
 * 提交表单
 * @param formRef 表单引用
 * @param formData 表单数据
 * @param submitFlag 提交标记
 * @param maintainMethod 远程交互的方法
 * @param extraAction 额外的操作
 */
export function submitForm(formRef,
                           formData,
                           submitFlag,
                           maintainMethod,
                           extraAction) {
	formRef.value.validate((valid) => {
		if (!valid) return

		submitFlag.value = true

		maintainMethod(formData, maintainMethod).then(response => {
			if (response.data) {
				ElMessage.success({
					message: '保存成功',
					duration: 1500,
				})

				if (extraAction) {
					extraAction()
				}

			} else {
				submitFlag.value = false
			}
		}).catch(error => {
			submitFlag.value = false
		})


	})
}