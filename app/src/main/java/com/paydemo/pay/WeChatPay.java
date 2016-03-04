package com.paydemo.pay;

import android.app.Activity;

import com.tencent.mm.sdk.constants.Build;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * Created by Zhozutashin on 2016/3/1.
 * 微信支付
 */
public abstract class WeChatPay implements Pay {
    private IWXAPI mApi;
    protected Activity mActivity;
    public WeChatPay(Activity activity){
        mActivity =activity;
    }

    @Override
    public void pay(final OrdersCreator ordersCreator) {
        if(!(ordersCreator instanceof WeChatPayOrderCreator)){
            result(PAY_FAIL);
            return;
        }
          mApi = WXAPIFactory.createWXAPI(mActivity, null);
        //版本不支持
         if(mApi.getWXAppSupportAPI() <Build.PAY_SUPPORTED_SDK_INT){
             result(PAY_FAIL);
             return;
         }
        mApi.registerApp(((WeChatPayOrderCreator) ordersCreator).getAppId());
        mApi.sendReq((BaseReq) ordersCreator.createOrder());
    }
}
