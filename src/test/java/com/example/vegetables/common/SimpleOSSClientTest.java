package com.example.vegetables.common;

import com.aliyun.oss.OSSClient;

import com.example.vegetables.common.oss.OSSClientFactory;
import com.example.vegetables.common.oss.OSSPath;
import com.example.vegetables.common.oss.OSSPathBuilder;
import com.example.vegetables.common.oss.SimpleOSSClient;


import com.example.vegetables.util.DateUtil;
import com.example.vegetables.util.OSSUtil;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import static com.example.vegetables.TestUtils.loadImage;
import static com.example.vegetables.common.oss.OSSClientFactory.DataType.picture;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * smy
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class SimpleOSSClientTest {

    @Value("${aliyun.oss.bucket-name}")
    private String bucketName;
    @Autowired
    private OSSClientFactory ossClientFactory;

    private static byte[] idCardImage;

    private OSSPath formalOssPath = OSSPathBuilder.create().withDirectory(OSSClientFactory.Directory.FORMAL).withDataType(OSSClientFactory.DataType.picture, "100").withFilename("id_card.jpg").build();
    private OSSPath renamedFormalOssPath = OSSPathBuilder.create().withDirectory(OSSClientFactory.Directory.FORMAL)
                                                         .withDataType(OSSClientFactory.DataType.picture, "100")
                                                         .withFilename("id_card_new.jpg").build();
    private OSSPath temporaryOssPath = OSSPathBuilder.create().withDirectory(OSSClientFactory.Directory.TEMPORARY).withDataType(OSSClientFactory.DataType.picture, "100").withFilename("id_card.jpg").build();


    @BeforeClass
    public static void init() throws IOException {
        idCardImage = loadImage("/images/image_id_card_front.jpg");
    }

    @Test
    public void testSave() throws Exception {
        SimpleOSSClient ossClient = ossClientFactory.createOSSClient();

     OSSUtil.saveOssImage(idCardImage, picture, "100", "image_id_card_front.jpg");
        String url = OSSUtil.getOssUrl("100",picture,"image_id_card_front.jpg");
        System.out.println("ssssssssssssssssssssssss"+url);
        assertFalse(ossClient.isFileExist(temporaryOssPath));

    }

//    @Test
//    public void testMove() {
//        SimpleOSSClient ossClient = ossClientFactory.createOSSClient();
//        ossClient.save(temporaryOssPath, idCardImage);
//
//        ossClient.move(OSSClientFactory.Directory.TEMPORARY, OSSClientFactory.Directory.FORMAL, OSSClientFactory.DataType.USER, "100", "id_card.jpg");
//        assertTrue(ossClient.isFileExist(formalOssPath));
//        assertFalse(ossClient.isFileExist(temporaryOssPath));
//    }
//
//    @Test
//    public void testRename() {
//        SimpleOSSClient ossClient = ossClientFactory.createOSSClient();
//        ossClient.save(temporaryOssPath, idCardImage);
//
//        ossClient.rename(OSSClientFactory.Directory.TEMPORARY, OSSClientFactory.Directory.FORMAL,
//                         OSSClientFactory.DataType.USER, "100", "id_card.jpg", "id_card_new.jpg");
//        assertTrue(ossClient.isFileExist(renamedFormalOssPath));
//        assertTrue(ossClient.isFileExist(temporaryOssPath));
//    }
//
//
//    @Test
//    public void testDownload() throws Exception {
//        SimpleOSSClient ossClient = ossClientFactory.createOSSClient();
//        ossClient.save(temporaryOssPath, idCardImage);
//
//        OutputStream outputStream = ossClient.download(temporaryOssPath);
//        assertNotNull(outputStream);
//    }

    @After
    public void clear() {
        OSSClient realOssClient = ossClientFactory.getRealOssClient();
        realOssClient.deleteObject(bucketName, temporaryOssPath.toString());
        realOssClient.deleteObject(bucketName, formalOssPath.toString());
        realOssClient.deleteObject(bucketName, renamedFormalOssPath.toString());
    }


}