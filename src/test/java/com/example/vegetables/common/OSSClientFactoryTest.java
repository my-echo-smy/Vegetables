package com.example.vegetables.common;

import com.aliyun.oss.OSSClient;

import com.example.vegetables.common.oss.OSSClientFactory;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * @author 洪星, xing.hong@htouhui.com
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class OSSClientFactoryTest {

    @Value("${aliyun.oss.bucket-name}")
    private String bucketName;
    @Autowired
    private OSSClientFactory ossClientFactory;

    @Test
    public void testOSSClientFactoryInit() {
        OSSClient realOssClient = ossClientFactory.getRealOssClient();
        System.out.println(realOssClient.doesBucketExist(bucketName));
    }

//    @After
//    public void clear() {
//        ossClientFactory.getRealOssClient().deleteBucket(bucketName);
//    }

}