package com.paydemo.pay;

/**
 * Created by Zhuztashin on 2016/3/1.
 * 支付基类
 */
public interface Pay {
    public final static int PAY_SUCCESS = 1;
    public final static int PAY_FAIL = 2;
    public final static int PAY_EXCEPTION = 3;
    /**
     * 支付
     * @param ordersCreator
     */
    public void pay(OrdersCreator ordersCreator);

    /**
     * 结果处理
     */
    public  void result(int payResult);
}
