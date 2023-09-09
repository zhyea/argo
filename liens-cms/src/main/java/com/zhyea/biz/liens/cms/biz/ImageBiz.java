package com.zhyea.biz.liens.cms.biz;

import com.zhyea.biz.liens.cms.model.ImageInf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * 图片业务类
 *
 * @author robin
 */
@Slf4j
@Component
public class ImageBiz {


    /**
     * 获取轮播图
     *
     * @return 轮播图图片
     */
    public List<ImageInf> getSwiper() {
        List<ImageInf> result = new LinkedList<>();

        ImageInf img1 = new ImageInf();
        img1.setId(1L);
        img1.setUrl("https://image.baidu.com/search/down?url=https://tvax3.sinaimg.cn/large/006BNqYCly1hhlg48ym5mj335s1s0b2a.jpg");

        result.add(img1);

        return result;
    }


}
