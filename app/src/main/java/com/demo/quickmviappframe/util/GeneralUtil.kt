package com.demo.quickmviappframe.util

import android.app.Activity
import android.content.Context
import com.umeng.analytics.MobclickAgent
import java.math.RoundingMode
import java.text.DecimalFormat

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

    fun getFileSize(filsSize: Long): String {
        //最低1M
        val standSize = 1024 * 1024
        val format = DecimalFormat("0.##")
        //未保留小数的舍弃规则，RoundingMode.FLOOR表示直接舍弃。
        format.roundingMode = RoundingMode.FLOOR

        if (filsSize > standSize) {//1M
            val mbs = filsSize / standSize
            return format.format(mbs) + "M"
        } else if (filsSize > standSize * 1024) {
            val gbs = filsSize / (standSize * 1024)
            return format.format(gbs) + "GB"
        } else {
            val kbs = filsSize / 1024
            return format.format(kbs) + "K"
        }
    }
}