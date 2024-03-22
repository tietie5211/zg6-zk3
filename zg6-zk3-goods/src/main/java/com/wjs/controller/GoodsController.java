package com.wjs.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wjs.mapper.GoodsMapper;
import com.wjs.param.GoodsParam;
import com.wjs.pojo.Brand;
import com.wjs.pojo.Goods;
import com.wjs.service.GoodsService;
import com.wjs.util.R;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * description
 *
 * @author wangjunshan
 * @date 2023/11/29 9:32
 */
@RestController
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    /**
     * 列表
     * @param goodsParam
     * @return
     */
    @PostMapping("goodsList")
    public R goodsList(@RequestBody GoodsParam goodsParam){

       IPage<Goods> page = goodsService.goodsList(goodsParam);

        return R.ok().message("查询成功").data("page",page);
    }

    /**
     * 品牌
     */
    @GetMapping("brand")
    public R brand(){

       List<Brand> list = goodsService.brandList();

        return R.ok().message("查询成功").data("list",list);
    }

    /**
     * 添加
     * @param goods
     * @return
     */
    @PostMapping("addGoods")
    public R addGoods(@RequestBody Goods goods){

       // int i = goodsService.addGoods(goods);
        boolean save = goodsService.save(goods);

        return save ? R.ok().message("添加成功") : R.error().message("添加失败");
    }


    /**
     * 图片上传
     */
    @PostMapping("upload")
    public R upload(MultipartFile file){

      String fullPath = goodsService.upload(file);
      if(StringUtils.isNotBlank(fullPath)){
          return R.ok().message("上传成功").data("fullPath",fullPath);
      }
        return R.error().message("上传失败");
    }


    @PostMapping("addBrand")
    public R addBrand(@RequestBody Brand brand){

       int i = goodsService.addBrand(brand);

        return R.ok().message("添加成功");
    }


}
