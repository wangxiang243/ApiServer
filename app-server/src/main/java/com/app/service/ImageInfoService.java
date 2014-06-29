package com.app.service;

import com.app.entity.ImageInfo;
import com.app.mapper.ImageInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by wangxiang2 on 14-6-24.
 */
@Service
public class ImageInfoService {

    @Resource
    private ImageInfoMapper imageInfoMapper;

    public void insertSelective(ImageInfo imageInfo) {
        imageInfoMapper.insertSelective(imageInfo);
    }

}