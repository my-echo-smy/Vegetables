package com.example.vegetables.controller;

import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSON;
import com.example.vegetables.common.ErrorResponseData;
import com.example.vegetables.common.ResponseData;
import com.example.vegetables.common.SuccessResponseData;
import com.example.vegetables.model.Product;
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

/**
 * @author SMy
 */
@Api(tags = {"ProductController"}, description = "商品相关API")
@Slf4j
@RestController
@RequestMapping(value = "/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @ResponseBody
    @ApiOperation(value = "获取商品详情", notes = "id:商品id ")
    @GetMapping(value = "getProduct")
    public ResponseData getProduct(@RequestParam("id") String productId) {
        try {
            return new SuccessResponseData(productService.selectById(productId));
        } catch (Exception e) {
            return new ErrorResponseData("获取商品详情数据异常");
        }
    }

    @ResponseBody
    @ApiOperation(value = "获取自取店下所有商品", notes = "id:自取点id name：模糊搜索商品名称 可为空")
    @GetMapping(value = "getProductList")
    public ResponseData getProductList(@RequestParam("id") String pickUpId,@RequestParam("name") String name) {
        try {
            return new SuccessResponseData(productService.selectByPickUp(pickUpId,name));
        } catch (Exception e) {
            return new ErrorResponseData("获取自取店下所有商品数据异常");
        }
    }

    @ResponseBody
    @ApiOperation(value = "添加商品", notes = " file：商品图片 ，body ：商品详情")
    @PostMapping(value = "saveProduct")
    public ResponseData saveProduct(HttpServletRequest request,  @RequestParam("body") String json) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("file");
        if (file == null) {
            return new ErrorResponseData("商品图片为空");
        }
        Product product = JSON.parseObject(json, Product.class);
        productService.save(product,file);
        return new SuccessResponseData();
    }

    @ResponseBody
    @ApiOperation(value = "修改商品", notes = " file：商品图片 ，body ：商品详情")
    @PostMapping(value = "updateProduct")
    public ResponseData updateProduct(HttpServletRequest request,  @RequestParam("body") String json) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("file");
        if (file == null) {
            return new ErrorResponseData("商品图片为空");
        }
        Product product = JSON.parseObject(json, Product.class);
        productService.update(product,file);
        return new SuccessResponseData();
    }


}