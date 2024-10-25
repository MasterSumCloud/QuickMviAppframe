package com.demo.quickmviappframe.entries

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

data class BuyResponseBackBean(
    val noncestr: String,
    val prepayid: String,
    val sign: String,
    val timestamp: Int,
    val order_no: String,
    val alipay: String
)


data class MyActFunListItemBean(
    var icon: Int, var text: String, var showArr: Boolean, var showCheckBox: Boolean
) {
    var switchChecked = false
    var myfmHeaderData: MyfmHeaderData? = null
}

data class MyfmHeaderData(
    val userName: String?,
    val userId: String?,
    val headerImageUrl: String?,
    val vipTime: String?,
    val openVip: String?
)

data class TitleBarSet(
    var title: String = "",
    var backClick: () -> Unit = {},
    var color: Color = Color.White,
    var rightButton: (@Composable () -> Unit)? = null,
    var hideTitle: Boolean = false
)