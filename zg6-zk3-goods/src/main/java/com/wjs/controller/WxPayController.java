package com.wjs.controller;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.lang.generator.SnowflakeGenerator;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wjs.pojo.Goods;
import com.wjs.service.GoodsService;
import com.wjs.util.R;
import com.wjs.utils.QRCodeUtil;
import com.wjs.utils.WxPayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * description
 *
 * @author wangjunshan
 * @date 2023/11/29 19:11
 */
@RestController
@RequestMapping("/goods/wxpay")
public class WxPayController {


    @Autowired
    WxPayUtils wxPayUtils;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    GoodsService goodsService;

    @GetMapping("searchWxPay/{orderId}")
    public void searchWxPay(@PathVariable String orderId, HttpServletResponse response){

        LambdaQueryWrapper<Goods> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Goods::getOrderNum,orderId);

        Goods one = goodsService.getOne(wrapper);
        if(one == null){
            System.out.println("订单不存在,无法支付");
            return;
        }
        SnowflakeGenerator snowflakeGenerator = new SnowflakeGenerator();
        Long next = snowflakeGenerator.next();
        String s = next.toString();
        String s1 = wxPayUtils.wxPay(s);

        QRCodeUtil.createQRCode(response,s1);

        stringRedisTemplate.opsForValue().set("orderNum_"+orderId,s,12, TimeUnit.HOURS);
    }

    /**
     *
     * @param orderId
     * @return
     * @throws Exception
     */
    @GetMapping("returnUrl/{orderId}")
    public Boolean returnUrl(@PathVariable String orderId) throws Exception {

        String orderNum = stringRedisTemplate.opsForValue().get("orderNum_" + orderId);
        Boolean aBoolean = wxPayUtils.refreshWxPay(orderNum);

        return aBoolean;
    }



}
