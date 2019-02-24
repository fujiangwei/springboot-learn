package com.example.springbootqiniu.storage;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * descripiton: 七牛OSS
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/2/23
 * @time: 21:58
 * @modifier:
 * @since:
 */
@Component
public class QiNiuStorage {

    @Value("${storage.AK}")
    private String accessKey;
    @Value("${storage.SK}")
    private String secretKey;
    @Value("${storage.domain}")
    private String domain;
    @Value("${storage.bucket}")
    private String bucket;

    public void upload() {
        if (StringUtils.isAnyBlank(accessKey, secretKey, domain, bucket)) {
            throw new IllegalArgumentException("请先设置配置信息");
        }

        //文件所在的目录名
        String fileDir = "image/jpg/";
        //文件名
        String key = "user.png";
        String path = fileDir + key;
        File file = null;
        try {
            //此处的static前不能加/！！！
            file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "static/images/user.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Zone z = Zone.autoZone();
        Configuration configuration = new Configuration(z);

        Auth auth = Auth.create(accessKey, secretKey);
        StringMap putPolicy = new StringMap();
        putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
        long expireSeconds = 3600;
        String upToken = auth.uploadToken(bucket, path, expireSeconds, putPolicy);
        System.out.println("upToken=====》" + upToken);

        UploadManager uploadManager = new UploadManager(configuration);
        try {
            Response response = uploadManager.put(file, path, upToken);
            System.out.println("statusCode: " + response.statusCode);
        } catch (QiniuException e) {
            e.printStackTrace();
        }

        System.out.println("访问路径=====》" + domain + "/" + path);

    }

    public void deleteFile() {
        if (StringUtils.isAnyBlank(accessKey, secretKey, domain, bucket)) {
            throw new IllegalArgumentException("请先设置配置信息");
        }

        //文件所在的目录名
        String fileDir = "image/jpg/";
        //文件名
        String key = "user.png";

        String path = StringUtils.remove(fileDir + key, domain.trim());

        Zone z = Zone.autoZone();
        Configuration configuration = new Configuration(z);
        Auth auth = Auth.create(accessKey, secretKey);

        BucketManager bucketManager = new BucketManager(auth, configuration);
        try {
            bucketManager.delete(bucket, path);
        } catch (QiniuException e) {
            Response r = e.response;
            System.out.println(e.response.getInfo());
        }
    }

}
