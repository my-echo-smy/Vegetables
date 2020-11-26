package com.example.vegetables.common;

import com.aliyun.oss.OSSClient;

import com.example.vegetables.common.oss.OSSClientFactory;
import com.example.vegetables.common.oss.OSSPath;
import com.example.vegetables.common.oss.OSSPathBuilder;
import com.example.vegetables.common.oss.SimpleOSSClient;


import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;


import static com.example.vegetables.TestUtils.loadImage;
import static com.example.vegetables.common.oss.OSSClientFactory.DataType.picture;
import static com.example.vegetables.common.oss.OSSClientFactory.Directory.PICTURE;
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


    @BeforeClass
    public static void init() throws IOException {
        idCardImage = loadImage("/images/测试.jpg");
    }

    @Test
    public void testSave() throws Exception {
        SimpleOSSClient ossClient = ossClientFactory.createOSSClient();
        OSSPath ossPath = OSSPathBuilder.create()
                .withDirectory(PICTURE)
                .withDataType(picture, "5")
                .withFilename("测试.jpg")
                .build();
        ossClient.save(ossPath, idCardImage);
        String url = ossClient.getUrl(ossPath);

        System.out.println("ssssssssssssssssssssssss"+url);
        System.out.println("rreeee"+ossPath.toString());
        assertTrue(ossClient.isFileExist(ossPath));

    }

}