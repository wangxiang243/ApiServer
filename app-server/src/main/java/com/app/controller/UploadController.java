package com.app.controller;

import com.app.entity.ImageInfo;
import com.app.service.ImageInfoService;
import com.app.utils.FileUtils;
import com.app.utils.QRCodeHelper;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Created by wangxiang2 on 14-6-24.
 */
@Controller
@RequestMapping("/app")
public class UploadController {

    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    private static final long IMAGE_SIZE = 50 << 20;

    @Value("${app.imageHostPath}")
    private String imageHostPath;

    @Value("${app.imagePath}")
    private String imagePath;

    @Value("${app.imageType}")
    private String imageType;

    @Value("${app.qrCodeheight}")
    private int qrCodeheight;

    @Value("${app.qrCodewidth}")
    private int qrCodewidth;

    @Resource
    private ImageInfoService imageInfoService;

    /**
     * 上传图片返回JSON格式数据
     *
     * @param imageFile
     * @param request
     * @return
     */
    @RequestMapping("uploadImageRetJson")
    @ResponseBody
    public Map<String, String> uploadImageRetJson(MultipartFile imageFile, HttpServletRequest request) {

        Map<String, String> resultMap = Maps.newHashMap();
        if (imageFile.getSize() == 0) {
            logger.error("上传图片不能为空");
            return ImmutableMap.<String, String>of("result", "false", "msg", "上传图片不能为空");
        }
        if (imageFile.getSize() > IMAGE_SIZE) {
            logger.error("图片太大");
            return ImmutableMap.<String, String>of("result", "false", "msg", "图片太大");
        }
        String originalName = imageFile.getOriginalFilename();
        String imageSuffix = originalName.substring(originalName.lastIndexOf(".") + 1);

        if (!imageType.contains(imageSuffix.toLowerCase())) {
            logger.error("非法的图片");
            return ImmutableMap.<String, String>of("result", "false", "msg", "非法的图片");
        }

        String webappPath = imagePath + File.separator +
                new SimpleDateFormat("yyyyMMdd").format(new Date());
        String imageName = UUID.randomUUID().toString() + "." + imageSuffix;

        String imageRealPath = request.getSession().getServletContext().getRealPath(webappPath);
        String imageRelativePath = webappPath + File.separator + imageName;
        String imageShowPath = imageHostPath + File.separator + imageRelativePath;

        //保存图片
        FileUtils.saveFile(imageRealPath, imageName, imageFile);

        //生成二维码
        String qrName = UUID.randomUUID().toString() + ".jpg";
        BufferedImage qrImage = QRCodeHelper.createImage(imageShowPath, qrCodeheight, qrCodewidth);
        FileUtils.createJPEGFile4zip(imageRealPath, qrName, qrImage);
        String qrRelativePath = webappPath + File.separator + qrName;
        String qrImageShowPath = imageHostPath + File.separator + qrRelativePath;

        //保存记录
        ImageInfo imageInfo = new ImageInfo();
        imageInfo.setDimensionCodePath(qrRelativePath);
        imageInfo.setImageName(imageName);
        imageInfo.setQrName(qrName);
        imageInfo.setImagePath(imageRelativePath);
        imageInfo.setOrignalImageName(originalName);
        imageInfoService.insertSelective(imageInfo);

        resultMap.put("result", "true");
        resultMap.put("msg", "保存成功");
        resultMap.put("qrImageShowPath", qrImageShowPath);
        resultMap.put("imageShowPath", imageShowPath);
        return resultMap;
    }

    /**
     * 上传图片返回输出流数据
     *
     * @param imageFile
     * @param request
     * @return
     */
    @RequestMapping("uploadImageRetStream")
    public void uploadImageRetStream(MultipartFile imageFile, HttpServletRequest request, HttpServletResponse response) throws IOException {

        ServletOutputStream out = response.getOutputStream();
        response.setContentType("image/gif");

//        Map<String, String> resultMap = Maps.newHashMap();
//        if (imageFile.getSize() == 0) {
//            logger.error("上传图片不能为空");
//            return ImmutableMap.<String, String>of("result", "false", "msg", "上传图片不能为空");
//        }
//        if (imageFile.getSize() > IMAGE_SIZE) {
//            logger.error("图片太大");
//            return ImmutableMap.<String, String>of("result", "false", "msg", "图片太大");
//        }
        String originalName = imageFile.getOriginalFilename();
        String imageSuffix = originalName.substring(originalName.lastIndexOf(".") + 1);
//
//        if (!imageType.contains(imageSuffix.toLowerCase())) {
//            logger.error("非法的图片");
//            return ImmutableMap.<String, String>of("result", "false", "msg", "非法的图片");
//        }

        String webappPath = imagePath + File.separator +
                new SimpleDateFormat("yyyyMMdd").format(new Date());
        String imageName = UUID.randomUUID().toString() + "." + imageSuffix;

        String imageRealPath = request.getSession().getServletContext().getRealPath(webappPath);
        String imageRelativePath = webappPath + File.separator + imageName;
        String imageShowPath = imageHostPath + File.separator + imageRelativePath;

        //保存图片
        FileUtils.saveFile(imageRealPath, imageName, imageFile);

        //生成二维码
        String qrName = UUID.randomUUID().toString() + ".jpg";
        BufferedImage qrImage = QRCodeHelper.createImage(imageShowPath, qrCodeheight, qrCodewidth);
        FileUtils.createJPEGFile4zip(imageRealPath, qrName, qrImage);
        String qrRelativePath = webappPath + File.separator + qrName;
        String qrImageShowPath = imageHostPath + File.separator + qrRelativePath;

        //保存记录
        ImageInfo imageInfo = new ImageInfo();
        imageInfo.setDimensionCodePath(qrRelativePath);
        imageInfo.setImageName(imageName);
        imageInfo.setQrName(qrName);
        imageInfo.setImagePath(imageRelativePath);
        imageInfo.setOrignalImageName(originalName);
        imageInfoService.insertSelective(imageInfo);
        InputStream is = null;
        try {
            is = new FileInputStream(imageRealPath + File.separator + imageName);
            byte[] buffer = new byte[1024];
            int r;
            while ((r = is.read(buffer)) != -1) {
                out.write(buffer, 0, r);
            }
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } finally {
            out.close();
            is.close();
        }
    }

    /**
     * 上传图片返回输出流数据
     *
     * @param imageFile
     * @param request
     * @return
     */
    @RequestMapping("uploadImageRetJsonStream")
    @ResponseBody
    public Map uploadImageRetJsonStream(MultipartFile imageFile, HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, String> resultMap = Maps.newHashMap();
        if (imageFile.getSize() == 0) {
            logger.error("上传图片不能为空");
            return ImmutableMap.<String, String>of("result", "false", "msg", "上传图片不能为空");
        }
        if (imageFile.getSize() > IMAGE_SIZE) {
            logger.error("图片太大");
            return ImmutableMap.<String, String>of("result", "false", "msg", "图片太大");
        }
        String originalName = imageFile.getOriginalFilename();
        String imageSuffix = originalName.substring(originalName.lastIndexOf(".") + 1);

        if (!imageType.contains(imageSuffix.toLowerCase())) {
            logger.error("非法的图片");
            return ImmutableMap.<String, String>of("result", "false", "msg", "非法的图片");
        }

        String webappPath = imagePath + File.separator +
                new SimpleDateFormat("yyyyMMdd").format(new Date());
        String imageName = UUID.randomUUID().toString() + "." + imageSuffix;

        String imageRealPath = request.getSession().getServletContext().getRealPath(webappPath);
        String imageRelativePath = webappPath + File.separator + imageName;
        String imageShowPath = imageHostPath + File.separator + imageRelativePath;

        //保存图片
        FileUtils.saveFile(imageRealPath, imageName, imageFile);

        //生成二维码
        String qrName = UUID.randomUUID().toString() + ".jpg";
        BufferedImage qrImage = QRCodeHelper.createImage(imageShowPath, qrCodeheight, qrCodewidth);
        FileUtils.createJPEGFile4zip(imageRealPath, qrName, qrImage);
        String qrRelativePath = webappPath + File.separator + qrName;
        String qrImageShowPath = imageHostPath + File.separator + qrRelativePath;

        //保存记录
        ImageInfo imageInfo = new ImageInfo();
        imageInfo.setDimensionCodePath(qrRelativePath);
        imageInfo.setImageName(imageName);
        imageInfo.setQrName(qrName);
        imageInfo.setImagePath(imageRelativePath);
        imageInfo.setOrignalImageName(originalName);
        imageInfoService.insertSelective(imageInfo);
        ByteArrayOutputStream out = null;
        InputStream is = null;
        try {
            out = new ByteArrayOutputStream();
            is = new FileInputStream(imageRealPath + File.separator + imageName);
            byte[] buffer = new byte[1024];
            int r;
            while ((r = is.read(buffer)) != -1) {
                out.write(buffer, 0, r);
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } finally {
            out.close();
            is.close();
        }
        System.out.println(out.toByteArray());
        return ImmutableMap.<String, Object>of("result", true, "stream", out.toByteArray());
    }
}
