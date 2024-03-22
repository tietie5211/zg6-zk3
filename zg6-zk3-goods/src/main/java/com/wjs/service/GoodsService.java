package com.wjs.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wjs.param.GoodsParam;
import com.wjs.pojo.Brand;
import com.wjs.pojo.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
* @author user
* @description 针对表【goods】的数据库操作Service
* @createDate 2023-11-29 09:31:59
*/
public interface GoodsService extends IService<Goods> {

    IPage<Goods> goodsList(GoodsParam goodsParam);

    int addGoods(Goods goods);

    String upload(MultipartFile file);

    List<Brand> brandList();

    int addBrand(Brand brand);
}
