package com.paydemo.pay;

import com.tencent.mm.sdk.modelpay.PayReq;

/**
 * Created by Zhouztashin on 2016/3/1.
 * 创建微信订单
 */
public abstract  class WeChatPayOrderCreator extends OrdersCreator {

    public WeChatPayOrderCreator(String productName, String productDetail, String price,int qunantity) {
        super(productName, productDetail, price,qunantity);
    }

    @Override
    public Object createOrder() {
        PayReq req = new PayReq();
        req.appId			=getAppId();
        req.partnerId		=getPartnerId();
        req.prepayId		= getPrepayId();
        req.nonceStr		= getNonceString();
        req.timeStamp		= getTimestamp();
        req.packageValue	= getPackageValue();
        req.sign			= getSign();
        return req;
    }

    /**
     *公众账号Id
     * @return
     */
    protected abstract  String getAppId();

    /**
     *签名,具体请看微信文档解释
     * @return
     */
    protected  abstract String getSign();


    /**
     * 预交易会话Id,由服务端向微信请求而来
     * @return
     */
    protected abstract  String getPrepayId();

    /**
     * 随机字符串，具体请看微信文档解释
     * @return
     */
    protected abstract String getNonceString();

    /**
     * 时间戳，具体请看微信文档解释
     * @return
     */
    protected abstract String getTimestamp() ;

    /**
     * 扩展字段，目前固定值
     * @return
     */
    private String getPackageValue(){
        return "Sign=WXPay";
    }

}
