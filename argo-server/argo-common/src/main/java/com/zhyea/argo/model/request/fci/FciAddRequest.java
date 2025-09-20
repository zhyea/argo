package com.zhyea.argo.model.request.fci;

import com.zhyea.argo.constants.enums.DataBindFlagEnum;
import com.zhyea.argo.constants.enums.FciUsageScopeEnum;
import com.zhyea.argo.constants.enums.YesOrNo;
import com.zhyea.argo.tools.Args;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.chobit.commons.contract.Checkable;
import org.chobit.commons.exception.ParamException;
import org.chobit.commons.validation.EnumVal;
import org.chobit.commons.validation.WholeCheck;

import static com.zhyea.argo.constants.ResponseCode.DATA_BIND_URL_IS_BLANK;
import static com.zhyea.argo.constants.ResponseCode.PROP_DATA_REQUEST_METHOD_IS_NULL;

/**
 * 组件实例新增请求
 *
 * @author robin
 */
@WholeCheck(message = "组件实例信息有误")
@Data
public class FciAddRequest extends BaseFciRequest implements Checkable {

	/**
	 * 应用ID
	 */
	@NotNull(message = "应用Id不能为空")
	private Long appId;


	/**
	 * 组件模型ID
	 */
	@NotNull(message = "fcmId不能为空")
	private Long fcmId;


	/**
	 * 组件名称
	 */
	@NotBlank(message = "name不能为空")
	private String name;


	/**
	 * 组件开关标志
	 */
	@EnumVal(enumClass = FciUsageScopeEnum.class, message = "使用范围取值错误")
	@NotNull(message = "使用范围不能为空")
	private Integer usageScope;


	/**
	 * 组件开关标志
	 */
	@EnumVal(enumClass = YesOrNo.class, message = "开关标志错误")
	private Integer switchFlag;


	/**
	 * 数据绑定标识
	 */
	@EnumVal(enumClass = DataBindFlagEnum.class, message = "数据绑定标识错误")
	@NotNull(message = "数据绑定标识不能为空")
	private Integer dataBindFlag;


	/**
	 * 备注
	 */
	private String remark;


	@Override
	public boolean check() throws ParamException {


		if (DataBindFlagEnum.BIND_DATA.is(getDataBindFlag())) {
			System.out.println(Args.class);
			Args.checkNotBlank(this.getDataUrl(), DATA_BIND_URL_IS_BLANK);
			Args.checkNotNull(this.getDataRequestMethod(), PROP_DATA_REQUEST_METHOD_IS_NULL);
		}

		return true;
	}
}
