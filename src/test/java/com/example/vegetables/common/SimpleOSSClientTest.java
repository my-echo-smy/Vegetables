package com.example.vegetables.common;

import com.aliyun.oss.OSSClient;

import com.example.vegetables.common.oss.OSSClientFactory;
import com.example.vegetables.common.oss.OSSPath;
import com.example.vegetables.common.oss.OSSPathBuilder;
import com.example.vegetables.common.oss.SimpleOSSClient;


import org.junit.After;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.OutputStream;


import static com.example.vegetables.TestUtils.loadImage;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * @author 洪星, xing.hong@htouhui.com
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class SimpleOSSClientTest {

    @Value("${aliyun.oss.bucket-name}")
    private String bucketName;
    @Autowired
    private OSSClientFactory ossClientFactory;

    private static byte[] idCardImage;

    private OSSPath formalOssPath = OSSPathBuilder.create().withDirectory(OSSClientFactory.Directory.FORMAL).withDataType(OSSClientFactory.DataType.USER, "100").withFilename("id_card.jpg").build();
    private OSSPath renamedFormalOssPath = OSSPathBuilder.create().withDirectory(OSSClientFactory.Directory.FORMAL)
                                                         .withDataType(OSSClientFactory.DataType.USER, "100")
                                                         .withFilename("id_card_new.jpg").build();
    private OSSPath temporaryOssPath = OSSPathBuilder.create().withDirectory(OSSClientFactory.Directory.TEMPORARY).withDataType(OSSClientFactory.DataType.USER, "100").withFilename("id_card.jpg").build();


    @BeforeClass
    public static void init() throws IOException {
        idCardImage = loadImage("/images/image_id_card_front.jpg");
    }

    @Test
    public void testSave() throws Exception {
        SimpleOSSClient ossClient = ossClientFactory.createOSSClient();
        String key = ossClient.save(formalOssPath, idCardImage);

        assertFalse(ossClient.isFileExist(temporaryOssPath));
        assertTrue(ossClient.isFileExist(formalOssPath));
    }

    @Test
    public void testMove() {
        SimpleOSSClient ossClient = ossClientFactory.createOSSClient();
        ossClient.save(temporaryOssPath, idCardImage);

        ossClient.move(OSSClientFactory.Directory.TEMPORARY, OSSClientFactory.Directory.FORMAL, OSSClientFactory.DataType.USER, "100", "id_card.jpg");
        assertTrue(ossClient.isFileExist(formalOssPath));
        assertFalse(ossClient.isFileExist(temporaryOssPath));
    }

    @Test
    public void testRename() {
        SimpleOSSClient ossClient = ossClientFactory.createOSSClient();
        ossClient.save(temporaryOssPath, idCardImage);

        ossClient.rename(OSSClientFactory.Directory.TEMPORARY, OSSClientFactory.Directory.FORMAL,
                         OSSClientFactory.DataType.USER, "100", "id_card.jpg", "id_card_new.jpg");
        assertTrue(ossClient.isFileExist(renamedFormalOssPath));
        assertTrue(ossClient.isFileExist(temporaryOssPath));
    }


    @Test
    public void testDownload() throws Exception {
        SimpleOSSClient ossClient = ossClientFactory.createOSSClient();
        ossClient.save(temporaryOssPath, idCardImage);

        OutputStream outputStream = ossClient.download(temporaryOssPath);
        assertNotNull(outputStream);
    }

    @After
    public void clear() {
        OSSClient realOssClient = ossClientFactory.getRealOssClient();
        realOssClient.deleteObject(bucketName, temporaryOssPath.toString());
        realOssClient.deleteObject(bucketName, formalOssPath.toString());
        realOssClient.deleteObject(bucketName, renamedFormalOssPath.toString());
    }


}