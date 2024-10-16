package com.demo.quickmviappframe.base

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blankj.utilcode.util.DeviceUtils
import com.blankj.utilcode.util.GsonUtils
import com.demo.quickmviappframe.core.Constent
import com.demo.quickmviappframe.ext.isNotEmptyOrNull
import com.demo.quickmviappframe.ext.logd
import com.demo.quickmviappframe.ext.request
import com.demo.quickmviappframe.net.apiService
import com.demo.quickmviappframe.util.MMKVUtil
import com.demo.quickmviappframe.util.Tos

open class BaseViewModel : ViewModel() {
    val loadingChange: UiLoadingChange by lazy { UiLoadingChange() }
    /**
     * 内置封装好的可通知Activity/fragment 显示隐藏加载框 因为需要跟网络请求显示隐藏loading配套
     */
    inner class UiLoadingChange {
        //显示加载框
        val showDialog by lazy { MutableLiveData<String>() }

        //隐藏
        val dismissDialog by lazy { MutableLiveData<Boolean>() }
    }

}