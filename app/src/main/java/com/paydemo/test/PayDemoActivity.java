package com.paydemo.test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.paydemo.pay.Pay;

/**
 * Created by Administrator on 2016/3/4.
 */
public class PayDemoActivity extends Activity implements PayCallBack {
    private static String TAG =PayDemoActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyAlipayPay.setCallBack(this);
        alipay();
        weChatPay();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyAlipayPay.removeCallBack();
    }

    private void alipay(){
        Pay pay = new MyAlipayPay(this);
        pay.pay(new MyAlipayOrdersCretor("产品名称", "产品详情", "价格", 1));
    }
    private void weChatPay(){
        Pay pay = new MyWeChatPay(this);
        pay.pay(new MyWeChatOrdersCreator("产品名称","产品详情","价格",1));
    }

    @Override
    public void result(int i) {
        //输出结果
        if(i == Pay.PAY_SUCCESS){
            //支付成功
            Log.i(TAG, "支付成功");
        }else{
            //支付失败
            Log.i(TAG, "支付失败");
        }
    }
}
