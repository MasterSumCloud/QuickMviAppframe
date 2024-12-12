package com.demo.quickmviappframe.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<S, I, E> : ViewModel() {

    // UI 状态的 StateFlow UI相关
    private val _uiState = MutableStateFlow(initialState())
    val uiState: StateFlow<S> get() = _uiState

    // 单次事件的 SharedFlow 单对多
    private val _effect = MutableSharedFlow<E>()
    val effect: SharedFlow<E> get() = _effect

    // 用户意图 (Channel) 单对单
    private val _intent = Channel<I>(Channel.BUFFERED)
    val intent: Flow<I> get() = _intent.receiveAsFlow()

    private val _loadIintent = Channel<LoadUiIntent>()
    val loadingIntent = _loadIintent.receiveAsFlow()

    /**
     * 初始化 UI 状态
     */
    protected abstract fun initialState(): S

    /**
     * 更新 UI 状态
     */
    fun setState(reducer: S.() -> S) {
        _uiState.update { it.reducer() }
    }

    /**
     * 发送单次事件
     */
    fun sendEffect(effect: E) {
        viewModelScope.launch { _effect.emit(effect) }
    }

    fun sendIntent(intent: I) {
        viewModelScope.launch { _intent.send(intent) }
    }

    fun showLoading() {
        viewModelScope.launch {
            _loadIintent.send(LoadUiIntent.Loading(true))
        }
    }

    fun disLoading() {
        viewModelScope.launch {
            _loadIintent.send(LoadUiIntent.Loading(false))
        }
    }
}