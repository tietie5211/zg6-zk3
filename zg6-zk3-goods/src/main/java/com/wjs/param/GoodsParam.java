package com.wjs.param;

import lombok.Data;

import java.io.Serializable;

/**
 * description
 *
 * @author wangjunshan
 * @date 2023/11/29 9:37
 */
@Data
public class GoodsParam implements Serializable {

    private Integer pageCurrent = 1;

    private Integer pageSize = 10;

    private String name;


}
