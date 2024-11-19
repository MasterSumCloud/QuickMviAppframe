package com.demo.quickmviappframe.base

open class NoViewModel : BaseViewModel<NoUiState, NoUiIntent, NoUiEffect>() {
    override fun initialState(): NoUiState {
        return NoUiState.Initial
    }
}

sealed class NoUiState : IUiState {
    object Initial : NoUiState()
}

sealed class NoUiIntent : IUiIntent {
    object Initial : NoUiIntent()
}

sealed class NoUiEffect : IUiIntent {
    object Initial : NoUiEffect()
}