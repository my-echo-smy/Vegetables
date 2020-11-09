package com.example.vegetables.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;
/**
 * 用户信息
 */
@Data
@TableName("user")
public class User {
    @TableId(type = IdType.UUID)
    private Integer id;
    /**
     * 用户名
     */
    private String name;
    /**
     * 微信openid
     */
    private String openId;
    /**
     * 创建日期
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date update_time;
}
