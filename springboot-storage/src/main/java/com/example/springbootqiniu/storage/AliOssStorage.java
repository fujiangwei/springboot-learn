package com.example.springbootqiniu.storage;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * descripiton: 阿里云OSS
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/2/23
 * @time: 21:58
 * @modifier:
 * @since:
 */
@Component
public class AliOssStorage {

    // 访问域名。
    private String endpoint = "";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
    private String accessKeyId = "";
    private String accessKeySecret = "";
    //存储空间名
    private String bucketName = "";

    public void upload() {

        if (StringUtils.isAnyBlank(accessKeyId, accessKeySecret, endpoint, bucketName)) {
            throw new IllegalArgumentException("请先设置配置信息");
        }

        //文件所在的目录名
        String fileDir = "link/";
        //文件名
        String key = "aliU.png";
        String path = fileDir + key;
        File file = null;
        try {
            //此处的static前不能加/！！！
            file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "static/images/user.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        // 上传内容到指定的存储空间（bucketName）并保存为指定的文件名称（objectName）。
        PutObjectResult putObjectResult = ossClient.putObject(bucketName, path, file);

        // 关闭OSSClient。
        ossClient.shutdown();

        System.out.println("访问路径=====》" + endpoint + "/" + path);

    }

    public void delete() {
        if (StringUtils.isAnyBlank(accessKeyId, accessKeySecret, endpoint, bucketName)) {
            throw new IllegalArgumentException("请先设置配置信息");
        }

        //文件所在的目录名
        String fileDir = "image/jpg/";
        //文件名
        String key = "aliU.png";
        String path = fileDir + key;

        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        // 删除文件。
        ossClient.deleteObject(bucketName, path);

        // 关闭OSSClient。
        ossClient.shutdown();
    }


}
