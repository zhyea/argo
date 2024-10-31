package com.zhyea.argo.cms.model.request.fcm;

import com.zhyea.argo.cms.model.item.FcmPropItem;
import com.zhyea.argo.cms.model.request.BaseOperateRequest;
import com.zhyea.argo.constants.enums.FcScopeEnum;
import com.zhyea.argo.constants.enums.FcmTypeEnum;
import com.zhyea.argo.constants.enums.YesOrNo;
import lombok.Data;
import org.chobit.commons.contract.Checkable;
import org.chobit.commons.exception.ParamException;
import org.chobit.commons.validation.EnumVal;
import org.chobit.commons.validation.WholeCheck;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * 前端组件模型新增请求
 *
 * @author robin
 */
@WholeCheck(message = "组件模型相关信息有误")
@Data
public class FcmAddRequest extends BaseOperateRequest implements Checkable {


    /**
     * 组件名称
     */
    @NotBlank(message = "组件名称不能为空")
    private String name;


    /**
     * 图标
     */
    private String icon;


    /**
     * 类型
     */
    @EnumVal(enumClass = FcmTypeEnum.class, message = "组件类型错误")
    @NotNull(message = "组件类型不能为空")
    private Integer type;


    /**
     * 作用域
     */
    @EnumVal(enumClass = FcScopeEnum.class, message = "组件作用域错误")
    @NotNull(message = "组件作用域不能为空")
    private Integer scope;


    /**
     * 应用ID
     */
    private Long appId;


    /**
     * 属性
     */
    @Valid
    @NotEmpty(message = "组件属性不能为空")
    private Set<FcmPropItem> props;


    /**
     * 是否需要绑定数据标记
     */
    @EnumVal(enumClass = YesOrNo.class, message = "是否需要绑定数据标记错误")
    @NotNull(message = "是否需要绑定数据标记不能为空")
    private Integer dataBindFlag;


    /**
     * 组件备注
     */
    private String remark;


    @Override
    public boolean check() throws ParamException {
        // 如果是应用组件，则应用ID不能为空
        if (FcScopeEnum.APP.is(this.scope)) {
            return null != appId && appId > 0;
        }
        return true;
    }
}
