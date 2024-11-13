package com.zhyea.argo.cms.action;

import com.zhyea.argo.cms.model.item.FciPropItem;
import com.zhyea.argo.cms.model.request.fci.FciPropAddRequest;
import com.zhyea.argo.cms.model.request.fci.FciPropEditRequest;
import com.zhyea.argo.cms.service.FciPropService;
import com.zhyea.argo.constants.ResponseCode;
import com.zhyea.argo.constants.enums.EffectivePeriodTypeEnum;
import com.zhyea.argo.except.ArgoServerException;
import lombok.extern.slf4j.Slf4j;
import org.chobit.commons.utils.Collections2;
import org.chobit.commons.utils.JsonKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.zhyea.argo.constants.NumConstants.ONE;

/**
 * 组件属性维护
 *
 * @author robin
 */
@Slf4j
@Component
public class FciPropMaintainAction {


    private final FciPropService fciPropService;

    @Autowired
    public FciPropMaintainAction(FciPropService fciPropService) {
        this.fciPropService = fciPropService;
    }


    /**
     * 新增组件属性
     *
     * @param request 新增组件属性请求
     * @return 新增的组件属性id
     */
    public Long add(FciPropAddRequest request) {

        return null;
    }


    /**
     * 修改组件属性
     *
     * @param request 修改组件属性请求
     * @return 是否修改成功
     */
    public boolean edit(FciPropEditRequest request) {
        return false;
    }


    private void checkTime(FciPropAddRequest request) {

    }


    /**
     * 检查组件属性是否存在重叠（只考虑重叠不考虑断档，断档则不显示）
     *
     * @param propItems 组件属性列表
     */
    private void checkTimeOverlap(List<FciPropItem> propItems) {

        // 空记录无需检查
        if (Collections2.isEmpty(propItems)) {
            return;
        }

        // 只有一行记录时不可能存在交叉
        if (propItems.size() == ONE) {
            return;
        }

        // 过滤出短期有效记录并做排序
        List<FciPropItem> tmp = propItems.stream()
                .filter(e -> EffectivePeriodTypeEnum.FIXED_TERM.is(e.getEffectivePeriodType()))
                .sorted(Comparator.comparing(FciPropItem::getEffectiveStartTime)).collect(Collectors.toList());

        // 存在多条长期有效记录
        if (Collections2.isEmpty(tmp)) {
            throw new ArgoServerException(ResponseCode.FCI_PROP_EFFECTIVE_TIME_OVERLAP);
        }

        // 是否存在长期有效记录
        boolean existsLongTerm = propItems.size() > tmp.size();

        LocalDateTime t = null;
        for (FciPropItem item : tmp) {
            if (item.getEffectiveStartTime().isAfter(item.getEffectiveEndTime())
                    || item.getEffectiveStartTime().equals(item.getEffectiveEndTime())) {
                // 起止时间重叠
                logger.error("FciPropMaintainAction.checkTimeOverlap 组件属性起止时间有误 item:{}", JsonKit.toJson(item));
                throw new ArgoServerException(ResponseCode.FCI_PROP_EFFECTIVE_TIME_OVERLAP);
            }

            if (null != t && t.isAfter(item.getEffectiveStartTime())) {
                // 上次的结束时间大于本次的开始时间，说明存在重叠
                throw new ArgoServerException(ResponseCode.FCI_PROP_EFFECTIVE_TIME_OVERLAP);
            } else if (null == t) {
                t = item.getEffectiveEndTime();
            }

            if (item.getEffectiveEndTime().isAfter(LocalDateTime.now()) && existsLongTerm) {
                // 存在长期有效记录，且存在短期有效记录的结束时间大于当前时间，说明存在重叠
                throw new ArgoServerException(ResponseCode.FCI_PROP_EFFECTIVE_TIME_OVERLAP);
            }
        }
    }


}
