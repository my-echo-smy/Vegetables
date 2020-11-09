package com.example.vegetables.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.example.vegetables.common.oss.OSSPath;
import com.example.vegetables.common.oss.OSSPathBuilder;
import com.example.vegetables.common.oss.SimpleOSSClient;
import com.example.vegetables.mapper.ProductMapper;
import com.example.vegetables.model.Product;
import com.example.vegetables.model.dto.ProductDto;
import com.example.vegetables.service.ProductService;

import com.example.vegetables.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.vegetables.common.oss.OSSClientFactory;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
@Slf4j
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    @Autowired
    OSSClientFactory ossClientFactory;

    @Override
    public Product selectById(String id) {
        return baseMapper.selectById(id);
    }

    @Override
    public List<ProductDto> selectByPickUp(String id) {

        return null;
    }

    @Override
    public String uploadFile(MultipartFile file) {
        File toFile = null;
        try {
            long start = System.currentTimeMillis();
            String filename = file.getOriginalFilename();
            InputStream ins = file.getInputStream();
            toFile = new File(filename);
            inputStreamToFile(ins, toFile);
            ins.close();
            Date date = new Date();
            String dateTime = DateUtil.format(date,DateUtil.YYYY_MM_DD) ;

            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(toFile));
            OSSPath formalOssPath = OSSPathBuilder.create().withDirectory(OSSClientFactory.Directory.FORMAL).
                    withDataType(OSSClientFactory.DataType.CPS, dateTime).withFilename(filename).build();
            SimpleOSSClient ossClient = ossClientFactory.createOSSClient();
            String key = ossClient.save(formalOssPath, bis);
            String url = ossClient.getUrl(key);
            long end = System.currentTimeMillis();
            log.debug("consume 耗时-> " + (end - start));
            return url;
        }catch (Exception e){
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
