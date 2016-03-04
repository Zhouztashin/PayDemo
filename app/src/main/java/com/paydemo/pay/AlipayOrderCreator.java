package com.paydemo.pay;


import com.paydemo.pay.util.SignUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Zhouztashin on 2016/3/1.
 * 创建支付宝订单
 */
public abstract class AlipayOrderCreator extends OrdersCreator {

    public AlipayOrderCreator(String productName, String productDetail, String price,int quantity) {
        super(productName, productDetail, price,quantity);
    }

    @Override
    public String createOrder() {
        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + getPartnerId() + "\"";
        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + getSellerAccount() + "\"";
        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + getOrdersId() + "\"";
        // 商品名称
        orderInfo += "&subject=" + "\"" + mProductName + "\"";
        // 商品详情
        orderInfo += "&body=" + "\"" + mProductDetail+ "\"";
        // 商品金额
        orderInfo += "&total_fee=" + "\"" + mPrice + "\"";
        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + getNotifyUrl() + "\"";
        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";
        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";
        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";
        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";
        orderInfo += "&quantity=" + "\"" + mQuantity+ "\"";
        return orderInfo;
    }

    public String signOrder(Object order) {
        //这里参考支付宝的订单签名,建议放在服务器去做
        String sign = SignUtils.sign((String) order, "私钥，参照支付宝文档");
        try {
            // 仅需对sign 做URL编码
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sign;
    }

    /**
     * 签名类型跟signOrder()方法对应
     * @return
     */
    public String getSignType() {
        return "sign_type=\"RSA\"";
    }

    /**
     * 服务器回调地址，服务器根据这个回调地址获取支付订单号
     * @return
     */
    protected abstract String getNotifyUrl();

    /**
     * 商家支付账号
     * @return
     */
    protected abstract String getSellerAccount();



}
