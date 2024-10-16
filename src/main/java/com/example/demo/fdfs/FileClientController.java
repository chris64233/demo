package com.example.demo.fdfs;

import com.example.demo.fdfs.config.FileConfig;
import com.example.demo.fdfs.entity.IdCardOcrForm;
import com.example.demo.fdfs.entity.MultipartFiles;
import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/fastdfs")
public class FileClientController {

    @Resource
    FastFileStorageClient fastFileStorageClient;

    @Autowired
    FileConfig config;

    private String baseUrl = "http://10.0.9.146:80/";

    @GetMapping("/config")
    public String config() {
        System.out.println(config.getBaseUrl());
        return config.getBaseUrl();
    }

    /**
     * 文件上传
     *
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/upload")
    public String upload(MultipartFile file) throws Exception {
        StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(),
                file.getSize(),
                StringUtils.substringAfterLast(file.getOriginalFilename(), "."),
                null);
        return baseUrl + storePath.getFullPath();
    }

    /**
     * 文件上传
     *
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/uploadTest")
    public String uploadTest(@RequestParam("file") MultipartFile file, @RequestParam("test") String test) throws Exception {
        Set<MetaData> metaDataSet = new HashSet<>();
        MetaData metaData = new MetaData("userId", "CCC123456");
        metaDataSet.add(metaData);


        StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(),
                file.getSize(),
                StringUtils.substringAfterLast(file.getOriginalFilename(), "."),
                metaDataSet);
        System.out.println(baseUrl + storePath.getFullPath());
        return baseUrl + storePath.getFullPath();
    }

    @PostMapping("/uploadFiles1")
    public String uploadFiles1(@Valid IdCardOcrForm form) throws Exception {
        System.out.println("test uploadFiles");
        return "";
    }

    @PostMapping("/uploadFiles2")
    public String uploadFiles2(MultipartFile[] files) throws Exception {
        return "";
    }

    @PostMapping("/uploadFiles3")
    public String uploadFiles3(MultipartFiles fileList) throws Exception {
        return "";
    }

    // https://www.jianshu.com/p/487a32405974
    @PostMapping("/uploadFiles4")
    public String uploadFiles4(List<MultipartFile> fileList) throws Exception {
        return "";
    }

    /**
     * @param groupName
     * @param fileKey
     * @param response
     * @throws Exception
     */
    @PostMapping("/download")
    public void download(String groupName, String fileKey, HttpServletResponse response) throws Exception {
        byte[] download = fastFileStorageClient.downloadFile(groupName, fileKey, new DownloadByteArray());
        // 将download以流的方式返回
        response.getOutputStream().write(download);
    }

    @PostMapping("/download2")
    public void download2(String url, HttpServletResponse response) throws Exception {
        StorePath storePath = StorePath.parseFromUrl(url);
        byte[] download = fastFileStorageClient.downloadFile(storePath.getGroup(), storePath.getPath(), new DownloadByteArray());
        // 将download以流的方式返回
        response.getOutputStream().write(download);
    }


    /**
     * 删除文件
     *
     * @param filePath 文件的完整路径
     */
    @PostMapping("/delete")
    public void delFile(String filePath) {
        fastFileStorageClient.deleteFile(filePath);
    }
}
