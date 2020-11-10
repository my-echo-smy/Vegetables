package com.example.vegetables.util;

import com.example.vegetables.common.oss.OSSClientFactory;
import com.example.vegetables.common.oss.OSSPath;
import com.example.vegetables.common.oss.OSSPathBuilder;
import com.example.vegetables.common.oss.SimpleOSSClient;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static com.example.vegetables.common.oss.OSSClientFactory.Directory.PICTURE;


/**
 * smy
 */
public class OSSUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(OSSUtil.class);

    /**
     * oss 图片 URL 头
     */
    private static final String PICTURE_URL_HEAD = "https://static.m/bonus/";

    /**
     * 获取 oss 图片地址
     */
    public static String getOssUrl(String id, OSSClientFactory.DataType dataType, String fileName) {
        LOGGER.info("get oss url , record id  is :{}, data type is :{}, file name:{}", id, dataType, fileName);
        SimpleOSSClient ossClient = SpringUtils.getBean(OSSClientFactory.class).createOSSClient();
        OSSPath ossPath = OSSPathBuilder.create()
                                        .withDirectory(PICTURE)
                                        .withDataType(dataType, id)
                                        .withFilename(fileName)
                                        .build();
        if (!ossClient.isFileExist(ossPath)) {
            LOGGER.error("get oss image error, image is not exist with id:{}", id);
            return null;
        }
        return ossClient.getUrl(ossPath);
    }


    /**
     * 保存商品图片到 OSS
     */
    public static void saveOssImage(byte[] image, OSSClientFactory.DataType dataType, String id, String fileName) {
        SimpleOSSClient ossClient = SpringUtils.getBean(OSSClientFactory.class).createOSSClient();
        OSSPath ossPath = OSSPathBuilder.create()
                                        .withDirectory(PICTURE)
                                        .withDataType(dataType, id)
                                        .withFilename(fileName)
                                        .build();
        ossClient.save(ossPath, image);
    }


    /**
     * OSS 图片删除
     *
     * @param dataType
     * @param id
     * @param fileName
     */
    public static void deleteFromOssImage(OSSClientFactory.DataType dataType, Long id, String fileName) {
        SimpleOSSClient ossClient = SpringUtils.getBean(OSSClientFactory.class).createOSSClient();
        OSSPath ossPath = OSSPathBuilder.create()
                                        .withDirectory(PICTURE)
                                        .withDataType(dataType, Long.toString(id))
                                        .withFilename(fileName)
                                        .build();
        ossClient.delete(ossPath);
    }


    /**
     * 根据环境不同采用不同方式获取 oss 文件路径
     *
     * @param recordId
     * @param dataType
     * @param fileName
     *
     * @return
     */
    public static String getOssUrlByEnvironment(String recordId, OSSClientFactory.DataType dataType, String fileName) {
//        //预发或者生产环境采用 CDN 加速
//        if (Settings.ENV == Env.pre || Settings.ENV == Env.production) {
//            return StringUtils.join(PICTURE_URL_HEAD, dataType, "/", recordId, "/", fileName).toLowerCase();
//        }
        return getOssUrl(recordId, dataType, fileName);
    }

    /**
     * 生成图片名
     *
     * @param fileNamePre 图片名前缀
     *
     * @return
     */
    public static String makePictureName(String fileNamePre) {
        String fileNameSuf = StringUtils.join(UUID.randomUUID().toString(), System.currentTimeMillis());
        return StringUtils.join(fileNamePre, "_", DigestUtils.md5Hex(fileNameSuf), ".jpg").toLowerCase();
    }
}
