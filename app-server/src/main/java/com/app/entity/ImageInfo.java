package com.app.entity;

import java.util.Date;

public class ImageInfo {
    private Integer id;

    private String orignalImageName;

    private String imageName;

    private String imagePath;

    private String qrName;

    private String dimensionCodePath;

    private Date uploadDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrignalImageName() {
        return orignalImageName;
    }

    public void setOrignalImageName(String orignalImageName) {
        this.orignalImageName = orignalImageName == null ? null : orignalImageName.trim();
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName == null ? null : imageName.trim();
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath == null ? null : imagePath.trim();
    }

    public String getQrName() {
        return qrName;
    }

    public void setQrName(String qrName) {
        this.qrName = qrName == null ? null : qrName.trim();
    }

    public String getDimensionCodePath() {
        return dimensionCodePath;
    }

    public void setDimensionCodePath(String dimensionCodePath) {
        this.dimensionCodePath = dimensionCodePath == null ? null : dimensionCodePath.trim();
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }
}