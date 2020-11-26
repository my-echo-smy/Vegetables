package com.example.vegetables.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.vegetables.mapper.PickUpMapper;
import com.example.vegetables.mapper.ProductMapper;
import com.example.vegetables.model.Product;
import com.example.vegetables.service.ProductService;
import com.example.vegetables.util.OSSUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.vegetables.common.oss.OSSClientFactory;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import static com.example.vegetables.common.oss.OSSClientFactory.DataType.picture;

@Slf4j
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    @Autowired
    OSSClientFactory ossClientFactory;
    @Autowired
    PickUpMapper pickUpMapper;

    @Override
    public Product selectById(String id) {
        Product product = baseMapper.selectById(id);
        if(product.getPicture()!=null){
            String url = OSSUtil.getOssUrl(product.getId(), picture, product.getPicture());
            product.setPicture(url);
        }

        return product;
    }

    @Override
    public List<Product> selectByPickUp(String id,String name) {
        List<Product> products = baseMapper.selectByPickUp(id,name);
        for (Product product : products) {
            if(product.getPicture()!=null) {
                String url = OSSUtil.getOssUrl(product.getId(), picture, product.getPicture());
                product.setPicture(url);
            }
        }
        return products;
    }

    @Override
    public void save(Product product, MultipartFile file) {
        String filename = null;
        if (file != null) {
            filename = file.getOriginalFilename();
            product.setPicture(filename);
        }
        product.setStatus(2);
        String id = baseMapper.insertProduct(product);
        if (file != null) {
            File toFile = null;
            try {
                InputStream ins = file.getInputStream();
                toFile = new File(filename);
                inputStreamToFile(ins, toFile);
                ins.close();
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(toFile));
                ByteArrayOutputStream imageByteArrayOS = new ByteArrayOutputStream();
                IOUtils.copy(bis, imageByteArrayOS);
                byte[] imageBytes = imageByteArrayOS.toByteArray();
                OSSUtil.saveOssImage(imageBytes, picture, id, filename);
            } catch (Exception e) {
                log.error(e.getMessage());
                toFile.delete();
            }
        }
    }

    @Override
    public void update(Product product, MultipartFile file) {
        String filename = null;
        if (file != null) {
            filename = file.getOriginalFilename();
            product.setPicture(filename);
        }
        baseMapper.updateById(product);
        if (file != null) {
            File toFile = null;
            try {
                InputStream ins = file.getInputStream();
                toFile = new File(filename);
                inputStreamToFile(ins, toFile);
                ins.close();
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(toFile));
                ByteArrayOutputStream imageByteArrayOS = new ByteArrayOutputStream();
                IOUtils.copy(bis, imageByteArrayOS);
                byte[] imageBytes = imageByteArrayOS.toByteArray();
                OSSUtil.saveOssImage(imageBytes, picture, product.getId(), filename);
            } catch (Exception e) {
                log.error(e.getMessage());
                toFile.delete();
            }
        }
    }

    @Override
    public String uploadFile(MultipartFile file) {
        File toFile = null;
        try {

            String filename = file.getOriginalFilename();
            InputStream ins = file.getInputStream();
            toFile = new File(filename);
            inputStreamToFile(ins, toFile);
            ins.close();
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(toFile));
            ByteArrayOutputStream imageByteArrayOS = new ByteArrayOutputStream();
            IOUtils.copy(bis, imageByteArrayOS);
            byte[] imageBytes = imageByteArrayOS.toByteArray();
            OSSUtil.saveOssImage(imageBytes, picture, "100", filename);
            String url = OSSUtil.getOssUrl("100", picture, filename);
            return url;
        } catch (Exception e) {
            log.error(e.getMessage());
            toFile.delete();
        }
        return null;
    }

    public static void inputStreamToFile(InputStream ins, File file) {
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
            log.error(e.getMessage());
        }
    }
}
