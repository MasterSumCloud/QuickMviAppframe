package com.demo.quickmviappframe.core

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.text.TextUtils
import com.blankj.utilcode.util.ObjectUtils
import java.util.*


/**
 * Activity管理类
 */
class AppManager private constructor() {
    /**
     * 添加Activity到堆栈
     */
    fun addActivity(activity: Activity?) {
        if (activityStack == null) {
            activityStack = Stack<Activity>()
        }
        activityStack?.add(activity)
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    fun currentActivity(): Activity? {
        return activityStack?.lastElement()
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    fun finishActivity() {
        var activity: Activity? = activityStack?.lastElement()
        if (activity != null) {
            activity.finish()
            activity = null
        }
    }

    /**
     * 结束指定的Activity
     */
    fun finishActivity(activity: Activity?) {
        var activity = activity
        if (activity != null) {
            activityStack?.remove(activity)
            activity.finish()
            activity = null
        }
    }

    /**
     * 结束指定类名的Activity
     */
    fun finishActivity(cls: Class<*>) {
        for (activity in activityStack!!) {
            if (activity.javaClass == cls) {
                finishActivity(activity)
            }
        }
    }

    /**
     * 结束所有Activity
     */
    fun finishAllActivity() {
        var i = 0
        val size: Int = activityStack!!.size
        while (i < size) {
            if (null != activityStack!!.get(i)) {
                activityStack?.get(i)?.finish()
            }
            i++
        }
        activityStack?.clear()
    }

    /**
     * 退出应用程序
     */
    fun AppExit(context: Context) {
        try {
            finishAllActivity()
            val activityMgr = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            activityMgr.killBackgroundProcesses(context.packageName)
            System.exit(0)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getMainAct(): Activity? {
        if (ObjectUtils.isNotEmpty(activityStack)) {
            activityStack?.forEach {
                if (TextUtils.equals(it.localClassName, "MainActivity")) {
                    return it
                }
            }
        }
        return null
    }

    companion object {
        private var activityStack: Stack<Activity>? = null
        private var instance: AppManager? = null

        /**
         * 单一实例
         */
        val appManager: AppManager?
            get() {
                if (instance == null) {
                    instance = AppManager()
                }
                return instance
            }
    }
}