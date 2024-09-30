package com.demo.quickmviappframe.core;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;

import com.alipay.sdk.app.PayTask;
import com.blankj.utilcode.util.GsonUtils;
import com.demo.quickmviappframe.entries.BuyResponseBackBean;
import com.demo.quickmviappframe.util.PayUtils;

import java.util.Map;

public class Alipay {

    private static Alipay instance;
    private PayUtils.IPayUtilCallback mCallback;

    public static synchronized Alipay getInstance() {
        if (instance == null) {
            instance = new Alipay();
        }
        return instance;
    }

    private Alipay() {

    }

    public void startPay(final Context context, String json, PayUtils.IPayUtilCallback callback) {
        this.mCallback = callback;
        BuyResponseBackBean data = GsonUtils.fromJson(json, BuyResponseBackBean.class);
        String str = data.getAlipay();
        final Runnable payRunnable = () -> {
            PayTask alipay = new PayTask((Activity) context);
            Map<String, String> result = alipay.payV2(str, true);
//            LogU.INSTANCE.d("支付返回==" + GsonUtils.toJson(result));
            String resultStatus = result.get("resultStatus");
            if (!TextUtils.isEmpty(resultStatus) && "9000".equals(resultStatus)) {
                mHandler.sendEmptyMessage(200);
            } else {
                mHandler.sendEmptyMessage(1);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }


    Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            if (mCallback != null) {
                if (msg.what == 200)
                    mCallback.onPaySuccess();
                else
                    mCallback.onPayFailure();
            }
            super.handleMessage(msg);
        }
    };
}
