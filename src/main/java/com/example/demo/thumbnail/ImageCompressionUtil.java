package com.example.demo.thumbnail;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author yangchi
 * @date 2024/8/1
 * <p>
 * Description:
 */
public class ImageCompressionUtil {

    public static MultipartFile compressImage(MultipartFile multipartFile, int maxSize) throws IOException, IOException {
        // 获取MultipartFile的InputStream
        InputStream inputStream = multipartFile.getInputStream();

        // 使用Thumbnails进行压缩
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        double quality = 1.0;

        Thumbnails.of(inputStream)
                .outputQuality(quality)
                .toOutputStream(outputStream);

        // 检查文件大小是否超过最大限制
        while (outputStream.size() >= maxSize && quality > 0.05) {
            quality -= 0.05; // 减少质量
            outputStream.reset();
            Thumbnails.of(inputStream)
                    .outputQuality(quality)
                    .toOutputStream(outputStream);
        }

        return convertByteArrayToMultipartFile(outputStream.toByteArray(),
                multipartFile.getOriginalFilename(), multipartFile.getContentType());
    }

    private static MultipartFile convertByteArrayToMultipartFile(byte[] bytes, String fileName, String contentType) throws IOException {
        FileItem fileItem = new DiskFileItem("file", contentType, true, fileName, bytes.length, null);
        fileItem.getOutputStream().write(bytes);
        fileItem.getOutputStream().close();

        return new CommonsMultipartFile(fileItem);
    }
}
