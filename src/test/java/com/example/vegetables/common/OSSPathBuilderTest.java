package com.example.vegetables.common;

import com.example.vegetables.common.oss.OSSClientFactory;
import com.example.vegetables.common.oss.OSSPath;
import com.example.vegetables.common.oss.OSSPathBuilder;
import org.junit.jupiter.api.Test;

import static net.sf.ezmorph.test.ArrayAssertions.assertEquals;


/**
 * @author 洪星, xing.hong@htouhui.com
 * @version 1.0
 */
public class OSSPathBuilderTest {

    @Test
    public void testBuildOssPath() {
        OSSPath ossPath = OSSPathBuilder.create()
                .withDirectory(OSSClientFactory.Directory.FORMAL)
                .withDataType(OSSClientFactory.DataType.USER, "1234567890")
                .withFilename("abc.txt").build();

        assertEquals("formal/user/1234567890/abc.txt", ossPath.toString());
    }

}