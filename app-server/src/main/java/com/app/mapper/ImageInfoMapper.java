package com.app.mapper;

import com.app.entity.ImageInfo;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface ImageInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ImageInfo record);

    int insertSelective(ImageInfo record);

    ImageInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ImageInfo record);

    int updateByPrimaryKey(ImageInfo record);

    List<ImageInfo> queryAllImageInfo();

    List<ImageInfo> queryPagedImageInfo(RowBounds rows);
}