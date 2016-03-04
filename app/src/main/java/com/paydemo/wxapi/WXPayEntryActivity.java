package com.paydemo.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.paydemo.pay.Pay;
import com.paydemo.test.MyWeChatPay;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
	

    private IWXAPI api;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
		api = WXAPIFactory.createWXAPI(this, "跟WeChatOrder.getAppId()的值一致");
        api.handleIntent(getIntent(), this);
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
        api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
	}

	@Override
	public void onResp(BaseResp resp) {
		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
			if(resp.errCode ==0) {
				MyWeChatPay.WeChatResult(Pay.PAY_SUCCESS);
				finish();
			}else{
				MyWeChatPay.WeChatResult(Pay.PAY_FAIL);
				finish();
			}
		}
	}
}