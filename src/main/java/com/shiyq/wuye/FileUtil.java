package com.shiyq.wuye.utils;

import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

/**
 * 文件读取工具类
 */
public class FileUtil {

    /**
     * 读取文件内容，作为字符串返回
     */
    public static String readFileAsString(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException(filePath);
        }

        if (file.length() > 1024 * 1024 * 1024) {
            throw new IOException("File is too large");
        }

        StringBuilder sb = new StringBuilder((int) (file.length()));
        // 创建字节输入流
        FileInputStream fis = new FileInputStream(filePath);
        // 创建一个长度为10240的Buffer
        byte[] bbuf = new byte[10240];
        // 用于保存实际读取的字节数
        int hasRead = 0;
        while ( (hasRead = fis.read(bbuf)) > 0 ) {
            sb.append(new String(bbuf, 0, hasRead));
        }
        fis.close();
        return sb.toString();
    }

    /**
     * 根据文件路径读取byte[] 数组
     */
    public static byte[] readFileByBytes(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException(filePath);
        } else {
            ByteArrayOutputStream bos = new ByteArrayOutputStream((int) file.length());
            BufferedInputStream in = null;

            try {
                in = new BufferedInputStream(new FileInputStream(file));
                short bufSize = 1024;
                byte[] buffer = new byte[bufSize];
                int len1;
                while (-1 != (len1 = in.read(buffer, 0, bufSize))) {
                    bos.write(buffer, 0, len1);
                }

                byte[] var7 = bos.toByteArray();
                return var7;
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException var14) {
                    var14.printStackTrace();
                }

                bos.close();
            }
        }
    }

    /**
     * 文件上传，返回本地地址
     * @param file
     * @return 本地路径
     * @throws IOException
     */
    public static String uploadFile(MultipartFile file) throws IOException {
        // 获取根路径
        String basePath = null;
        try {
            basePath = ResourceUtils.getURL("classpath:").getPath();
        } catch (FileNotFoundException e) {
            throw  new IOException("无法获取根路径");
        }
        // 创建路径
        File uploadPath = new File(basePath);
        if (!uploadPath.exists()){
            uploadPath.mkdirs();
        }
        // 判断文件是否为空
        if (file.isEmpty() || file.getSize() <= 0) {
            throw  new IOException("上传文件为空！");
        }
        // 文件名处理
        // 获取文件后缀名，如：“.jpg”
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        // 更改文件名
        String uploadFileName =  UUID.randomUUID().toString() + suffix;
        //通过CommonsMultipartFile的方法直接写文件
        try {
            file.transferTo(new File(basePath + "/" + uploadFileName));
        } catch (IOException e){
            throw  new IOException("文件上传出现异常");
        }
        return uploadFileName;
    }
}
