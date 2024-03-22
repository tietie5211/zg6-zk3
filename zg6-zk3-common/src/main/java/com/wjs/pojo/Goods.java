package com.wjs.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @TableName goods
 */
@TableName(value = "goods")
@Data
public class Goods implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 订单价格
     */
    private double price;
    /**
     * 名称
     */
    private String name;

    /**
     * 品牌ID
     */
    private Integer brandId;

    /**
     * 售后服务
     */
    private String afterServer;

    /**
     * 介绍
     */
    private String introduce;

    /**
     * 评价数
     */
    private String evaluateNum;

    /**
     * 是否上架1上架0未上架
     */
    private String isPut;

    /**
     * 0：未提交审核，1审核通过，2 审核未通过
     */
    private String status;
    /**
     * 0：未提交审核，1审核通过，2 审核未通过
     */
    private String orderNum;

    private String picture;

    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;


}