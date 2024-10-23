package com.demo.quickmviappframe.ui.widget

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * 设置横向间隔dp
 * Spacer with horizontal
 */
@Composable
fun HorizontalSpace(dp: Int) {
    Spacer(Modifier.width(dp.dp))
}

@Composable
fun HorizontalSpace(dp: Dp) {
    Spacer(Modifier.width(dp))
}

/**
 * 设置竖向间隔dp
 * Spacer with vertical
 */
@Composable
fun VerticalSpace(dp: Int, modifier: Modifier = Modifier) {
    Spacer(modifier.height(dp.dp))
}

@Composable
fun VerticalSpace(dp: Dp, modifier: Modifier = Modifier) {
    Spacer(modifier.height(dp))
}