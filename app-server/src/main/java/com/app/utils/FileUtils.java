package com.app.utils;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by wangxiang2 on 14-6-29.
 */
public class FileUtils {

    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    public static void saveFile(String fileDirPath, String newFileName, MultipartFile filedata) {
        logger.error("fileDirPath=" + fileDirPath);
        logger.error("newFileName=" + newFileName);
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

    public static ByteArrayOutputStream getOutputStream(String filePath) {
        ByteArrayOutputStream out = null;
        InputStream is = null;
        try {
            out = new ByteArrayOutputStream();
            is = new FileInputStream(filePath);
            byte[] buffer = new byte[1024];
            int r;
            while ((r = is.read(buffer)) != -1) {
                out.write(buffer, 0, r);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("关闭文件输出流异常");
            }
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("关闭文件输入流异常");
            }
        }
        return out;
    }

}
