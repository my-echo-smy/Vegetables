package com.example.vegetables.controller;


import com.example.vegetables.common.ErrorResponseData;
import com.example.vegetables.common.ResponseData;
import com.example.vegetables.common.SuccessResponseData;
import com.example.vegetables.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @author SMy
 */
@Api(tags = {"DemoController"}, description = "相关API")
@Slf4j
@RestController
@RequestMapping(value = "/demo")
public class DemoController {
    @Autowired
    ProductService productService;

    @ResponseBody
    @ApiOperation(value = "接口描述", notes = "接口描述")
    @GetMapping(value = "/test")
    public ResponseData demo() {
        log.info("接口描述");
        return new SuccessResponseData(productService.selectById("1"));
    }

    @ResponseBody
    @ApiOperation(value = "单一文件上传", notes = "")
    @PostMapping(value = "uploadFile")
    public ResponseData uploadFile(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("file");
        if (file == null) {
            return new ErrorResponseData("file为空");
        }
        return new SuccessResponseData(productService.uploadFile(file));
    }
}
