package com.shiyq.wuye.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import javax.imageio.ImageIO;

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
import sun.misc.BASE64Encoder;

/**
 * @author shiyq
 * @create 2021-05-25 10:46
 */
public class QRCodeUtil {

    /**
     * 定义日期格式
     */
    public static DateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 生成二维码 直接将二维码图片写到指定文件目录
     *
     * @param content 二维码内容
     * @param width 二维码宽度
     * @param height 二维码高度，通常建议二维码宽度和高度相同
     * @param picFormat 二维码图片格式，jpg/png
     */
    public static void generateQRCodePic(String content, int width, int height, String picFormat) {

        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.MARGIN, 1);

        try {
            // 构造二维字节矩阵
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);

            // 构造文件目录，若目录不存在，则创建目录
            String fileDir = "E:\\Test" + File.separator + "image" + File.separator + sf.format(new Date());
            if (!new File(fileDir).exists()) {
                new File(fileDir).mkdirs();
            }
            Path file = new File(fileDir + File.separator + "qrcode." + picFormat).toPath();

            // 将二位字节矩阵按照指定图片格式，写入指定文件目录，生成二维码图片
            MatrixToImageWriter.writeToPath(bitMatrix, picFormat, file);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成二维码 生成二维码图片字节流
     *
     * @param content 二维码内容
     * @param width 二维码宽度和高度
     * @param picFormat 二维码图片格式
     */
    public static byte[] generateQRCodeByte(String content, int width, String picFormat) {
        byte[] codeBytes = null;
        try {
            // 构造二维字节矩阵，将二位字节矩阵渲染为二维缓存图片
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, width);
            BufferedImage image = toBufferedImage(bitMatrix);

            // 定义输出流，将二维缓存图片写到指定输出流
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(image, picFormat, out);

            // 将输出流转换为字节数组
            codeBytes = out.toByteArray();

        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
        return codeBytes;
    }

    /**
     * 生成二维码 生成二维码图片Base64字符串
     *
     * @param content 二维码内容
     * @param width 二维码宽度和高度
     * @param picFormat 二维码图片格式
     */
    public static String generateQRCodeBase64(String content, int width, String picFormat) {
        return new BASE64Encoder().encode(generateQRCodeByte(content, width, picFormat));
    }

    /**
     * 将二维字节矩阵渲染为二维缓存图片
     *
     * @param matrix 二维字节矩阵
     * @return 二维缓存图片
     */
    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int onColor = 0xFF000000;
        int offColor = 0xFFFFFFFF;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? onColor : offColor);
            }
        }
        return image;
    }

    /**
     * 解析二维码内容
     *
     * @param filepath 二维码路径
     */
    public static void readQRCode(String filepath) {

        MultiFormatReader multiFormatReader = new MultiFormatReader();
        File file = new File(filepath);

        // 图片缓冲
        BufferedImage image = null;

        // 二进制比特图
        BinaryBitmap binaryBitmap = null;

        // 二维码结果
        Result result = null;

        try {
            image = ImageIO.read(file);
            binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
            result = multiFormatReader.decode(binaryBitmap);
        } catch (IOException | NotFoundException e1) {
            e1.printStackTrace();
        }

        System.out.println("读取二维码： " + result.toString());
        System.out.println("二维码格式： " + result.getBarcodeFormat());
        System.out.println("二维码内容： " + result.getText());
    }

}
