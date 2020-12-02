package com.example.vegetables.controller;


import com.example.vegetables.common.ErrorResponseData;
import com.example.vegetables.common.ResponseData;
import com.example.vegetables.common.SuccessResponseData;
import com.example.vegetables.service.OrderService;
import com.example.vegetables.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;


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
    @Autowired
    OrderService orderService;
    @ResponseBody
    @ApiOperation(value = "接口描述", notes = "接口描述")
    @GetMapping(value = "/test")
    public ResponseData demo(@RequestParam("id") String id) {
        log.info("接口描述");
        return new SuccessResponseData(productService.selectById(id));
    }

    @ResponseBody
    @ApiOperation(value = "单一文件上传测试demo", notes = "file：文件")
    @PostMapping(value = "uploadFile")
    public ResponseData uploadFile(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("file");
        if (file == null) {
            return new ErrorResponseData("file为空");
        }
        return new SuccessResponseData(productService.uploadFile(file));
    }



    @ApiOperation(value = "后端投诉信息下载", notes = "")
    @GetMapping(value = "getComplaintDownloadInfo")
    public void getComplaintDownload(  HttpServletResponse response) throws IOException, ParseException {
        orderService.getComplaintDownLoadInfo( response);
    }

}
