package com.demo.quickmviappframe.entries

data class BuyResponseBackBean(
    val noncestr: String,
    val prepayid: String,
    val sign: String,
    val timestamp: Int,
    val order_no: String,
    val alipay: String
)

data class MediaFile(
    val fileName: String,
    val path: String,
    val fileSize: Long,
    val addTime: Long,
    val duration: Long,
    val firstImagePath: String,
    val mediaType: Int,//0图 1视频
    var select: Boolean
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