package com.zhyea.argo.cms.model.request.fci;

import com.zhyea.argo.constants.enums.EffectivePeriodTypeEnum;
import com.zhyea.argo.constants.enums.YesOrNo;
import lombok.Data;
import org.chobit.commons.exception.ParamException;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static org.chobit.commons.utils.StrKit.isBlank;


/**
 * FCI属性修改请求
 *
 * @author robin
 */
@Data
public class FciPropEditRequest extends FciPropAddRequest {


    /**
     * 属性Id
     */
    @NotNull(message = "属性Id不能为空")
    private Long propId;


    @Override
    public boolean check() throws ParamException {

        if (YesOrNo.YES.is(getDataBindFlag()) && isBlank(getPropValueSelector())) {
            return false;
        }

        if (EffectivePeriodTypeEnum.FIXED_TERM.is(getEffectivePeriodType())) {
            LocalDateTime effectiveStartTime = getEffectiveStartTime();
            LocalDateTime effectiveEndTime = getEffectiveEndTime();

            if (null == effectiveStartTime || null == effectiveEndTime) {
                return false;
            }
            // 开始时间不能大于结束时间
            if (!effectiveEndTime.isAfter(effectiveStartTime)) {
                return false;
            }

            // 编辑时，结束时间不能<当前时间
            if (effectiveEndTime.isBefore(LocalDateTime.now())
                    || effectiveEndTime.isEqual(LocalDateTime.now())) {
                return false;
            }
        }

        return true;
    }
}
