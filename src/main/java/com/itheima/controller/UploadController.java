package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * ClassName: UploadController
 * Package: com.itheima.controller
 * Description:
 *
 * @Author: Hjr
 * @Create 2023/11/2 18:09
 * @Version 1.0
 */
@RestController
@Slf4j
public class UploadController {


    //存储在阿里云oss
    @Autowired
    private AliOSSUtils aliOSSUtils;

    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {

        log.info("上传的文件名为:{}",image.getOriginalFilename());
        String url = aliOSSUtils.upload(image);
        log.info("上传的文件url为:{}",url);

        return Result.success(url);
    }


    //本地存储文件方式
   /* @PostMapping("upload")
    public Result upload(String name, Integer age, MultipartFile image) throws IOException {
        log.info("传入的姓名为{},年龄为{},图片为{}",name,age,image);

        //获取到原始的文件名
        String p = image.getOriginalFilename();
        // 取得.的索引然后截取后缀再将新生成的随机的uuid转成字符串拼接到后缀之前，这就是新的文件名
        int a = p.lastIndexOf(".");
        String newFileName = UUID.randomUUID().toString()+p.substring(a);

        log.info("新的文件名为{}",newFileName);
        //将文件存入本地磁盘中
        image.transferTo(new File("D:\\uploadImage\\"+newFileName));

        return Result.success();
    }*/
}
