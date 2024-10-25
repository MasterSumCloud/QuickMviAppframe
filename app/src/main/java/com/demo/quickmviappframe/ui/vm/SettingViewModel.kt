package com.demo.quickmviappframe.ui.vm

import androidx.compose.runtime.mutableStateListOf
import com.blankj.utilcode.util.CacheDiskStaticUtils
import com.demo.quickmviappframe.base.BaseViewModel
import com.demo.quickmviappframe.util.GeneralUtil

class SettingViewModel : BaseViewModel() {
    val settingList = mutableStateListOf<SettingItem>().apply {
        add(SettingItem("清理缓存", GeneralUtil.getFileSize(CacheDiskStaticUtils.getCacheSize()), true, false))
        add(SettingItem("检查更新", "", true, false))
        add(SettingItem("关于我们", "", true, false))
        add(SettingItem("个性化推荐", "", false, true))
    }
}

data class SettingItem(
    val leftTx: String,
    var rightTx: String,
    val showArr: Boolean,
    val showCheckBox: Boolean
)