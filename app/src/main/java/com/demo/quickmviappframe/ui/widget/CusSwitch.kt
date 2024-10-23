package com.demo.quickmviappframe.ui.widget

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun CustomSwitch(
    width: Dp = 71.dp,
    height: Dp = 30.dp,
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    checkedTrackColor: Color = Color(0xFF8566FF),
    uncheckedTrackColor: Color = Color(0xFFF8F8F8),
    checkedThumbColor: Color = Color.White,
    thumbContent: (@Composable () -> Unit)? = null,
    gapBetweenThumbAndTrackEdge: Dp = 3.dp,
    cornerSize: Dp = 18.dp,
    thumbPadding: Dp = 4.dp,
    thumbSize: Dp = 16.dp,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }

    var switchOn by remember {
        mutableStateOf(checked)
    }

    val bgColorAnmi = animateColorAsState(targetValue = if (switchOn) checkedTrackColor else uncheckedTrackColor, label = "")

    val alignment by animateAlignmentAsState(if (switchOn) 1f else -1f)

    Box(
        modifier = modifier
            .size(width = width, height = height)
            .clip(RoundedCornerShape(size = cornerSize))
            .background(color = bgColorAnmi.value)
            .padding(6.dp)
            .clickable(
                indication = null,
                interactionSource = interactionSource
            ) {
                switchOn = !switchOn
                if (onCheckedChange != null) {
                    onCheckedChange(switchOn)
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .padding(
                    start = gapBetweenThumbAndTrackEdge,
                    end = gapBetweenThumbAndTrackEdge
                )
                .fillMaxSize(),
            contentAlignment = alignment
        ) {
            // Thumb
            if (thumbContent != null) {
                thumbContent()
            } else {
                Box(
                    modifier = Modifier
                        .size(size = thumbSize)
                        .clip(RoundedCornerShape(100))
                        .background(color = checkedThumbColor)
//                        .padding(all = thumbPadding),
                )
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
private fun animateAlignmentAsState(
    targetBiasValue: Float
): State<BiasAlignment> {
    val bias by animateFloatAsState(targetBiasValue, label = "")
    return derivedStateOf { BiasAlignment(horizontalBias = bias, verticalBias = 0f) }
}

