package com.paydemo.pay;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.alipay.sdk.app.PayTask;
import com.paydemo.pay.util.PayResult;

/**
 * Created by Zhouztashin on 2016/3/1.
 * 支付宝支付类
 */
public abstract class AlipayPay implements Pay {
    protected final Activity mActivity;
    public AlipayPay (Activity c){
        mActivity = c;
    }
    @Override
    public void pay(OrdersCreator ordersCreator) {
        if(!(ordersCreator instanceof AlipayOrderCreator)){
            result(PAY_FAIL);
            return;
        }
        String orderInfo = String.valueOf(ordersCreator.createOrder());
        String sign = ((AlipayOrderCreator) ordersCreator).signOrder(orderInfo);// 对订单做RSA 签名
        // 完整的支付宝订单信息
        final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" 	+ ((AlipayOrderCreator) ordersCreator).getSignType();
        //这里可改成自己的线程池
        new Thread(new Runnable() {
            @Override
            public void run() {
                payInternal(payInfo);
            }
        }).start();

    }

    private void payInternal(String payInfo){
        PayTask alipay = new PayTask(mActivity);
        // 调用支付接口，获取支付结果
        String result = alipay.pay(payInfo,true);
        PayResult payResult = new PayResult(result);
        // 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
        String resultStatus = payResult.getResultStatus();
        // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
        int resultInt = PAY_EXCEPTION;
        if (TextUtils.equals(resultStatus, "9000")) {
            resultInt = PAY_SUCCESS;
        } else {
            // 判断resultStatus 为非“9000”则代表可能支付失败
            // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
            if (TextUtils.equals(resultStatus, "8000")) {
                resultInt = PAY_FAIL;
            }
        }
        final int finalResult = resultInt;
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                result(finalResult);
            }
        });
    }


}
