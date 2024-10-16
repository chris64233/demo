package com.example.demo.thumbnail;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/thumbnail")
public class ThumbnailTest {

    @Resource
    FastFileStorageClient fastFileStorageClient;

    private static final long MAX_SIZE = 1_000_000; // 1MB

    public static void main(String[] args) throws IOException {
        String imagePath = "C:\\Users\\Administrator\\Desktop\\浙商中拓\\需求\\换电\\资料\\图片\\微信图片_20240731144400.jpg";
        compressImage(imagePath);
    }

    @PostMapping("/test")
    public void test(MultipartFile file) throws Exception {
        // 压缩图像
        MultipartFile compressedImage = ImageCompressionUtil.compressImage(file, 1024 * 1024); // 1MB

        StorePath storePath = fastFileStorageClient.uploadFile(compressedImage.getInputStream(),
                file.getSize(),
                StringUtils.substringAfterLast(file.getOriginalFilename(), "."),
                null);

    }

    private static void compressImage(String imagePath) throws IOException {
        File imageFile = new File(imagePath);
        System.out.println("压缩前大小： " + imageFile.length());
        while (imageFile.length() > MAX_SIZE) {
            BufferedImage image = ImageIO.read(imageFile);
            int width = image.getWidth();
            int height = image.getHeight();

            Thumbnails.of(imageFile)
                    .size(width / 2, height / 2) // 缩小图片为原始大小的一半
                    .outputQuality(0.9) // 压缩质量为90%
                    .toFile(imageFile);
        }
        System.out.println("压缩后大小： " + imageFile.length());
        System.out.println("Image compressed successfully!");
    }

}
