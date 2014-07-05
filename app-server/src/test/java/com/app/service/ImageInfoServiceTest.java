package com.app.service;

import com.app.BaseSpringContext;
import com.app.entity.ImageInfo;
import com.app.utils.pagination.model.Paging;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangxiang2 on 14-7-5.
 */
public class ImageInfoServiceTest extends BaseSpringContext {
    @Resource
    private ImageInfoService imageInfoService;

    @Test
    public void queryPagedImageInfoTest(){
        Paging<ImageInfo> page = new Paging<ImageInfo>(1);
        List<ImageInfo> list = imageInfoService.queryPagedImageInfo(page);
        System.out.println(list);
    }
}
