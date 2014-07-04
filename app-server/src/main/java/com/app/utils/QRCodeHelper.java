package com.app.utils;

/**
 * Created by wangxiang2 on 14-6-25.
 */

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCodeHelper {
    private static final Log logger = LogFactory.getLog(QRCodeHelper.class);

    private static final String charset = "UTF-8"; // or "ISO-8859-1"

    /**
     * 生成二维码图片
     *
     * @param qrCodeData 原文字
     * @param qrCodeheight 高度
     * @param qrCodewidth 宽度
     * @return
     */
    public static BufferedImage createImage(String qrCodeData, int qrCodeheight, int qrCodewidth) {
        Map<EncodeHintType, Object> hintMap = new HashMap<EncodeHintType, Object>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);//容错率
        hintMap.put(EncodeHintType.MARGIN, 0);//外边距
        hintMap.put(EncodeHintType.CHARACTER_SET, "UTF8");
        BitMatrix matrix = null;
        try {
            matrix = new MultiFormatWriter().encode(
                    new String(qrCodeData.getBytes(charset), charset),
                    BarcodeFormat.QR_CODE, qrCodewidth, qrCodeheight, hintMap);
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
        } catch (WriterException e) {
            logger.error(e.getMessage());
        }
        return MatrixToImageWriter.toBufferedImage(matrix);
    }

    /**
     * 读取二维码
     *
     * @param filePath 文件路径
     * @param charset 文件编码
     * @param hintMap 二维码配置
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @throws NotFoundException
     */
    public static String readQRCode(String filePath, String charset, Map hintMap)
            throws FileNotFoundException, IOException, NotFoundException {
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
                new BufferedImageLuminanceSource(
                        ImageIO.read(new FileInputStream(filePath)))));
        Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap,
                hintMap);
        return qrCodeResult.getText();
    }

}