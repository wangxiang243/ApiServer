package com.app.service;

import com.app.entity.ImageInfo;
import com.app.mapper.ImageInfoMapper;
import com.app.utils.pagination.model.Paging;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    public List<ImageInfo> queryAllImageInfo(){
        return imageInfoMapper.queryAllImageInfo();
    }

    public List<ImageInfo> queryPagedImageInfo(Paging<ImageInfo> page){
        return imageInfoMapper.queryPagedImageInfo(page.getRowBounds());
    }
}
