package com.paydemo.test;

import android.app.Activity;

import com.paydemo.pay.AlipayPay;

/**
 * Created by Administrator on 2016/3/4.
 */
public class MyAlipayPay extends AlipayPay {
    /**
     * 这里通过静态回调结果,可以自行考虑结果通知方案
     */
    private static PayCallBack sPayCallBack;
    public static void setCallBack(PayCallBack payCallBack){
        sPayCallBack = payCallBack;
    }
    public static void removeCallBack(){
        sPayCallBack = null;
    }
    public MyAlipayPay(Activity c) {
        super(c);
    }

    @Override
    public void result(int payResult) {
        if(sPayCallBack!=null){
            sPayCallBack.result(payResult);
        }
    }
}
