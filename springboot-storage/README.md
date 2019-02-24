# 七牛

## 七牛存储整合
[Java SDK](https://developer.qiniu.com/kodo/sdk/1239/java#2)

## 域名绑定
[融合CDN](https://portal.qiniu.com/cdn/domain/create?bucket=kinsonqn&fixBucket)

## CNAME解析
[如何配置域名的 CNAME](https://developer.qiniu.com/fusion/kb/1322/how-to-configure-cname-domain-name)

## 整合
> pom文件引入

    <dependency>
        <groupId>com.qiniu</groupId>
        <artifactId>qiniu-java-sdk</artifactId>
        <!--从7.2.0, 7.2.99选取最新的版本-->
        <version>[7.2.0, 7.2.99]</version>
    </dependency>
    
> application

    storage:
        #accessKey
        AK: xxxx
        #secretKey
        SK: xxxx
        #访问域名
        domain: xxxx
        #存储名
        bucket: xxxx
        
> 主要代码

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
    
# 阿里云OSS
 
 ## SDK
 [Java SDK](https://help.aliyun.com/document_detail/32011.html?spm=a2c4g.11186623.6.658.25866328LTfqLW)
 
 ## 整合
 
 > pom 文件
 
    <!--阿里云-->
    <dependency>
        <groupId>com.aliyun.oss</groupId>
        <artifactId>aliyun-sdk-oss</artifactId>
        <version>2.8.3</version>
    </dependency>
    
 > 主要代码
 
     // 创建OSSClient实例。
    OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

    // 上传内容到指定的存储空间（bucketName）并保存为指定的文件名称（objectName）。
    PutObjectResult putObjectResult = ossClient.putObject(bucketName, path, file);

    // 关闭OSSClient。
    ossClient.shutdown();