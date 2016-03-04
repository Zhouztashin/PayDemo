package com.paydemo.test;

import com.paydemo.pay.AlipayOrderCreator;

/**
 * Created by Administrator on 2016/3/4.
 */
public class MyAlipayOrdersCretor extends AlipayOrderCreator {
    //这里可以根据我们的业务进行一些数据的拟定，例如添加
    public MyAlipayOrdersCretor(String productName, String productDetail, String price, int quantity) {
        super(productName, productDetail, price, quantity);
    }

    @Override
    protected String getNotifyUrl() {
        return "支付宝通知回调接口，参照支付宝文档";
    }

    @Override
    protected String getSellerAccount() {
        return "商户账号，参照支付宝文档";
    }
    @Override
    protected String getPartnerId() {
        return "商户号，参照支付宝文档";
    }

    @Override
    protected String getOrdersId() {
        return "本地生成的订单号,参照支付宝文档";
    }
}
