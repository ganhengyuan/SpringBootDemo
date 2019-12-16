package com.ganhy.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileSystemView;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Gan
 * @ClassName: QRCodeUtil
 * @Date: 2019-12-16 15:12
 * @Description: QR码生成工具 (二维码申城)
 **/
@Slf4j
public class QRCodeUtil {

    /**
     * CODE_WIDTH：二维码宽度，单位像素
     * CODE_HEIGHT：二维码高度，单位像素
     * FRONT_COLOR：二维码前景色，0x000000 表示黑色
     * BACKGROUND_COLOR：二维码背景色，0xFFFFFF 表示白色
     * 演示用 16 进制表示，和前端页面 CSS 的取色是一样的，注意前后景颜色应该对比明显，如常见的黑白
     */
    private static final int CODE_WIDTH = 300;
    private static final int CODE_HEIGHT = 300;
    private static final int FRONT_COLOR = 0x000000;
    private static final int BACKGROUND_COLOR = 0xFFFFFF;
    private static final String FORMAT_NAME = "png";

    /**
     * @param codeContent           二维码参数内容，如果是一个网页地址，如 https://www.baidu.com/ 则 微信扫一扫会直接进入此地址
     *                              如果是一些参数，如 1541656080837，则微信扫一扫会直接回显这些参数值
     * @param codeImgFileSaveDir    二维码图片保存的目录,如 D:/codes
     * @param fileName              二维码图片文件名称，带格式,如 123.png
     */
    public static void createCodeToFile(String codeContent, File codeImgFileSaveDir, String fileName) {
        try {
            /** 参数检验*/
            if (StringUtils.isBlank(codeContent)) {
                log.error("二维码内容为空，不进行操作...");
                return;
            }
            codeContent = codeContent.trim();
            if (codeImgFileSaveDir == null || codeImgFileSaveDir.isFile()) {
                codeImgFileSaveDir = FileSystemView.getFileSystemView().getHomeDirectory();
                log.info("二维码图片存在目录为空，默认放在桌面...");
            }
/*            if (!codeImgFileSaveDir.exists()) {
                codeImgFileSaveDir.mkdirs();
                log.info("二维码图片存在目录不存在，开始创建...");
            }*/
            if (fileName == null || "".equals(fileName)) {
                fileName = System.currentTimeMillis() + ".png";
                log.info("二维码图片文件名为空，随机生成 png 格式图片...");
            }
            File codeImgFile = new File(codeImgFileSaveDir, fileName);
            outPutData(codeContent, codeImgFile);
            log.info("二维码图片生成成功：" + codeImgFile.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param codeContent  ：二维码内容
     * @param outputStream ：输出流，比如 HttpServletResponse 的 getOutputStream
     */
    public static void createCodeToOutputStream(String codeContent, OutputStream outputStream) {
        try {
            //参数检验
            if (StringUtils.isBlank(codeContent)) {
                System.out.println("二维码内容为空，不进行操作...");
                return;
            }
            codeContent = codeContent.trim();
            outPutData(codeContent, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 共有方法
     *
     * 生成二维码 并 输出到输出流————通常用于输出到网页上进行显示
     * 输出到网页与输出到磁盘上的文件中，区别在于最后一句 ImageIO.write
     *
     * {@link javax.imageio.ImageIO}        java 扩展的图像IO
     *      {@link javax.imageio.ImageIO#write(RenderedImage, String, File)}
     *      {@link javax.imageio.ImageIO#write(RenderedImage, String, OutputStream)}
     *          RenderedImage       待写入的图像
     *          String              图像写入的格式
     *          File                输出到文件中, 会默认创建文件夹
     *          OutputStream        写到输出流中
     *
     * {@link java.awt.image.BufferedImage}                      具有图像数据的可访问缓冲图像，实现了 RenderedImage 接口
     *      {@link java.awt.image.BufferedImage#setRGB(int x, int y, int rgb)}      设置图像像素
     *      {@link com.google.zxing.common.BitMatrix#get(int x, int y)}         获取比特矩阵内容，指定位置有值，则返回true，将其设置为前景色，否则设置为背景色
     *                                                  x：像素位置的横坐标，即列
     *                                                  y：像素位置的纵坐标，即行
     *                                                  rgb：像素的值，采用 16 进制,如 0xFFFFFF 白色
     *
     * {@link com.google.zxing.MultiFormatWriter}   多格式写入，这是一个工厂类，里面重载了两个 encode 方法，用于写入条形码或二维码
     *      {@link com.google.zxing.MultiFormatWriter#encode(String contents, BarcodeFormat format, int width, int height, Map hints)}
     *          contents        条形码/二维码内容
     *          format          编码类型，如 条形码，二维码 等
     *          width           码的宽度
     *          height          码的高度
     *          hints           码内容的编码类型
     *          BarcodeFormat   枚举该程序包已知的条形码格式，即创建何种码，如 1 维的条形码，2 维的二维码 等
     *          BitMatrix       位(比特)矩阵或叫2D矩阵，也就是需要的二维码
     *
     * {@link com.google.zxing.EncodeHintType}                              编码提示类型,枚举类型
     *      {@link com.google.zxing.EncodeHintType#MARGIN}                  设置二维码边距，单位像素，值越小，二维码距离四周越近
     *      {@link com.google.zxing.EncodeHintType#CHARACTER_SET}           设置字符编码类型
     *      {@link com.google.zxing.EncodeHintType#ERROR_CORRECTION}        设置误差校正
     *      {@link com.google.zxing.qrcode.decoder.ErrorCorrectionLevel}    误差校正等级，L = ~7% correction、M = ~15% correction、Q = ~25% correction、H = ~30% correction
     *                                                                      不设置时，默认为 L 等级，等级不一样，生成的图案不同，但扫描的结果是一样的
     */
    private static void outPutData(String codeContent, Object object) throws IOException, WriterException {
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 1);
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        BitMatrix bitMatrix = multiFormatWriter.encode(codeContent, BarcodeFormat.QR_CODE, CODE_WIDTH, CODE_HEIGHT, hints);
        BufferedImage bufferedImage = new BufferedImage(CODE_WIDTH, CODE_HEIGHT, BufferedImage.TYPE_INT_BGR);
        for (int x = 0; x < CODE_WIDTH; x++) {
            for (int y = 0; y < CODE_HEIGHT; y++) {
                bufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? FRONT_COLOR : BACKGROUND_COLOR);
            }
        }
        if (object instanceof OutputStream) {
            OutputStream outputStream = (OutputStream) object;
            ImageIO.write(bufferedImage, FORMAT_NAME, outputStream);
        } else if (object instanceof File) {
            File file = (File) object;
            ImageIO.write(bufferedImage, FORMAT_NAME, file);
        }else{
            log.error("返回参数类型错误,不为 OutputStream/File 类型");
        }
    }


}