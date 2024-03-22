package com.wjs.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wjs.param.GoodsParam;
import com.wjs.pojo.Brand;
import com.wjs.pojo.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author user
* @description 针对表【goods】的数据库操作Mapper
* @createDate 2023-11-29 09:31:59
* @Entity com.wjs.pojo.Goods
*/
public interface GoodsMapper extends BaseMapper<Goods> {

    List<Brand> brandList();

    Page<Goods> selectAll(Page<Goods> goodsPage, @Param(value = "goodsParam") GoodsParam goodsParam);

    int addBrand(Brand brand);
}




