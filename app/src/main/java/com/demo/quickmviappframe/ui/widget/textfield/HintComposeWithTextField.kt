package com.demo.quickmviappframe.ui.widget.textfield

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit

interface HintComposeWithTextField {
    /**
     * 输入框没有内容时展示
     * Content of the [TextField] with if value is Empty
     */
    @Composable
    fun Hint(fontSize: TextUnit)

    companion object {
        /**
         * 创建'输入框没有内容时展示一段文字组件'的方法
         * Text content of the [TextField] with if value is Empty
         */
        fun createTextHintCompose(hint: String) = object : HintComposeWithTextField {
            @Composable
            override fun Hint(fontSize: TextUnit) {
                Text(
                    text = hint,
                    color = Color(0xFFB4B4B4),
                    fontSize = fontSize
                )
            }
        }
    }
}