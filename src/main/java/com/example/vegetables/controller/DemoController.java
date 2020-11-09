package com.example.vegetables.controller;


import com.example.vegetables.common.ResponseData;
import com.example.vegetables.common.SuccessResponseData;
import com.example.vegetables.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


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

}
