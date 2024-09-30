package com.demo.quickmviappframe.ext

import com.demo.quickmviappframe.util.LogU


fun String.logd(tag: String = "Peoject-Log-D") = LogU.d(tag, this)
fun String.loge(tag: String = "Peoject-Log-E") = LogU.d(tag, this)
fun String.logHttp(tag: String = "Peoject-Log-HTTP") = LogU.d(tag, this)
