package com.peakmain.compose.space

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * author ：Peakmain
 * createTime：2025/6/23
 * mail:2726449200@qq.com
 * describe：
 */
@Composable
fun PkSpacer(
    modifier: Modifier = Modifier,
    width: Dp = 10.dp,
    height: Dp = 10.dp,
    isHorizontal: Boolean = true
) {
    if (isHorizontal) {
        return PkHorizontalSpacer(modifier, height)
    } else {
        PkVerticalSpacer(modifier, width)
    }
}

@Composable
fun PkVerticalSpacer(modifier: Modifier = Modifier, width: Dp) {
    Spacer(modifier
        .width(width)
        .fillMaxHeight())
}

@Composable
fun PkHorizontalSpacer(modifier: Modifier = Modifier, height: Dp) {
    Spacer(modifier
        .height(height)
        .fillMaxWidth())
}
