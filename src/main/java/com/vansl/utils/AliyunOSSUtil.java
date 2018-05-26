package com.vansl.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.Random;

/**
 * @author: vansl
 * @create: 18-5-10 下午4:09
 */
public class AliyunOSSUtil {

    static Logger logger = LogManager.getLogger(AliyunOSSUtil.class);

    private static String END_POINT;            //访问域名
    private static String ACCESS_KEY_ID;        //秘钥id
    private static String ACCESS_KEY_SECRET;    //秘钥key

    static{
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties properties = new Properties();
        try(InputStream in =loader.getResourceAsStream("config/aliyunOSS.properties");){
            properties.load(in);
            END_POINT= properties.getProperty("END_POINT");                //访问域名
            ACCESS_KEY_ID=properties.getProperty("ACCESS_KEY_ID");         //秘钥id
            ACCESS_KEY_SECRET=properties.getProperty("ACCESS_KEY_SECRET"); //秘钥key
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获得oss连接对象
     * @return oss连接对象
     */
    private static OSSClient initOssClient(){
        return new OSSClient(END_POINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
    }

    /**
     * 创建bucket并赋予权限
     *
     * @param bucketName buket名
     * @param authc      访问权限（1,2,3）（私有，公共读，公共读写）
     */
    public static void createBucket(String bucketName, int authc) {
        //创建OSS客户端
        OSSClient ossClient = initOssClient();

        CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
        switch (authc) {
            case 1:
                createBucketRequest.setCannedACL(CannedAccessControlList.Private);
                break;
            case 2:
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                break;
            case 3:
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicReadWrite);
                break;
            default:
                createBucketRequest.setCannedACL(CannedAccessControlList.Private);
                break;
        }
        ossClient.createBucket(createBucketRequest);
        // 关闭OSS连接
        ossClient.shutdown();
    }

    /**
     * 删除bucket
     *
     * @param bucketName bucket名
     */
    public void deleteBucket( String bucketName) {
        //创建OSS客户端
        OSSClient ossClient = initOssClient();

        ossClient.deleteBucket(bucketName);
        // 关闭OSS连接
        ossClient.shutdown();
    }

    /**
     * 创建文件夹
     *
     * @param bucketName bucket名
     * @param folder     模拟文件夹名
     */
    public static void createFolder(String bucketName, String folder) {
        //创建OSS客户端
        OSSClient ossClient = initOssClient();
        //上传空文件以创建目录
        ossClient.putObject(bucketName, folder+"/", new ByteArrayInputStream(new byte[0]));
        OSSObject object = ossClient.getObject(bucketName, folder);
        String fileDir = object.getKey();
        logger.info("文件夹创建成功："+fileDir);
        // 关闭OSS连接
        ossClient.shutdown();
    }

    /**
     * 删除文件夹
     *
     * @param bucketName bucket名
     * @param folder     模拟文件夹名
     */
    public static void deleteFolder(String bucketName, String folder) {
        //创建OSS客户端
        OSSClient ossClient = initOssClient();
        //需要递归删除子文件夹
        ObjectListing objectListing = ossClient.listObjects(bucketName, folder);
        List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
        for (OSSObjectSummary s : sums) {
            if(!s.getKey().equals(folder)){
                deleteFolder(bucketName,s.getKey());
            }
        }
        ossClient.deleteObject(bucketName, folder);
        logger.info("删除文件夹成功："+ folder);
        // 关闭OSS连接
        ossClient.shutdown();
    }

    /**
     * 上传文件
     *
     * @param inputStream 文件流
     * @param fileName    文件名
     * @param bucketName bucket名
     * @param folder  模拟文件夹名
     * @return 文件访问url
     */
    public static String upload(InputStream inputStream, String fileName,String bucketName, String folder){
        if (inputStream == null) {
            return null;
        }
        //创建OSS客户端
        OSSClient ossClient = initOssClient();

        try{
            //创建上传文件的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            //上传文件的长度
            metadata.setContentLength(inputStream.available());
            //指定该object被下载时的网页的缓存行为
            metadata.setCacheControl("no-cache");
            //指定该object下设置Header
            metadata.setHeader("Pragma","no-cache");
            //指定该object被下载时的内容编码方式
            metadata.setContentEncoding("utf-8");
            //文件MIME
            String contentype=Files.probeContentType(Paths.get(fileName));
            metadata.setContentType(contentype);
            // 生成新的文件名
            String suffix=fileName.substring(fileName.lastIndexOf("."));
            String newFilename=generateFilename(suffix);
            //上传文件
            ossClient.putObject(bucketName, folder+"/"+newFilename, inputStream, metadata);

            String fileUrl = "https://"+bucketName+"."+END_POINT+"/"+folder+"/"+newFilename;
            return fileUrl;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 关闭OSS连接
            ossClient.shutdown();
        }
        return null;
    }

    /**
     * 生成随机文件名
     *
     * @param suffix 文件后缀
     * @return 生成的文件名
     */
    public static String generateFilename(String suffix){
        String randomFilename = "";

        //生成五位随机数
        Random random = new Random();
        int randomNum = (int)(random.nextDouble()*90000)+10000;

        //返回时间戳+随机数作为文件名
        randomFilename = String.valueOf(System.currentTimeMillis())+randomNum+suffix;

        return randomFilename;
    }

    /**
     * 删除OSS文件
     *
     * @param bucketName bucket名
     * @param folder    模拟文件夹名
     * @param fileName  文件名(可以包含路径)
     */
    public static void deleteFile(String bucketName,String folder,String fileName){
        //创建OSS客户端
        OSSClient ossClient = initOssClient();

        ossClient.deleteObject(bucketName,folder+"/"+fileName);
        logger.debug("删除"+bucketName+"下的文件:"+folder+"/"+fileName+"成功!");
        // 关闭OSS连接
        ossClient.shutdown();
    }

}