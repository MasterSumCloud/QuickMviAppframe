package com.demo.quickmviappframe.util

import android.app.Activity
import android.text.TextUtils
import com.blankj.utilcode.util.AppUtils
import com.demo.quickmviappframe.R
import com.demo.quickmviappframe.core.Alipay
import com.demo.quickmviappframe.wxapi.Wx

class PayUtils(private val mActivity: Activity?) {
    fun payMoney(json: String?, payTypeIn: String) {
        if (mActivity != null && !TextUtils.isEmpty(json)) {
            GeneralUtil.onUMengClickEvent(mActivity, "wait_pay_counts")
            pay(payTypeIn, json)
        }
    }

    private fun pay(payType: String, json: String?) {
        // 微信支付
        if (payType == TYPE_WX) {
            if (!isWeixinAvilible) {
                Tos.showToastShort(mActivity!!.getString(R.string.wechat_not_install))
            } else {
                Wx.getInstance().startPay(mActivity, json, mCallback)
            }
            // 支付宝支付
        } else if (payType == TYPE_ALIPAY) {
            Alipay.getInstance().startPay(mActivity, json, mCallback)
        }
    }

    private var mCallback: IPayUtilCallback? = null

    interface IPayUtilCallback {
        fun onPaySuccess()

        fun onPayFailure()
    }

    fun setIPayUtilCallback(callback: IPayUtilCallback?) {
        this.mCallback = callback
    }

    val isWeixinAvilible: Boolean
        get() = AppUtils.isAppInstalled("com.tencent.mm")

    companion object {
        const val TYPE_ALIPAY: String = "ali"
        const val TYPE_WX: String = "wx"
    }
}