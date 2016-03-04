package com.paydemo.test;

import com.paydemo.pay.WeChatPayOrderCreator;

/**
 * Created by Administrator on 2016/3/4.
 */
public class MyWeChatOrdersCreator extends WeChatPayOrderCreator {
    public MyWeChatOrdersCreator(String productName, String productDetail, String price, int qunantity) {
        super(productName, productDetail, price, qunantity);
    }

    @Override
    protected String getAppId() {
        return "注册的应用ID，参考微信文档";
    }

    @Override
    protected String getSign() {
        return "微信签名,参考微信文档";
    }

    @Override
    protected String getPrepayId() {
        return "预交易ID,参考微信文档";
    }

    @Override
    protected String getNonceString() {
        return "随机字符串,参考微信文档";
    }

    @Override
    protected String getTimestamp() {
        return "时间戳，参考微信文档";
    }

    @Override
    protected String getPartnerId() {
        return "商户ID，参考微信文档";
    }

    @Override
    protected String getOrdersId() {
        return "本地生成的订单号，参考微信文档";
    }
}
