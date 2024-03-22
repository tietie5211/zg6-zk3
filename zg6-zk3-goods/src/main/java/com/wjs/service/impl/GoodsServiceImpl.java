package com.wjs.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.wjs.param.GoodsParam;
import com.wjs.pojo.Brand;
import com.wjs.pojo.Goods;
import com.wjs.service.GoodsService;
import com.wjs.mapper.GoodsMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
* @author user
* @description 针对表【goods】的数据库操作Service实现
* @createDate 2023-11-29 09:31:59
*/
@Service
@Transactional
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods>
    implements GoodsService{

    private static final String URL = "http://192.168.171.129/";
    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    FastFileStorageClient fastFileStorageClient;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public IPage<Goods> goodsList(GoodsParam goodsParam) {

        // IPage<Goods> page = new Page<>(goodsParam.getPageCurrent(),goodsParam.getPageSize());

        Page<Goods> goodsPage = new Page<>(goodsParam.getPageCurrent(),goodsParam.getPageSize());

        // LambdaQueryWrapper<Goods> wrapper = new LambdaQueryWrapper<>();
        // wrapper.eq(StringUtils.isNotBlank(goodsParam.getName()),Goods::getName,goodsParam.getName());

        Page<Goods> goodsPage1 = goodsMapper.selectAll(goodsPage, goodsParam);

        return goodsPage1;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ,rollbackFor = Exception.class)
    public int addGoods(Goods goods) {
        int insert = goodsMapper.insert(goods);
        /**
         * 延迟队列
         */
        rabbitTemplate.convertAndSend("2104query", "", insert, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                // message.getMessageProperties().setDelay(30*1000);
                message.getMessageProperties().setDelay(30*1000);
                return message;
            }
        });

        return insert;
    }

    @Override
    public String upload(MultipartFile file) {
        String fullPath = "";
        try {
        String s = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
            fullPath  =URL + fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), s, null).getFullPath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fullPath;
    }

    @Override
    public List<Brand> brandList() {

       List<Brand> list = goodsMapper.brandList();

        return list;
    }

    @Override
    public int addBrand(Brand brand) {

        int i = goodsMapper.addBrand(brand);

        return i;
    }
}




