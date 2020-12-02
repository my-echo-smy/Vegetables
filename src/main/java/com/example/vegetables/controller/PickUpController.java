package com.example.vegetables.controller;

import com.example.vegetables.common.ErrorResponseData;
import com.example.vegetables.common.ResponseData;
import com.example.vegetables.common.SuccessResponseData;
import com.example.vegetables.service.PickUpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = {"PickUpController"}, description = "自提点API")
@Slf4j
@RestController
@RequestMapping(value = "/pickup")
public class PickUpController {
    @Autowired
    PickUpService pickUpService;

    @ResponseBody
    @ApiOperation(value = "获取所有自取点信息", notes = "")
    @GetMapping(value = "getPickupList")
    public ResponseData getOrderList() {
        try {
            return new SuccessResponseData(pickUpService.getList());
        } catch (Exception e) {
            return new ErrorResponseData("获取自取店数据异常");
        }
    }
}
