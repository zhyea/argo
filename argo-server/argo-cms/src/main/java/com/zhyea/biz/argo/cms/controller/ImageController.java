package com.zhyea.biz.argo.cms.controller;


import com.zhyea.biz.argo.cms.biz.ImageBiz;
import com.zhyea.biz.argo.cms.model.ImageInf;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 图片接口
 *
 * @author robin
 */
@RestController
@RequestMapping("/img")
public class ImageController {


    @Resource
    private ImageBiz imageBiz;


    @GetMapping("/swiper")
    public List<ImageInf> swiper() {
        return imageBiz.getSwiper();
    }

}