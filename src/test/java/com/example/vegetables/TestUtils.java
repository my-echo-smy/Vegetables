
package com.example.vegetables;



import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author redstarstar, star.hong@gmail.com
 * @version 1.0
 */
public class TestUtils {

    public static byte[] loadImage(String imagePath) {
        byte[] imageBytes;
        try (InputStream imageInputStream = TestUtils.class.getResourceAsStream(imagePath)) {
            ByteArrayOutputStream imageByteArrayOS = new ByteArrayOutputStream();
            IOUtils.copy(imageInputStream, imageByteArrayOS);
            imageBytes = imageByteArrayOS.toByteArray();
        } catch (IOException e) {
            imageBytes = new byte[0];
        }
        return imageBytes;
    }
}
