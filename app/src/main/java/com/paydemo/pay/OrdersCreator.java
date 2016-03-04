package com.paydemo.pay;

/**
 * Created by Zhouztashin on 2016/3/1.
 * 生成支付订单
 */
public abstract class OrdersCreator {
    /**
     * 支付产品名称
     */
    protected String mProductName;
    /**
     * 产品详情
     */
    protected  String mProductDetail;
    /**
     * 价格
     */
    protected  String mPrice;
    /**
     * 数量
     */
    protected int mQuantity;

    public OrdersCreator(String productName, String productDetail, String price, int quantity){
        this.mProductName= productName;
        this.mProductDetail = productDetail;
        this.mPrice = price;
        this.mQuantity = quantity;
    }
    /**
     * 创建订单
     */
    public  abstract  Object createOrder();

    /**
     *商户ID
     * @return
     */
    protected abstract String getPartnerId();

    /**
     * 订单ID
     * @return
     */
    protected abstract String getOrdersId();
}

