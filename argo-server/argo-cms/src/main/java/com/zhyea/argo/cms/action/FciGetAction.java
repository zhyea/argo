package com.zhyea.argo.cms.action;

import com.zhyea.argo.cms.model.item.FciItem;
import com.zhyea.argo.cms.model.item.FciPropItem;
import com.zhyea.argo.cms.service.FciPropService;
import com.zhyea.argo.cms.service.FciService;
import com.zhyea.argo.constants.enums.DataBindFlagEnum;
import com.zhyea.argo.tools.ParallelKit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 获取页面组件信息相关操作
 *
 * @author zhangrui
 * @since 2025/9/17
 */
@Slf4j
@Component
public class FciGetAction {


    private final FciService fciService;
    private final FciPropService fciPropService;


    @Autowired
    public FciGetAction(FciService fciService, FciPropService fciPropService) {
        this.fciService = fciService;
        this.fciPropService = fciPropService;
    }


    public FciItem getById(Long id) {
        List<Object> result = ParallelKit.executeIgnoreErrors(() -> fciService.getById(id),
                () -> fciPropService.findInEffectAndPendingByFciId(id));
        boolean existsInheritedProps = false;
        if (null != result.get(1)) {
            List<FciPropItem> fciProps = (List<FciPropItem>) result.get(1);
            existsInheritedProps = fciProps.stream().anyMatch(prop -> DataBindFlagEnum.INHERIT.is(prop.getDataBindFlag()));
        }

        FciItem fciItem = null;
        if (null != result.get(0)) {
            fciItem = (FciItem) result.getFirst();
            fciItem.setExistsInheritedProps(existsInheritedProps);
        }
        return fciItem;
    }
}
