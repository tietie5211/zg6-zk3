package com.wjs.utils;


import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConfig;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.wjs.param.AliPayParam;
import org.springframework.stereotype.Component;


@Component
public class ZhifubaoUtill {

    String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDJcLAHJajFMMJEO6zS/urRMkRpKiWW3osvem5BnNxkLGdepqaw4k8rm/W1BdWK+fFEatf/xb753oZRKa6D4DTue4WOr8yeWYRUSO+i/vUeJuc+2qmK5Tr7Y31klu28PC6L/iyCcCoT4p9HjNDDpGErsBDFq6Kuz2LmUtctdX5Xet5A8fVDbqbHCggMkrPnpt/9BLYsqH0adIiQOQcwy2ZElZNqyWemSNq0QUZS+uEZZzxrjC2D6Jlzi1Ia392QJ6tJaBw8xIYdAgXDVPygu4tW0LFbBTbxge1sMdDR0lLSMVCj2gNxjSKt7A4scyESalaPrBzH0SUdOYi2FHwTj5RnAgMBAAECggEBAKq6WGbmUyE1zyC3lyWJKom9Wd/r+cnhugO2d2YixWQdgkrlYdf/JuymFVykojVU21iKWaGM/uyV3i9B7YgV6abKiArEEt+OrSomWvxBrIwumsVZ3GI9IH1Z+Dfd94YXV7gM57pxyCfsZeTlgzadxkGZys1OR+ZzHLZrNbZwo3ENz/MxvcqyVHN9wMt0xLRP7HdqzkIF3zuc+01/sy/yLIngWwfccCYBGIeks7ODIjpG7+rd3dXJTCWoUC2uOj71mrjXaCKpGzHDe6dpIlXA0ITcdsRyIl4FNw6L8lXu8zrGpxpS4TSxIrBPTX2WYOCZN67XyiDclmQlEiGspUpM8lkCgYEA6DQA1x2ZzFAf3yZly62eQOeMGuS9tDLacT3sleVOhpZNokGZWgyg8Yxmye9RvCZil8kzHWKqLfOwBXqdRe21L/ARq4ML2SyXj2VFOBZJxFCXZ34yix1TxlkiV2nQKDcVUsJdhJobczBUgYqnmZwGCw6+QqB5iYS8opk5XRlhy0MCgYEA3hWZSCkHioY+LHOcKPjIbhXZCPMtgG64JowHhK1EnZbaxtFyRf4MxzU5BYVTIB81ECRHGCj9oO9ZC0WuFeFJCSJmNkaAxBS1KBAoo25gLfODBxNm1WZx45CS7dv68+njr4aB1t5UAwY/8wmBKts/U2qyxWf4HFiIcovcmvAVlg0CgYBCBn9yq3UO9n2JizaIys5j8DMTdpVGhus8Jfp+yNwiQtzCVVrQkyS0alLT+pvIHzYLqOdC5Mo1FMSpWiscNjI99kpf9XxJ/Xb/DDG5eATzsGLMx8KBcg5fKPst1eWdecQzaxcCXHuPrl9pEk8Dy6ygOkn5Uf4dD0RAC8eW5F+9VwKBgCSyMvK+fNWB2xIjREFs4kl2aLdW30TihqEv8CoXK0hS3fGOODCiit/7xmFIJ3UeUvlI1UqDs2z9P5xiNiK9aAEPYw5C1hIQTeLB0MU4fsH5A8SOiyZl2Bm/ENgmyi+1jP663gdwEzewt65j+qjnM26IKeP+ivu8EUfEHn6nfxmdAoGAFLixntT/xDpLXWWdhEyuCipMFz0JpXYDg9YlhqcTAM9ukDXGNQgrHzmGtUbwlv2l37QSkkhRLbX2FGXPVp8l0hDm0mr/JuGHLA2cxZbU9FXbjqyQzzLGwrvVvzf0u2UdqdaH+dyVDpL8ByYl+TTc/N8fhx/6OTa1ek5aNTmnGoE=";
    String alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvkARi4q35oznot1amOMUyQRI/Hu8+JvIw1cQgthSKtZoOmV4Dpa1DDBQrncscJTI+ZL5OiJnzl1o4y1kMycB9vC214kukxBhN6YxT7LJwbZBTp5mZQq/nZki0lsTMzPNt1oGtUBnIsLg1sxEyeTjzGlwTJBJ7uw6kJB6PW7okhr4creIulnHxkgtG509L51NqYmEnM9wUp3ntqC85Ebypci9GAEYba8S9l6XclBhr4W9NePuAFUPjhd8TjOxHghASB91egbHT/Od2Su7KizCtZBE2H8/ECLQsWI87fjjEEA75ZLxIY0p+bAGioWMRZ1gSuDiOaB4GEsoY7HswB561wIDAQAB";

    //生成支付订单接口
    public String pay(AliPayParam payData) throws AlipayApiException {
        AlipayConfig alipayConfig = new AlipayConfig();
        alipayConfig.setServerUrl("https://openapi-sandbox.dl.alipaydev.com/gateway.do");
        alipayConfig.setAppId("9021000132639023");
        alipayConfig.setPrivateKey(privateKey);
        alipayConfig.setFormat("json");
        alipayConfig.setAlipayPublicKey(alipayPublicKey);
        alipayConfig.setCharset("UTF8");
        alipayConfig.setSignType("RSA2");
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig);
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();

        request.setReturnUrl("http://localhost:10010/goods/alipay/returnUrl");

        AlipayTradePagePayModel model = new AlipayTradePagePayModel();
        model.setOutTradeNo(payData.getCode());
        model.setTotalAmount(payData.getMoney().toString());
        model.setSubject(payData.getName());
        model.setProductCode("FAST_INSTANT_TRADE_PAY");
        request.setBizModel(model);
        AlipayTradePagePayResponse response = alipayClient.pageExecute(request);
        System.out.println(response.getBody());

        return response.getBody();
    }


    //查询接口
    public Boolean queryResult(String tradeNo) throws AlipayApiException {
        AlipayConfig alipayConfig = new AlipayConfig();
        alipayConfig.setServerUrl("https://openapi-sandbox.dl.alipaydev.com/gateway.do");
        alipayConfig.setAppId("9021000132639023");
        alipayConfig.setPrivateKey(privateKey);
        alipayConfig.setFormat("json");
        alipayConfig.setAlipayPublicKey(alipayPublicKey);
        alipayConfig.setCharset("UTF8");
        alipayConfig.setSignType("RSA2");
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig);
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        AlipayTradeQueryModel model = new AlipayTradeQueryModel();
        model.setOutTradeNo(tradeNo);
        request.setBizModel(model);
        AlipayTradeQueryResponse response = alipayClient.execute(request);
        System.out.println(response.getBody());
        return response.isSuccess();
    }
}
