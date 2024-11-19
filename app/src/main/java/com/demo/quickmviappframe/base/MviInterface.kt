package com.demo.quickmviappframe.base

import androidx.annotation.Keep

@Keep
interface IUiState

@Keep
interface IUiIntent //event

sealed class LoadUiIntent {
    data class Loading(var isShow: Boolean) : LoadUiIntent()
    data class ShowTxDialog(var isShow: Boolean) : LoadUiIntent()
}