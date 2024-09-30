package com.demo.quickmviappframe.ext

import android.text.TextUtils
import com.demo.quickmviappframe.util.Tos

fun String?.isNotEmptyOrNull(): Boolean {
    if ("null" == this) {
        return false
    }
    return !TextUtils.isEmpty(this)
}


fun String?.toastShort() {
    if (this.isNotEmptyOrNull()) {
        Tos.showToastShort(this)
    }
}

fun String?.toastLong() {
    if (this.isNotEmptyOrNull()) {
        Tos.showToastLong(this)
    }
}