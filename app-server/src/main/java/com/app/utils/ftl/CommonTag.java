package com.app.utils.ftl;

import org.springframework.stereotype.Service;

@Service
public class CommonTag {
	
	private String imageHostPath;

    private String resPath;

    public String getImageHostPath() {
        return imageHostPath;
    }

    public void setImageHostPath(String imageHostPath) {
        this.imageHostPath = imageHostPath;
    }

    public String getResPath() {
        return resPath;
    }

    public void setResPath(String resPath) {
        this.resPath = resPath;
    }

    public String res(String path){
        return path == null ? resPath : resPath+path;
    }
}
