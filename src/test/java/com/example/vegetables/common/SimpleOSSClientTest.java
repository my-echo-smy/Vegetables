package com.example.vegetables.common;


import com.example.vegetables.TestUtils;
import com.example.vegetables.common.oss.OSSClientFactory;
import com.example.vegetables.common.oss.OSSPath;
import com.example.vegetables.common.oss.OSSPathBuilder;
import com.example.vegetables.common.oss.SimpleOSSClient;


import org.apache.tomcat.util.http.fileupload.IOUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


import static com.example.vegetables.common.oss.OSSClientFactory.DataType.picture;
import static com.example.vegetables.common.oss.OSSClientFactory.Directory.PICTURE;

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


    @Test
    public void testSave() throws Exception {
        String filepath = "D:/蔬菜/苹果.png";
        File toFile = new File(filepath);
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(toFile));
        ByteArrayOutputStream imageByteArrayOS = new ByteArrayOutputStream();
        IOUtils.copy(bis, imageByteArrayOS);
        byte[] idCardImage = imageByteArrayOS.toByteArray();

        SimpleOSSClient ossClient = ossClientFactory.createOSSClient();
        OSSPath ossPath = OSSPathBuilder.create()
                .withDirectory(PICTURE)
                .withDataType(picture, "2")
                .withFilename("苹果.png")
                .build();
        ossClient.save(ossPath, idCardImage);
        String url = ossClient.getUrl(ossPath);

        System.out.println("ssssssssssssssssssssssss"+url);
        System.out.println("rreeee"+ossPath.toString());
        assertTrue(ossClient.isFileExist(ossPath));

    }
    public   void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {

        }
    }
}