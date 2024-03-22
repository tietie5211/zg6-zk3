package com.wjs.controller;

import com.alipay.api.AlipayApiException;
import com.wjs.param.AliPayParam;
import com.wjs.util.R;
import com.wjs.utils.QRCodeUtil;
import com.wjs.utils.ZhifubaoUtill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * description
 *
 * @author wangjunshan
 * @date 2023/11/29 19:19
 */
@Controller
@RequestMapping("/goods/alipay")
public class AliPayController {

    @Autowired
    ZhifubaoUtill zhifubaoUtill;

    /**
     * 生成订单
     */
    @PostMapping("createOrder")
    @ResponseBody
    public String createOrder(@RequestBody AliPayParam aliPayParam) throws AlipayApiException {
        String pay = zhifubaoUtill.pay(aliPayParam);
        return pay;
    }

    @RequestMapping("returnUrl")
    public String returnUrl(HttpServletRequest request) throws AlipayApiException {
        String out_trade_no = request.getParameter("out_trade_no");
        Boolean aBoolean = zhifubaoUtill.queryResult(out_trade_no);
        if (aBoolean) {
            return "redirect:http:localhost//8888/success";
        }
        return "redirect:http://localhost:8888/error";
    }

}
