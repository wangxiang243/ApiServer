package com.app.utils;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by wangxiang2 on 14-6-29.
 */
public class FileUtils {

    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    public static void saveFile(String fileDirPath, String newFileName, MultipartFile filedata) {

        File fileDir = new File(fileDirPath);

        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        try {
            FileOutputStream out = new FileOutputStream(fileDirPath + File.separator + newFileName);
            out.write(filedata.getBytes());
            out.flush();
            out.close();
        } catch (Exception e) {
            logger.error("保存上传图片异常");
            e.printStackTrace();
        }
    }

    public static boolean createJPEGFile4zip(String fileDirPath, String newFileName, BufferedImage buffImg) {
        File fileDir = new File(fileDirPath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        OutputStream jpgOs = null;
        try {
            jpgOs = new FileOutputStream(fileDirPath + File.separator + newFileName);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(jpgOs);
            encoder.encode(buffImg);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (jpgOs != null) {
                try {
                    jpgOs.close();
                } catch (IOException e) {
                    logger.error("保存二维码图片异常");
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

}
