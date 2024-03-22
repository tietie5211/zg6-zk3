package com.wjs.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * description
 *
 * @author wangjunshan
 * @date 2023/11/29 10:48
 */
@Data
@TableName("brand")
public class Brand implements Serializable {


    @TableId(type = IdType.AUTO)
    private Integer id;

    private String brandName;


}
