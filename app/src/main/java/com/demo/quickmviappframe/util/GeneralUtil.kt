package com.demo.quickmviappframe.util

import android.app.Activity
import android.content.Context
import com.umeng.analytics.MobclickAgent

object GeneralUtil {

    /**
     * 友盟点击统计
     */
    fun onUMengClickEvent(context: Context, event: String) {
        MobclickAgent.onEvent(context.applicationContext, event)
    }

    /**
     * 友盟用户统计登入
     */
    fun onUMengAccountSignIn(provider: String, accountId: String?) {
        MobclickAgent.onProfileSignIn(provider, accountId)
    }

    fun isActExist(act: Activity?): Boolean {
        if (act == null) {
            return false
        } else {
            return !(act.isFinishing || act.isDestroyed)
        }
    }
}